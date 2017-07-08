package logic;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.lang.reflect.Executable;
import java.net.Socket;
import java.net.SocketAddress;

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
            socket.setSoTimeout(timeout);
            mOutputStream = new ObjectOutputStream(mSocket.getOutputStream());
            mInputStream = new ObjectInputStream(mSocket.getInputStream());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Try to read specific count from input stream
     */
    public byte[] read(final int count){
        byte[] bytes = new byte[100];
        try {
            if(mInputStream.read(bytes) ==-1 && mSocket.isConnected()) {
                return bytes;
            }
        }catch (Exception E){
            System.out.println("ERROR in read method in tcpChannel Class");
        }
        return null;
    }

    /**
     * Write bytes on output stream
     */
    public void write(byte[] data){
        try {
            mOutputStream.write(data);
            mOutputStream.flush();
            System.out.println("sended data");
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
