package logic.Message;


import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class AttackMessage extends BaseMessage {
    private int x, y;
    private byte messageType;

    public AttackMessage(int x, int y) {
        this.x = x;
        this.y = y;
        serialize();
    }

    public AttackMessage(byte[] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 4;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.ATTACK);
        byteBuffer.putInt(x);
        byteBuffer.putInt(y);
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
        this.x = byteBuffer.getInt();
        this.y = byteBuffer.getInt();
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
