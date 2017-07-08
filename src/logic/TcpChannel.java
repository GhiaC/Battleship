package logic;

import java.io.*;
import java.net.Socket;

public class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;


//    public TcpChannel(SocketAddress socketAddress,int timeout){
//
//    }

    public TcpChannel(Socket socket,int timeout){
        mSocket = socket;
        try {
//            socket.setSoTimeout(timeout);
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Try to read specific count from input stream
     */
    public byte[] read(final int count){
        byte[] bytes = new byte[count];
        try {
            if(mSocket.isConnected() && mInputStream.read(bytes) !=-1) {
                return bytes;
            }
        }catch (IOException E){
            E.printStackTrace();
        }
        return null;
    }

    /**
     * Write bytes on output stream
     */
    public void write(byte[] data){
        System.out.println("writing");
        try {
            mOutputStream.write(data);
        }catch (IOException ioException){
            System.out.println("Error writing object in messageManger class");
        }
        // TODO receive terminate recipe
    }

    /**
     * Check socket's connectivity
     */
    public boolean isConnected(){
        return mSocket.isConnected();
    }

    /**
     * Try to close socket and input-output streams.
     */
    public void closeChannel(){
        try {
            mInputStream.close();
            mOutputStream.close();
            mSocket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
