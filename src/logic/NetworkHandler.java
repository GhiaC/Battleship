package logic;

import logic.Message.*;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private ArrayList<byte[]> mSendQueue;
    private ArrayList<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    protected INetworkHandlerCallback iNetworkHandlerCallback;
    private boolean stop = false;

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
        if(!stop) {
            try {
                mSendQueue.add(baseMessage.getSerialized());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
        mTcpChannel.closeChannel();
        stop = true;
    }

    /**
     * Try to read some bytes from the channel.
     */
    public byte[] readChannel() {
        if (!stop) {
            int size = sizeOfMessage();
            byte[] bytes = mTcpChannel.read(size - 4);
            return bytes;
        }
        return null;
    }

    private class Sender extends Thread {
        public Sender() {
            start();
        }

        @Override
        public void run() {
            try {
                while (mTcpChannel.isConnected() && !stop) {
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
                while (mTcpChannel.isConnected() && !stop) {
                    byte[] bytes = readChannel();
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
                        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
//        int messageLength = byteBuffer.getInt();
                        byte protocolVersion = byteBuffer.get();
                        byte messageType = byteBuffer.get();
                        BaseMessage baseMessage = null;
                        switch (messageType) {
                            case MessageTypes.REQUEST_LOGIN:
                                baseMessage = new RequestLoginMessage(bytes);
                                break;
                            case MessageTypes.ATTACK:
                                baseMessage = new AttackMessage(bytes);
                                break;
                            case MessageTypes.CHAT:
                                baseMessage = new ChatMessage(bytes);
                                break;
                            case MessageTypes.ACCEPT:
                                baseMessage = new AcceptRejectMessage(bytes);
                                break;
                            case MessageTypes.REJECT:
                                baseMessage = new AcceptRejectMessage(bytes);
                                break;
                            case MessageTypes.FieldMessage:
                                baseMessage = new FieldMessage(bytes);
                                break;
                            case MessageTypes.READY:
                                baseMessage = new ReadyMessage(bytes);
                                break;
                        }
                        iNetworkHandlerCallback.onMessageReceived(baseMessage);
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
