package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class ReadyMessage extends BaseMessage {
    private byte messageType;

    public ReadyMessage() {
        serialize();
    }

    public ReadyMessage(byte[] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.READY);
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }
}
