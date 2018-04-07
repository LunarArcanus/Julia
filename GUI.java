package julia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Eynar Roshev
 */
public class GUI extends JApplet implements ActionListener, KeyListener {

    private AI agent;

    private JFrame mainFrame;
    private JFrame responseFrame;
    private JFrame historyFrame;

    private JPanel responsePanel;
    private JPanel historyPanel;

    private JLabel responseLabel;
    private JTextField userText;

    private JButton sayButton;

    private JLabel responseBotLabel;

    public String username;

    private JLabel[] chatHistory;
    private int chatIndex = 0;

    public GUI(String title, AI ai, String imageLocation) {
        initialise(title, ai, imageLocation);
    }

    private void initialise(String title, AI ai, String imageLocation) {
        agent = ai;
        chatHistory = new JLabel[10];
        initAvatarFrame(title, imageLocation);
        assignUsername();
        initResponseFrame();
        initHistoryFrame();
    }

    public void setAgent(AI agent) {
        this.agent = agent;
    }

    private void assignUsername() {
        String name;
        name = JOptionPane.showInputDialog(mainFrame, "Please enter your name");
        if (name == null || name.isEmpty()) {
            username = "Julia";
        } else {
            username = name;
        }
    }

    private void initAvatarFrame(String title, String imageLocation) {
        mainFrame = new JFrame(title);
        JPanel panel = new JPanel();
        JLabel iconLabel;
        ImageIcon image;
        panel.setLayout(new FlowLayout());
        image = new ImageIcon(imageLocation, "Avatar");

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        iconLabel = new JLabel(image, JLabel.CENTER);
        panel.add(iconLabel);
        mainFrame.add(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void initHistoryFrame() {
        historyFrame = new JFrame("History");
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));

        // Populate the chat history slots with JLabels
        for (int i = 0; i < chatHistory.length; i += 2) {
            chatHistory[i] = new JLabel();
            chatHistory[i].setForeground(Color.green);
            chatHistory[i + 1] = new JLabel();
            chatHistory[i + 1].setForeground(Color.blue);
        }

        historyFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        historyFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(historyFrame,
                        "This window cannot"
                        + " be closed. To close the application, close the main"
                        + " window with the avatar.",
                        "Cannot close window", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        historyFrame.add(historyPanel);
        historyFrame.pack();
        historyFrame.setVisible(true);
    }

    private void initResponseFrame() {
        responsePanel = new JPanel();

        responseFrame = new JFrame("Dialogue");
        responseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        responseFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(responseFrame,
                        "This window cannot"
                        + " be closed. To close the application, close the main"
                        + " window with the avatar.",
                        "Cannot close window", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        responsePanel.setLayout(new FlowLayout());

        responseLabel = new JLabel("Enter your text here");
        responseBotLabel = new JLabel();
        responseBotLabel.setForeground(Color.blue);
        userText = new JTextField(50);
        userText.addKeyListener(this);
        sayButton = new JButton("Say");
        sayButton.setBackground(Color.green);
        sayButton.addActionListener(this);

        responsePanel.add(responseLabel);
        responsePanel.add(userText);
        responsePanel.add(sayButton);
        responsePanel.add(responseBotLabel);

        responseFrame.add(responsePanel);
        responseFrame.pack();
        responseFrame.setVisible(true);
    }

    private void advanceHistory() {
        if (chatIndex < 9) {
            chatIndex++;
        } else {
            chatIndex = 0;
        }
    }

    private void updateHistory(String userQuery, String botResponse) {
        // Update the chat history.
        // There are slots, with 5 for user queries and 5 for bot responses
        // which are arranged in pairs.
        chatHistory[chatIndex].setText(username + ": " + userQuery);
        historyPanel.add(chatHistory[chatIndex]);

        advanceHistory();
        chatHistory[chatIndex].setText(agent.getBotName() + ": " + botResponse);
        historyPanel.add(chatHistory[chatIndex]);
        historyFrame.pack();

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(sayButton)) {
            String text = userText.getText();
            String response;
            agent.setQuery(text);
            response = agent.getResponse();
            userText.setText(null);
            responseBotLabel.setText(response);
            responseFrame.pack();
            updateHistory(text, response);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER) {
            sayButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
}
