package logic;

import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList;

    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(String ip, int port){

    }

    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * Create a RequestLoginMessage object and sent it through the appropriate network handler.
     */
    public void sendRequestLogin(String to,String username , String password ){

    }
    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * Use the message.
     */
    private void consumeRequestLogin(RequestLoginMessage message){

    }
    
    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler){

    }
    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * According to the message type of baseMessage, call corresponding method to use it.
     */
    @Override
    public void onMessageReceived(BaseMessage baseMessage){
        switch (baseMessage.getMessageType()){
            case MessageTypes.REQUEST_LOGIN:
                consumeRequestLogin((RequestLoginMessage) baseMessage);
                break;
        }
    }

    @Override
    public void onSocketClosed(){

    }
}
