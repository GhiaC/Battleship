package logic;

public class MovementMessage extends BaseMessage{

    @Override
    protected void serialize() {

    }

    @Override
    protected void deserialize() {

    }

    @Override
    public byte getMessageType() {
        return MessageTypes.MOVEMENT;
    }
}
