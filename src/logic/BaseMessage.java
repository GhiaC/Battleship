package logic;

public abstract class BaseMessage {
    protected byte [] mSerialized;

    /**
     *  Fiels are stored into serial bytes in this method
     */
    protected abstract void serialize () ;

    /**
     * Fields are restored from serial bytes in this method
     */
    protected abstract void deserialize () ;

    /**
     * Return message type code
     */
    public abstract byte getMessageType ();

    public byte [] getSerialized () {
        return mSerialized;
    }
}