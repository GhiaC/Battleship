package logic;

//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import tools.ChatHandler;

import java.lang.reflect.Executable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private ArrayList<byte[]> mSendQueue;
    private ArrayList<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    protected INetworkHandlerCallback iNetworkHandlerCallback;
    private boolean queueEmpty = false;

    //k
//    public NetworkHandler(SocketAddress socketAddress,INetworkHandlerCallback iNetworkHandlerCallback){
//
//    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback) {
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        mTcpChannel = new TcpChannel(socket, 10000);
        mSendQueue = new ArrayList<byte[]>();
        mReceivedQueue = new ArrayList<byte[]>();
        mSendQueue.clear();
        mReceivedQueue.clear();
        mConsumerThread = new ReceivedMessageConsumer();
        mConsumerThread.start();
    }

    /**
     * Add serialized bytes of message to the sendQueue
     */
    public void sendMessage(BaseMessage baseMessage) {
        try {
            ChatMessage chatMessage = new ChatMessage("EEEEEEEEEE");
            mSendQueue.add(chatMessage.getSerialized());
            System.out.println("added in queue");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * while channel is connected and stop is not called :
     * if sendQueue is not empty,then poll a message and send it
     * else if readChannel() is returning bytes,then add it to receivedQueue.
     */
    @Override
    public void run() {
        System.out.println("networkHandler Started");
        ChatMessage chatMessage = new ChatMessage("CCCCCCCC");
        while (mTcpChannel.isConnected() && !Thread.currentThread().isInterrupted()) {
            if (!mSendQueue.isEmpty()) {
                mTcpChannel.write(mSendQueue.get(0));
                mSendQueue.remove(0);
            } else {
                byte[] bytes = readChannel();
                if (bytes != null)
                    mReceivedQueue.add(chatMessage.getSerialized());
//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    /**
     * Kill the thread and close the channel.
     */
    public void stopSelf() {
        queueEmpty = false;
    }

    /**
     * Try to read some bytes from the channel.
     */
    public byte[] readChannel() {
        byte[] bytes = mTcpChannel.read(100);
        return bytes;
    }

    private class ReceivedMessageConsumer extends Thread {

        /**
         * while channel is connected and stop is not called:
         * if there exists message in receivedQueue, then create a message object
         * from appropriate class based and message type byte and pass it through onMessageReceived
         * else if receivedQueue is empty, then sleep 100 ms
         */
        @Override
        public void run() {
            try {
                while (mTcpChannel.isConnected()) {
                    if (mReceivedQueue.size() > 0) {
                        System.out.println("received in ReceivedMessageConsumer");
                        byte[] bytes = mReceivedQueue.get(0);
                        mReceivedQueue.remove(0);
                        ChatMessage chatMessage = new ChatMessage(bytes);
                        iNetworkHandlerCallback.onMessageReceived(chatMessage);
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR IN ReceivedMessageConsumer" + e.getMessage());
            }
        }
    }

}
