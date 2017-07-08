package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class AcceptRejectMessage extends BaseMessage {
    private byte messageType;
    private boolean accept;

    public AcceptRejectMessage(boolean accept) {
        this.accept = accept;
        serialize();
    }
    public AcceptRejectMessage(byte [] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1  ;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        if(accept){
            byteBuffer.put(MessageTypes.ACCEPT);
        }else {
            byteBuffer.put(MessageTypes.REJECT);
        }
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }
}
