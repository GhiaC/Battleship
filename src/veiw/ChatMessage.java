package veiw;

import javax.swing.*;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatMessage extends JLabel {
    private static ChatViewerPanel chatViewerPanel;

    public static void setChatViewerPanel(ChatViewerPanel chatViewerPanel) {
        ChatMessage.chatViewerPanel = chatViewerPanel;
    }
}
