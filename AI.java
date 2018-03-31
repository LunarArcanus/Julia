package julia;

import java.io.File;
import org.alicebot.ab.*;

/**
 *
 * @author Eynar Roshev
 */
public class AI {
    private String botName;
    private String path;
    private String query;
    private Bot bot;
    private Chat session;
    
    public AI(String name) {
        botName = name;
        path = "." + File.separator + botName;

        bot = new Bot(botName, path);
        session = new Chat(bot);
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    public String getResponse() {
        return this.session.multisentenceRespond(this.query);
    }
    
    public String getBotName() {
        return botName;
    }
    
    public void setBotName(String name) {
        botName = name;
    }
}
