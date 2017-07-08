package logic;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

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
        mSendQueue = new ArrayList<>();
        mReceivedQueue = new ArrayList<>();
        mSendQueue.clear();
        mReceivedQueue.clear();
        mConsumerThread = new ReceivedMessageConsumer();
        mConsumerThread.start();
        new Sender();
        new Receiver();
    }

    /**
     * Add serialized bytes of message to the sendQueue
     */
    public void sendMessage(BaseMessage baseMessage) {
        try {
            mSendQueue.add(baseMessage.getSerialized());
            System.out.println("in sendMessage method :" + ((ChatMessage) baseMessage).getTextChat());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private int sizeOfMessage() {
        return ByteBuffer.wrap(mTcpChannel.read(4)).getInt();
    }
//    /**
//     * while channel is connected and stop is not called :
//     * if sendQueue is not empty,then poll a message and send it
//     * else if readChannel() is returning bytes,then add it to receivedQueue.
//     */
//    @Override
//    public void run() {
//        System.out.println("networkHandler started");
//        try {
//            while (mTcpChannel.isConnected()) {
//                System.out.println(mSendQueue.isEmpty());
//                if (!mSendQueue.isEmpty()) {
//                    mTcpChannel.write(mSendQueue.get(0));
//                    mSendQueue.remove(0);
//                } else {
//                    System.out.println("before read");
//                    byte[] bytes = readChannel();
//                    System.out.println("after read");
//                    if (bytes != null) {
//                        mReceivedQueue.add(bytes);
//                    }
//                }
//            }
//        }catch (Exception e){
//            System.out.println("Error in rn method of networkHandler");
//        }
//    }

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
        int size = sizeOfMessage();
        byte[] bytes = mTcpChannel.read(size - 4);
        return bytes;
    }

    private class Sender extends Thread {
        public Sender() {
            start();
        }

        @Override
        public void run() {
            try {
                while (mTcpChannel.isConnected()) {
                    if (!mSendQueue.isEmpty()) {
                        mTcpChannel.write(mSendQueue.get(0));
                        mSendQueue.remove(0);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error in method of networkHandler");
            }
        }
    }

    private class Receiver extends Thread {
        public Receiver() {
            start();
        }

        @Override
        public void run() {
            try {
                while (mTcpChannel.isConnected()) {
                    System.out.println("before read");
                    byte[] bytes = readChannel();
                    System.out.println("after read");
                    if (bytes != null) {
                        mReceivedQueue.add(bytes);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error in method of networkHandler");
            }
        }
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
                    if (!mReceivedQueue.isEmpty()) {
                        byte[] bytes = mReceivedQueue.get(0);
                        mReceivedQueue.remove(0);
                        ChatMessage chatMessage = new ChatMessage(bytes);
                        System.out.println("message received");
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
