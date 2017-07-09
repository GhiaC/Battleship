package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class isTypingMessage extends BaseMessage {
    private byte messageType;
    private boolean isTyping;

    public isTypingMessage(boolean isTyping) {
        this.isTyping = isTyping;
        serialize();
    }
    public isTypingMessage(byte [] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1  ;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        if(isTyping){
            byteBuffer.put(MessageTypes.isTyping);
        }else {
            byteBuffer.put(MessageTypes.endTyping);
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

    public boolean isTyping() {
        return isTyping;
    }
}
