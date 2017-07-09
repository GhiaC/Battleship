package veiw;

import model.InputFileRedear;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class HistoryChat extends JFrame {
    JPanel jPanel = new JPanel();

    public HistoryChat() {
        setSize(new Dimension(400, 1000));
        setLayout(null);
        jPanel.setLayout(null);
        add(jPanel);
        jPanel.setBounds(0, 0, 400, 1000);
        showHistory();
        setVisible(true);
    }

    public void showHistory() {
        jPanel.removeAll();
        File inputFile = new File("historyChat");
        ArrayList<String> inputAddr;
        inputAddr = new ArrayList<>();
        String inputDir;
        if (inputFile.isDirectory()) {
            inputDir = inputFile.getName();
            for (String addr : inputFile.list()) {
                inputAddr.add(inputDir + "/" + addr);
            }
        }
        for (int i = 0; i < inputAddr.size(); i++) {
            InputFileRedear inputFileRedear = new InputFileRedear(inputAddr.get(i));
            JLabel log = logs(inputFileRedear.readLog());
            if (230 + i * 10 < 255) log.setBackground(new Color(230 - i * 10, 230 - i * 10, 230 - i * 10));
            log.setOpaque(true);
            log.setBounds(0, i * 50, 400, 50);
            log.setFont(new Font("Georgia", Font.PLAIN, 14));
            jPanel.add(log);
        }
        jPanel.revalidate();
        repaint();
        revalidate();
    }

    private JLabel logs(String json) {
        JSONArray jsonArray = new JSONArray(json);
        JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length() - 1);
        String name = jsonObject.getString("name");
        String message = jsonObject.getString("message");
        String time = jsonObject.getString("time");
        JLabel result = new JLabel(name + " : " + message + " ( " + time + " )");
        result.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showFrame(json);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return result;
    }

    private void showFrame(String json) {
        jPanel.removeAll();
        // create a JEditorPane
        JEditorPane jEditorPane = new JEditorPane();

// make it read-only
        jEditorPane.setEditable(false);

// add a HTMLEditorKit to the editor pane
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);

// now add it to a scroll pane
        JScrollPane scrollPane = new JScrollPane(jEditorPane);


// add some styles to the html
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("p {background:rgb(230,230,230);padding:10px;position:absolute;top:0;" +
                "right:0;width:80%;height:300px;direction:rtl !important;float:right;border:3px solid rgb(60,60,60);border-radius: 50%;}");
        styleSheet.addRule("* {position:relative;padding:0;margin:0}");

// create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        String html = "<html><body>";
        try {
            JSONArray jsonArray = new JSONArray(json);

            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String message = jsonObject.getString("message");
                    String time = jsonObject.getString("time");
                    html += "<div>" +
                            "<p class='animation'>" + name + "<br>" + message + "<br>" + time + "</p>" +
                            "</div>";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        html += "</body></html>";
        jEditorPane.setText(html);
        jPanel.add(scrollPane);
        scrollPane.setBounds(0, 50, 400, 950);
        Button backBtn = new Button("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistory();
            }
        });
        jPanel.add(backBtn);
        backBtn.setBounds(100, 10, 200, 30);
        revalidate();
        repaint();
        jPanel.revalidate();
    }
}
