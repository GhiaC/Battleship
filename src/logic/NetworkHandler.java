package logic;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

public class NetworkHandler extends Thread{
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;

    public NetworkHandler(SocketAddress socketAddress,INetworkHandlerCallback inetWorkHandlerCallback){

    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback ){

    }

    /**
     * Add serialized bytes of message to the sendQueue
     */
    public void sendMessage(BaseMessage baseMessage){

    }
    /**
     * while channel is connected and stop is not called :
     * if sendQueue is not empty,then poll a message and send it
     * else if readChannel() is returning bytes,then add it to receivedQueue.
     */
    @Override
    public void run(){

    }

    /**
     * Kill the thread and close the channel.
     */
    public void stopSelf(){

    }

    /**
     * Try to read some bytes from the channel.
     */
    public byte[] readChannel(){

        return null;
    }
    private class ReceivedMessageConsumer extends Thread{

        /**
         * cm
         */
        @Override
        public void run(){

        }
    }

}
