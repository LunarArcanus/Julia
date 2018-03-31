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

    private JLabel userHistoryResponse;
    private JLabel botHistoryResponse;

    private JLabel botResponse;
    private JTextField userText;

    private JButton sayButton;

    public String username;

    public GUI(String title, AI ai, String imageLocation) {
        initialise(title, ai, imageLocation);
    }

    private void initialise(String title, AI ai, String imageLocation) {
        agent = ai;
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
        getContentPane().add(panel);
        mainFrame.add(panel);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }

    private void initHistoryFrame() {
        historyFrame = new JFrame("History");
        JPanel panel = new JPanel();
        userHistoryResponse = new JLabel();
        botHistoryResponse = new JLabel();
        JLabel userLabel = new JLabel(username);
        JLabel botLabel = new JLabel(agent.getBotName());
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
        
        panel.add(userLabel);
        panel.add(userHistoryResponse);
        panel.add(botLabel);
        panel.add(botHistoryResponse);
        getContentPane().add(panel);
        historyFrame.add(panel);
        historyFrame.setSize(200, 600);
        historyFrame.setVisible(true);
    }

    private void initResponseFrame() {
        JPanel panel = new JPanel();

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

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        botResponse = new JLabel("Enter your text here", JLabel.CENTER);
        userText = new JTextField();
        userText.addKeyListener(this);
        sayButton = new JButton("Say");
        sayButton.setBackground(Color.green);
        sayButton.addActionListener(this);

        panel.add(botResponse);
        panel.add(userText);
        panel.add(sayButton);

        getContentPane().add(panel);
        responseFrame.add(panel);
        responseFrame.setSize(600, 200);
        responseFrame.setVisible(true);
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
            System.out.println(response);
            userHistoryResponse.setText(text);
            botHistoryResponse.setText(response);
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
