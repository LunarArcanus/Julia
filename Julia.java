package julia;
import java.io.File;

/**
 *
 * @author Eynar Roshev
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
