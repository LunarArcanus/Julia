/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package julia;

import java.io.File;
import org.alicebot.ab.*;

/**
 *
 * @author 3ynar
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
}
