package logic;

public interface INetworkHandlerCallback{
    void onMessageReceived(BaseMessage baseMessage);
    void onSocketClosed();
}