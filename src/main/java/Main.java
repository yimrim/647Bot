import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        JDA jda = JDABuilder.createDefault("INSERT BOT KEY HERE").build();
        jda.awaitReady();


        Runnable r = new Runnable() {
            @Override
            public void run() {
                Wetter w = null;
                try {
                    w = new Wetter("INSERT LOCATION STRING HERE");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        jda.getPresence().setActivity(Activity.playing("drau\u00dfen bei " + w.getTemp()));
                        System.out.println("updated temp in presence (" + w.getTemp() + ")");
                        TimeUnit.MINUTES.sleep(10);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        //jda.getPresence().setActivity(Activity.playing("an der M-Box bei Raik "));
        jda.addEventListener(new MessageListener());
    }
}
