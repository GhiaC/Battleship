package logic;

public interface INetworkHandlerCallback{
    void onMessageReceived(BaseMessage baseMessage);
    void onSocketClosed();
    void sendData(BaseMessage message);
}