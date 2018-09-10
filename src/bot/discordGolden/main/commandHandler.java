package bot.discordGolden.main;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.commands.Command;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;

import java.util.HashMap;

public class commandHandler {
    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static void handleCommand(commandParser.commandContainer cmd) throws Exception {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}