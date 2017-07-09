package model;

import logic.Message.FieldMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputFileRedear {

    private String filename = "log.txt";

    public InputFileRedear(String filename) {
        this.filename = filename;
    }

    public String readLog() {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                result += (sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}