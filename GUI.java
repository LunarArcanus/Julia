/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author 3ynar
 */
public class GUI extends JApplet implements ActionListener {

    private JFrame mainFrame;
    private JFrame responseFrame;
    private JFrame historyFrame;
    
    private JButton sayButton;

    public String username;

    public GUI(String title, String imageLocation) {
        initialise(title, imageLocation);
    }

    private void initialise(String title, String imageLocation) {
        initAvatarFrame(title, imageLocation);
        assignUsername();
        initResponseFrame();
        initHistoryFrame();
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
        JLabel iconLabel;
        ImageIcon image;
        mainFrame.setLayout(new FlowLayout());
        image = new ImageIcon(imageLocation, "Avatar");

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        iconLabel = new JLabel(image, JLabel.CENTER);
        mainFrame.add(iconLabel);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }

    private void initHistoryFrame() {
        JLabel userResponse;
        JLabel botResponse;

        historyFrame = new JFrame("History");
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
        historyFrame.setSize(200, 600);
        historyFrame.setVisible(true);
    }

    private void initResponseFrame() {
        JLabel botResponse;
        JTextField userText;
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
        
        botResponse = new JLabel("foo", JLabel.CENTER);
        userText = new JTextField();
        sayButton = new JButton("Say");
        sayButton.setBackground(Color.green);
        sayButton.addActionListener(this);
        
        panel.add(botResponse);
        panel.add(userText);
        panel.add(sayButton);
        
        responseFrame.add(panel);
        responseFrame.setSize(600, 200);
        responseFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
       if (source == sayButton) {
           System.out.println("foo");
       } 
    }
}
