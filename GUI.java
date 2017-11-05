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
public class GUI {

    private JFrame mainFrame;
    private JFrame responseFrame;
    private JFrame historyFrame;

    private JLabel iconLabel;

    private JOptionPane optionPane;
    private String username;
    private String imageLocation;
    private ImageIcon image;

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
        System.out.println(this.username);
    }

    private void initAvatarFrame(String title, String imageLocation) {
        mainFrame = new JFrame(title);
        mainFrame.setLayout(new FlowLayout());
        this.imageLocation = imageLocation;
        image = new ImageIcon(imageLocation, "Avatar");

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        iconLabel = new JLabel(null, image, JLabel.CENTER);
        mainFrame.add(iconLabel);
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
        historyFrame.setSize(200, 200);
        historyFrame.setVisible(true);
    }

    private void initResponseFrame() {
        JLabel botResponse;
        JTextField userText;

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
        responseFrame.setSize(200, 200);
        responseFrame.setVisible(true);
    }
}
