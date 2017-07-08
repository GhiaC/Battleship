package logic;

import sun.nio.ch.Net;
import tools.ChatHandler;
import tools.MessageManagerHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();

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
            networkHandler.start();
            mNetworkHandlerList.add(networkHandler);
        }
    }

    private void closeConnection() throws IOException{
        System.out.println("Closing connection");
//        output.close();
//        input.close();
//        client.close();
        // TODO show message
    }

    @Override
    public void sendData(BaseMessage message) {
        if(mNetworkHandlerList.size() > 0) {
            for (int i = 0; i < mNetworkHandlerList.size(); i++) {
                mNetworkHandlerList.get(i).sendMessage(new ChatMessage("HIIIIIIIII"));
            }
        }
    }

    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler){
        networkHandler.start();
        mNetworkHandlerList.add(networkHandler);
        sendData(new ChatMessage("WWWWWWW"));
    }

    @Override
    public void onSocketClosed(){

    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage){
        baseMessage = new ChatMessage("onMessageReceived");
        if(((ChatMessage) baseMessage).getTextChat() != null) {
            ChatHandler chatHandler = new ChatHandler();
            chatHandler.writeMessage("test ", ((ChatMessage) baseMessage).getTextChat());
        }
//        switch (baseMessage.getMessageType()){
//            case MessageTypes.REQUEST_LOGIN:
//                consumeRequestLogin((RequestLoginMessage) baseMessage);
//                break;
//        }
    }


}
