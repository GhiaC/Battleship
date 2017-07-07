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

    // k
//    public TcpChannel(SocketAddress socketAddress,int timeout){
//
//    }

    public TcpChannel(Socket socket,int timeout){
        mSocket = socket;
        try {
            mOutputStream = new ObjectOutputStream(mSocket.getOutputStream());
            mOutputStream.flush();

            mInputStream = new ObjectInputStream(mSocket.getInputStream());
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
            System.out.println("read in tcpChannel");
            mInputStream.read(bytes);
            //TODO  : parse data and do that effect
            System.out.println("read");//Test
        }catch (Exception E){
            System.out.println("ERROR in ProcessConnection method in tcpChannel Class");
        }
        return bytes;
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
