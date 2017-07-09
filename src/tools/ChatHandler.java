package tools;

import model.OutputFileWriter;
import org.json.JSONArray;
import veiw.ChatMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class ChatHandler {

    private static JSONArray jsonArr = new JSONArray();

    public static void GameAlarm(String pm){
        ChatMessage chatMessage = new ChatMessage("GAME", pm);
    }
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
        if(jsonArr.length() == 0){
            return;
        }
        String message = jsonArr.toString();
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();

        OutputFileWriter outputFileWriter = new OutputFileWriter("historyChat\\"+dateObj.getYear()+"-"+dateObj.getMonth()+"-"+dateObj.getDay()+dateObj.getTime()+".txt");
        outputFileWriter.write(message);
        outputFileWriter.close();
    }
}
