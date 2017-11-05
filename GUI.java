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
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JLabel iconLabel;
   private JPanel controlPanel;
   private JOptionPane optionPane;
   private String userName;
   private String imageLocation;
   private ImageIcon image;
   
   public GUI(String title, String imageLocation) {
       initialise(title, imageLocation);
   }
   
   private void initialise(String title, String imageLocation) {
       mainFrame = new JFrame(title);
       mainFrame.setLayout(new GridLayout(3,1));
       this.imageLocation = imageLocation;
       image = new ImageIcon(imageLocation);
       
       userName = JOptionPane.showInputDialog(mainFrame, "Please enter your name");
       System.out.println(userName);
       
       mainFrame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent windowEvent) {
               System.exit(0);
           }
       });

       controlPanel = new JPanel();
       controlPanel.setLayout(new FlowLayout());
       
       
       iconLabel = new JLabel("", image, JLabel.CENTER);
       mainFrame.add(iconLabel);
       //mainFrame.add(headerLabel);
       mainFrame.add(controlPanel);
       //mainFrame.add(statusLabel);
       mainFrame.setVisible(true);
   }
}
