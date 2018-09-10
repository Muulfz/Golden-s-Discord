package bot.discordGolden.listeners;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.Util.STATIC;
import bot.discordGolden.main.commandHandler;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class commandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().startsWith(STATIC.PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            try {
                commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw(), event));
            } catch (InvalidAuthTokenException e) {
                e.printStackTrace();
            } catch (DataNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}