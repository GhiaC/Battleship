package logic;

public class MessageTypes {
    /**
     * Version of communication protocol
     */
    public static final byte PROTOCOL_VERISON = 1;

    /**
     * Code of request login message
     */
    public static final byte REQUEST_LOGIN = 1;

    /**
     * data of movement
     */
    public static final byte ATTACK = 2;
    /**
     * chat message
     */
    public static final byte CHAT = 3;

    public static final byte REJECT = 4;

    public static final byte ACCEPT = 5;

    public static final byte FieldMessage = 6;
}
