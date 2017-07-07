package logic;

import sun.nio.ch.Net;
import tools.ChatHandler;

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
            mNetworkHandlerList.add(networkHandler);
            networkHandler.start();
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
//        System.out.println(mNetworkHandlerList.size());
        if(mNetworkHandlerList.size() > 0) {
            System.out.println(mNetworkHandlerList.size());
            for (int i = 0; i < mNetworkHandlerList.size(); i++) {
                mNetworkHandlerList.get(i).sendMessage(message);
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

//    /**
//     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
//     * Create a RequestLoginMessage object and sent it through the appropriate network handler.
//     * EDITED by masoud
//     */
//    public void sendJoinRequest(){
//
////        sendData();
//    }
//    /**
//     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
//     * Use the message.
//     */
//    private void consumeRequestLogin(RequestLoginMessage message){
//
//    }


    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * According to the message type of baseMessage, call corresponding method to use it.
     */
    @Override
    public void onMessageReceived(BaseMessage baseMessage){
        ChatHandler chatHandler = new ChatHandler();
        chatHandler.writeMessage("test",((ChatMessage) baseMessage).getTextChat());
//        switch (baseMessage.getMessageType()){
//            case MessageTypes.REQUEST_LOGIN:
//                consumeRequestLogin((RequestLoginMessage) baseMessage);
//                break;
//        }
    }


}
