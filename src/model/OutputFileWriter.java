package model;

import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {
    private FileWriter file;
    /**
     * open file
     * @param addr address file
     */
    public OutputFileWriter(String addr) {
        try {
            file = new FileWriter(addr);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * write by String
     * @param str str that will be write
     */
    public void write(String str) {
        try {
            file.write(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * close conection
     */
    public void close() {
        try {
            file.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}