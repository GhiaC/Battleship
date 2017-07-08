package logic;

import java.nio.ByteBuffer;

public class ChatMessage extends BaseMessage {
    private String textChat;

    public ChatMessage(String textChat) {
        this.textChat = textChat;
        serialize();
    }
    public ChatMessage(byte [] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int textChatLength = textChat.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + textChatLength ;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.REQUEST_LOGIN);
        byteBuffer.putInt(textChatLength);
        byteBuffer.put(textChat.getBytes());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int textChatLength = byteBuffer.getInt();
        byte[] textChatBytes = new byte[textChatLength];
        byteBuffer.get(textChatBytes);
        textChat = new String(textChatBytes);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.CHAT;
    }

    public String getTextChat() {
        return textChat;
    }
}
