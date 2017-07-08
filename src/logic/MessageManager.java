package logic;

import logic.Message.AcceptRejectMessage;
import logic.Message.AttackMessage;
import logic.Message.ChatMessage;
import logic.Message.RequestLoginMessage;
import tools.ChatHandler;
import veiw.MainFrame;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();
    private int enemyNum;
    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port){
        mServerSocketHandler = new ServerSocketHandler(port,this,this);
        mServerSocketHandler.start();
    }

    public MessageManager(String ip, int port){
        Socket socket = null;
        try {
            socket = new Socket(ip, port); //server's ip and port
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(socket!=null){
            NetworkHandler networkHandler = new NetworkHandler(socket,this );
            mNetworkHandlerList.add(networkHandler);
        }
    }

    private void closeConnection() throws IOException{
        System.out.println("Closing connection");
        for (int i = 0; i < mNetworkHandlerList.size(); i++) {
            mNetworkHandlerList.get(i).stopSelf();
        }
    }

    public void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message);
        mNetworkHandlerList.get(enemyNum).sendMessage(chatMessage);
    }

    public void sendPointAttack(int x ,int y){
        AttackMessage attackMessage = new AttackMessage(x,y);
        mNetworkHandlerList.get(enemyNum).sendMessage(attackMessage);
    }
    public void sendRequestLogin(String name){
        RequestLoginMessage requestLoginMessage = new RequestLoginMessage(name);
        mNetworkHandlerList.get(enemyNum).sendMessage(requestLoginMessage);
    }
    public void Accept(int enemyNum){
        this.enemyNum = enemyNum;
        AcceptRejectMessage AcceptMessage = new AcceptRejectMessage(true);
        AcceptRejectMessage RejectMessage = new AcceptRejectMessage(false);
        if(mNetworkHandlerList.size() > 0) {
            for (int i = 0; i < mNetworkHandlerList.size(); i++) {
                if(i != enemyNum){
                    mNetworkHandlerList.get(i).sendMessage(RejectMessage);
                }else{
                    mNetworkHandlerList.get(i).sendMessage(AcceptMessage);
                }
            }
        }
    }
    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler){
        mNetworkHandlerList.add(networkHandler);
    }

    @Override
    public void onSocketClosed(){

    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage){
        switch (baseMessage.getMessageType()) {
            case MessageTypes.REQUEST_LOGIN:

                break;
            case MessageTypes.ATTACK:

                break;
            case MessageTypes.CHAT:
                ChatHandler chatHandler = new ChatHandler();
                chatHandler.writeMessage("test ", ((ChatMessage) baseMessage).getTextChat());
                break;
            case MessageTypes.ACCEPT:
                //MainFrame mainFrame = new MainFrame();
                break;
            case MessageTypes.REJECT:

                break;
            case MessageTypes.FieldMessage:

                break;
        }
    }


}
