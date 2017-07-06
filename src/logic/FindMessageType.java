package logic;

import java.awt.*;
import java.nio.ByteBuffer;

public class FindMessageType {
    byte messageType;
    public FindMessageType( byte [] mSerialized) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        messageType = byteBuffer.get();
    }
    public byte getType(){
        return messageType;
    }
}
