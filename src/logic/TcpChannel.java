package logic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    public TcpChannel(SocketAddress socketAddress,int timeout){

    }
    public TcpChannel(Socket socket,int timeout){

    }

    /**
     * Try to read specific count from input stream
     */
    public byte[] read(final int count){

        return null;
    }

    /**
     * Write bytes on output stream
     */
    public void write(byte[] data){

    }

    /**
     * Check socket's connectivity
     */
    public boolean isConnected(){

        return false;
    }

    /**
     * Try to close socket and input-output streams.
     */
    public void closeChannel(){

    }

}
