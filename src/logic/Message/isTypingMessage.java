package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class isTypingMessage extends BaseMessage {
    private byte messageType;
    private boolean isTyping = false;

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
        int messageLength = 4 + 1 + 1 ;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(getMessageType());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
        if(this.messageType == MessageTypes.isTyping){
            this.isTyping = true;
        }else if(this.messageType == MessageTypes.endTyping){
            this.isTyping = false;
        }
    }

    @Override
    public byte getMessageType() {
        System.out.println(isTyping());
        if(isTyping())
            return MessageTypes.isTyping;
        else if(!isTyping())
            return MessageTypes.endTyping;
        return MessageTypes.endTyping;
    }

    public boolean isTyping() {
        return isTyping;
    }
}
