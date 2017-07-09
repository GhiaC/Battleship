package tools;

import model.OutputFileWriter;
import org.json.JSONArray;
import veiw.ChatMessage;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatHandler {

    private static ArrayList messageLog = new ArrayList();
    private static JSONArray jsonArr = new JSONArray();

    public void writeMessage(String name, String message) {
        ChatMessage chatMessage = new ChatMessage(name, message);

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("message", message);
        json.put("time",dateObj.getHours() + " : " + dateObj.getMinutes());
        jsonArr.put(json);
    }
    public static void saveMessage(){
        String message = jsonArr.toString();
        OutputFileWriter outputFileWriter = new OutputFileWriter("log.txt");
        System.out.println(message);
        outputFileWriter.write(message);
        outputFileWriter.close();
    }
}
