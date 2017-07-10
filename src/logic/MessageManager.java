package logic;

import logic.Message.*;
import tools.ChatHandler;
import tools.Game;
import veiw.ChatPanel;
import veiw.GuestWaitingFrame;
import veiw.WaitingForConnectionFrame;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback {
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();
    private int enemyNum = 0;
    private WaitingForConnectionFrame waitingForConnectionFrame;
    private String name;
    private GuestWaitingFrame guestWaitingFrame;
    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port,String name) {
        mServerSocketHandler = new ServerSocketHandler(port, this, this);
        mServerSocketHandler.start();
        waitingForConnectionFrame = new WaitingForConnectionFrame();
        this.name = name;
    }

    public MessageManager(String ip, int port,String name) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port); //server's ip and port
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (socket != null) {
            NetworkHandler networkHandler = new NetworkHandler(socket, this);
            mNetworkHandlerList.add(networkHandler);
            sendRequestLogin(name);
            guestWaitingFrame = new GuestWaitingFrame();

        }
    }

    private void closeConnection() throws IOException {
        System.out.println("Closing connection");
        for (int i = 0; i < mNetworkHandlerList.size(); i++) {
            mNetworkHandlerList.get(i).stopSelf();
        }
    }

    public void sendMessage(String name, String message) {
        ChatMessage chatMessage = new ChatMessage(name, message);
        mNetworkHandlerList.get(enemyNum).sendMessage(chatMessage);
    }

    public void sendPointAttack(int x, int y) {
        AttackMessage attackMessage = new AttackMessage(x, y);
        mNetworkHandlerList.get(enemyNum).sendMessage(attackMessage);
    }

    public void sendRequestLogin(String name) {
        RequestLoginMessage requestLoginMessage = new RequestLoginMessage(name);
        mNetworkHandlerList.get(enemyNum).sendMessage(requestLoginMessage);
    }


    public void sendTurn() {
        TurnMessage turnMessage = new TurnMessage();
        int turn = 1;
        if (turnMessage.getTurn() == MessageTypes.TURNServer) {
            turn = 0;
        }
        Game.setTurn(turn);
        mNetworkHandlerList.get(enemyNum).sendMessage(turnMessage);
    }

    public void Accept(int enemyNum) {
        this.enemyNum = enemyNum;
        AcceptRejectMessage AcceptMessage = new AcceptRejectMessage(true);
        AcceptRejectMessage RejectMessage = new AcceptRejectMessage(false);
        if (mNetworkHandlerList.size() > 0) {
            for (int i = 0; i < mNetworkHandlerList.size(); i++) {
                if (i != enemyNum) {
                    mNetworkHandlerList.get(i).sendMessage(RejectMessage);
                } else {
                    mNetworkHandlerList.get(i).sendMessage(AcceptMessage);
                }
            }
        }
        sendTurn();
        Game.openMainFrame();
        waitingForConnectionFrame.setVisible(false);
    }
    public void Reject(int Num) {
        AcceptRejectMessage RejectMessage = new AcceptRejectMessage(false);
        mNetworkHandlerList.get(Num).sendMessage(RejectMessage);
        mNetworkHandlerList.get(Num).stopSelf();
    }

    public void isTyping(boolean isTyping) {
        isTypingMessage isTypingMessage = new isTypingMessage(isTyping);
        mNetworkHandlerList.get(enemyNum).sendMessage(isTypingMessage);
    }

    public void sendField(boolean[][] Field) {
        FieldMessage fieldMessage = new FieldMessage(Field);
        mNetworkHandlerList.get(enemyNum).sendMessage(fieldMessage);
        sendMessage("Enemy", "<span style='color:green'>I'm Ready</span>");
    }

    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        mNetworkHandlerList.add(networkHandler);
    }

    @Override
    public void onSocketClosed() {

    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {
        switch (baseMessage.getMessageType()) {
            case MessageTypes.REQUEST_LOGIN:
                String ip = mNetworkHandlerList.get(mNetworkHandlerList.size()-1).getIP();
                waitingForConnectionFrame.addNewConnection(ip,((RequestLoginMessage)baseMessage).getmUsername());
                break;
            case MessageTypes.ATTACK:
                Game.attackAt(((AttackMessage) baseMessage).getX(), ((AttackMessage) baseMessage).getY());
                //TODO
                break;
            case MessageTypes.CHAT:
                ChatHandler chatHandler = new ChatHandler();
                chatHandler.writeMessage(((ChatMessage) baseMessage).getName(), ((ChatMessage) baseMessage).getTextChat());
                break;
            case MessageTypes.ACCEPT:
                Game.openMainFrame();
                guestWaitingFrame.setVisible(false);
                //TODO
                break;
            case MessageTypes.REJECT:
                try {
//                    closeConnection();
                }catch (Exception e){
                    System.out.println("error in messagemanager line 151");
                }
                guestWaitingFrame.setVisible(false);
                System.exit(0);
                break;
            case MessageTypes.FieldMessage:
                Game.setOneField(((FieldMessage) baseMessage).getPlayerField(), 1);
                break;
            case MessageTypes.READY:
                //TODO
                break;
            case MessageTypes.TURN:
                int turn = 0;
                if (((TurnMessage) baseMessage).getTurn() == MessageTypes.TURNServer) {
                    turn = 1;
                }
                Game.setTurn(turn);
                break;
            case MessageTypes.isTyping:
                ChatPanel.isTypingLanel.setText("is Typing ...");
                break;
            case MessageTypes.endTyping:
                ChatPanel.isTypingLanel.setText("");
                break;

        }
    }


}
