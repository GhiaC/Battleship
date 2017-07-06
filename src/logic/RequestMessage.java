package logic;

import java.nio.ByteBuffer;

public class RequestMessage extends BaseMessage {
    private String mUsername;

    public RequestMessage(String name) {
        mUsername = name;
    }

    @Override
    protected void serialize() {
        int usernameLength = mUsername.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + usernameLength ;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.REQUEST_LOGIN);
        byteBuffer.putInt(usernameLength);
        byteBuffer.put(mUsername.getBytes());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int usernameLength = byteBuffer.getInt();
        byte[] usernameBytes = new byte[usernameLength];
        byteBuffer.get(usernameBytes);
        mUsername = new String(usernameBytes);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.REQUEST_LOGIN;
    }

    public String getmUsername() {
        return mUsername;
    }
}
