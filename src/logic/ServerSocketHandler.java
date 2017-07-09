package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread {
    private IServerHandlerCallback iServerHandlerCallback;
    private INetworkHandlerCallback iNetworkHandlerCallback;
    private int port;
    private ServerSocket server;

    public ServerSocketHandler(int port, INetworkHandlerCallback iNetworkHandlerCallback,
                               IServerHandlerCallback iServerSocketHandlerCallback) {
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        this.iServerHandlerCallback = iServerSocketHandlerCallback;
        this.port = port;
        server = null;
        try {
            server = new ServerSocket(port, 100); //server's ip and port
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * while server socket is connected and stop is not called
     * if a connection receives , then create a network handler and pass it through onNewConnectionReceived
     * else sleep for 100ms
     */
    @Override
    public void run() {

        if (!server.isClosed() && !Thread.currentThread().isInterrupted()) {
            try {
                while (!server.isClosed()) {
                    Socket s = server.accept();
                    NetworkHandler network = new NetworkHandler(s, iNetworkHandlerCallback);
                    this.iServerHandlerCallback.onNewConnectionReceived(network);
                }
            } catch (IOException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException iEx) {
                    iEx.printStackTrace();
                }
            } catch (Exception e) {
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
