package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;
import java.util.Random;

public class TurnMessage extends BaseMessage {
    private byte messageType;
    private byte turn;

    public TurnMessage() {
        serialize();
    }

    public TurnMessage(byte[] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.TURN);
        byteBuffer.put(MessageTypes.TURNServer);
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
        this.turn = byteBuffer.get();
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }

    public byte getTurn() {
        return turn;
    }
}
