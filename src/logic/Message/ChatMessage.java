package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

import java.nio.ByteBuffer;

public class ChatMessage extends BaseMessage {
    private String textChat, name;
    private byte messageType;

    public ChatMessage(String name, String textChat) {
        this.name = name;
        this.textChat = textChat;
        serialize();
    }

    public ChatMessage(byte[] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int textChatLength = textChat.getBytes().length;
        int nameLength = textChat.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + textChatLength + 4 + nameLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.CHAT);
        byteBuffer.putInt(textChatLength);
        byteBuffer.put(textChat.getBytes());
        byteBuffer.putInt(nameLength);
        byteBuffer.put(name.getBytes());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
        int textChatLength = byteBuffer.getInt();
        byte[] textChatBytes = new byte[textChatLength];
        byteBuffer.get(textChatBytes);
        textChat = new String(textChatBytes);

        int nameLength = byteBuffer.getInt();
        byte[] nameBytes = new byte[nameLength];
        byteBuffer.get(nameBytes);
        name = new String(nameBytes);
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }

    public String getTextChat() {
        return textChat;
    }

    public String getName() {
        return name;
    }
}
