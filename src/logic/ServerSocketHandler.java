package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread {
    private IServerHandlerCallback iServerHandlerCallback;
    private INetworkHandlerCallback iNetworkHandlerCallback;
    private int port;

    public ServerSocketHandler(int port, INetworkHandlerCallback iNetworkHandlerCallback,
                               IServerHandlerCallback iServerSocketHandlerCallback) {
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        this.iServerHandlerCallback = iServerSocketHandlerCallback;
        this.port = port;
    }

    /**
     * long cm
     */
    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port, 10000); //server's ip and port
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (server != null) {
            try {
                while (true) {
                    Socket s = server.accept();
                    NetworkHandler network = new NetworkHandler(s,iNetworkHandlerCallback);
                    this.iServerHandlerCallback.onNewConnectionReceived(network);
                }
            } catch(IOException e){
                    e.printStackTrace();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Kill the thread and close the server socket.
     */
    public void stopSelf() {
        //TODO send terminate message to client and exit

    }

}
