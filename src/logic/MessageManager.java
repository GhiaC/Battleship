package logic;

import logic.Message.*;
import tools.ChatHandler;
import tools.Game;
import veiw.ChatPanel;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback {
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();
    private int enemyNum = 0;

    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port) {
        mServerSocketHandler = new ServerSocketHandler(port, this, this);
        mServerSocketHandler.start();
    }

    public MessageManager(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port); //server's ip and port
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (socket != null) {
            NetworkHandler networkHandler = new NetworkHandler(socket, this);
            mNetworkHandlerList.add(networkHandler);
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

    public void Ready() {
        ReadyMessage readyMessage = new ReadyMessage();
        mNetworkHandlerList.get(enemyNum).sendMessage(readyMessage);
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
    }

    public void isTyping(boolean isTyping) {
        isTypingMessage isTypingMessage = new isTypingMessage(isTyping);
        mNetworkHandlerList.get(enemyNum).sendMessage(isTypingMessage);
    }

    public void sendField(boolean[][] Field) {
        FieldMessage fieldMessage = new FieldMessage(Field);
        mNetworkHandlerList.get(enemyNum).sendMessage(fieldMessage);
        sendMessage("Enemy","<span style='color:green'>I'm Ready</span>");
    }

    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        mNetworkHandlerList.add(networkHandler);
        sendTurn();
    }

    @Override
    public void onSocketClosed() {

    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {
        switch (baseMessage.getMessageType()) {
            case MessageTypes.REQUEST_LOGIN:
                //TODO
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
                //MainFrame mainFrame = new MainFrame();
                //TODO
                break;
            case MessageTypes.REJECT:
                //TODO
                break;
            case MessageTypes.FieldMessage:
                Game.setOneField(((FieldMessage) baseMessage).getPlayerField(), 1);
                //TODO
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
                //TODO
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
