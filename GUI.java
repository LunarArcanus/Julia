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
   private JPanel controlPanel;
   private JOptionPane optionPane;
   private String userName;
   
   public GUI(String title) {
       initialise(title);
   }
   
   private void initialise(String title) {
       mainFrame = new JFrame(title);
       mainFrame.setLayout(new GridLayout(3,1));
       
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

       //mainFrame.add(headerLabel);
       mainFrame.add(controlPanel);
       //mainFrame.add(statusLabel);
       mainFrame.setVisible(true);
   }
}
