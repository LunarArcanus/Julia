/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia;
import java.io.File;

/**
 *
 * @author 3ynar
 */
public class Julia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String imageLocation;
        final AI ai;
        final GUI gui;
        
        imageLocation = String.join(File.separator,
                ".", "resources", "avatar.jpg");
        ai = new AI("Julia");
        gui = new GUI("Chat with Julia", imageLocation);
        gui.setAgent(ai);
    }
    
}
