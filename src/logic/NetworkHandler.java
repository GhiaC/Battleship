package logic;

import tools.ChatHandler;

import java.net.Socket;
import java.util.Queue;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    protected INetworkHandlerCallback iNetworkHandlerCallback;
    private boolean queueEmpty = false;

    //k
//    public NetworkHandler(SocketAddress socketAddress,INetworkHandlerCallback iNetworkHandlerCallback){
//
//    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback) {
        mTcpChannel = new TcpChannel(socket, 10000);
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
    }

    /**
     * Add serialized bytes of message to the sendQueue
     */
    public void sendMessage(BaseMessage baseMessage) {
        mSendQueue.add(baseMessage.getSerialized());
    }

    /**
     * while channel is connected and stop is not called :
     * if sendQueue is not empty,then poll a message and send it
     * else if readChannel() is returning bytes,then add it to receivedQueue.
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (mTcpChannel.isConnected() && !queueEmpty) {
                    for (int i = 0; i < mSendQueue.size(); i++) {
                        mTcpChannel.write(mSendQueue.poll());
                    }

                    mReceivedQueue.add(readChannel());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        byte[] bytes = mTcpChannel.read(4);
        return bytes;
    }

    private class ReceivedMessageConsumer extends Thread {

        /**
         * while channel is connected and stop is not called:
         * if there exists message in receivedQueue, then create a message object
         *      from appropriate class based and message type byte and pass it through onMessageReceived
         * else if receivedQueue is empty, then sleep 100 ms
         */
        @Override
        public void run() {
            if(mReceivedQueue.size() > 0){
                for (int i = 0; i < mReceivedQueue.size(); i++) {
                    byte [] bytes =mReceivedQueue.poll();
                    ChatMessage chatMessage = new ChatMessage(bytes);
                    iNetworkHandlerCallback.onMessageReceived(chatMessage);
                }
            }

        }
    }

}
