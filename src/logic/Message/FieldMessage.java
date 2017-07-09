package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;
import veiw.PlayerField;

import java.nio.ByteBuffer;

public class FieldMessage extends BaseMessage {
    private boolean[][] Field =new boolean[10][10];
    private byte messageType;
    PlayerField playerField = new PlayerField();

    public FieldMessage(boolean[][] field) {
        this.Field =field;
        serialize();
    }
    public FieldMessage(PlayerField playerField) {
//        this.Field = playerField.get
        serialize();
    }

    public FieldMessage(byte[] mSerialized) {
        this.mSerialized = mSerialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 100;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERISON);
        byteBuffer.put(MessageTypes.FieldMessage);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                boolean f =this.Field[i][j];
                if(f){
                    byteBuffer.put(MessageTypes.SHIP);
                }else{
                    byteBuffer.put(MessageTypes.EMPTY);
                }
            }
        }
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
//        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        this.messageType = byteBuffer.get();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                byte byt = byteBuffer.get();
                if(byt == MessageTypes.SHIP){
                    this.Field[i][j]=true;
                }else{
                    this.Field[i][j]=false;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                boolean f =this.Field[i][j];
                if(f){
                    playerField.setShipAt(i,j);
                }
            }
        }
    }

    @Override
    public byte getMessageType() {
        return this.messageType;
    }

    public PlayerField getPlayerField() {
        return playerField;
    }

    public boolean[][] getField() {
        return Field;
    }
}
