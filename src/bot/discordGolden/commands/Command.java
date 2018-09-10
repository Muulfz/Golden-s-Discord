package bot.discordGolden.commands;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public interface Command {

    boolean called(String[] args, MessageReceivedEvent event);

    void action(String[] args, MessageReceivedEvent event) throws InvalidAuthTokenException, DataNotFoundException, Exception;

    void executed(boolean success, MessageReceivedEvent event);

    String help();

}