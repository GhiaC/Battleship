package logic;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList;

    /**
     * mFields
     */
    private Socket client;
    private ServerSocket server;
    private String ip;
    private int port;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private String data ; //IMPORTANT

    private int head = 0 ;
    private final int maxClient = 1000;

    private Socket connection[] = new Socket[maxClient];
    private ObjectOutputStream outputArr[] = new ObjectOutputStream[maxClient];
    private ObjectInputStream inputArr[] = new ObjectInputStream[maxClient];


    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port){
        mServerSocketHandler = new ServerSocketHandler(port,this,this);

    }

    public MessageManager(String ip, int port){
        this.ip = ip;
        this.port = port;
        Socket socket = null;
        try {
            socket = new Socket(ip, port); //server's ip and port
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(socket!=null){
            mNetworkHandlerList.add(new NetworkHandler(socket,this ));
        }
    }

    private void closeConnection() throws IOException{
        System.out.println("Closing connection");
        output.close();
        input.close();
        client.close();
        // TODO show message
    }

    private void sendData(BaseMessage message){

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

    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * Create a RequestLoginMessage object and sent it through the appropriate network handler.
     * EDITED by masoud
     */
    public void sendJoinRequest(){

//        sendData();
    }
    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * Use the message.
     */
    private void consumeRequestLogin(RequestLoginMessage message){

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


}
