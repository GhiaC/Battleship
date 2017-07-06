package logic;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class MessageManager implements IServerHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList;

    /**
     * mFields
     */
    private Socket client;
    private String ip;
    private int port;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private String data ; //ImPORTANT

    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port){

    }
    public MessageManager(String ip, int port){
        this.ip = ip;
        this.port = port;

        try {
            connectToServer();

            getStreams();

            processConnection();

            closeConnection();

        }catch (EOFException eofException){
            System.out.println("Server terminated connection");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * mMethod
     * client connection
     */
    private void connectToServer() throws IOException{
        System.out.println("Attempting connection\n");
        client = new Socket(InetAddress.getByName(ip),port); //server's ip and port
        System.out.println("Connected to : " + client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
        System.out.println("created outPutStream");
    }
    private void processConnection() throws IOException{
//        do {
            try {
                data = (String)input.readObject();
                //TODO  : parse data and do that effect
                System.out.println(data);//Test
            } catch (ClassNotFoundException e) {
                System.out.println("Unknown object type received");
            }catch (Exception E){
                System.out.println("ERROR in processConectrion method in messageManager Class");
            }
//        }while (!message.equals("SERVER>>> TERMINATE"));
        //TODO receive terminate recipe
    }
    private void closeConnection() throws IOException{
        System.out.println("Closing connection");
        output.close();
        input.close();
        client.close();
        // TODO show message
    }

    private void sendData(BaseMessage message){
        try {
            output.writeObject(message.getSerialized());
            output.flush();
            System.out.println("send data : "+ message);
        }catch (IOException ioException){
            System.out.println("Error writing object in messageManger class");
        }
        // TODO receive terminate recipe
    }





    /**
     * IMPORTANT : Request Login is an example message and doesn't relate to this project!
     * Create a RequestLoginMessage object and sent it through the appropriate network handler.
     * EDITED by masoud
     */
    public void sendJoinRequest( ){

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
