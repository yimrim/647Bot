import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        MessageChannel messageChannel = event.getChannel();
        Message message = event.getMessage();
        String msg = message.getContentRaw();
        System.out.println(msg);
        if (msg.equals("Test") || msg.equals("test")) {
            messageChannel.sendMessage("bin da!").queue();
        }

    }
}