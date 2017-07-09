package logic.Message;

import logic.BaseMessage;
import logic.MessageTypes;

public class FieldMessage extends BaseMessage {
    @Override
    protected void serialize() {

    }

    @Override
    protected void deserialize() {

    }

    @Override
    public byte getMessageType() {
        return MessageTypes.FieldMessage;
    }
}
