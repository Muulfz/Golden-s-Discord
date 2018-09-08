package bot.discordGolden.commands;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.main.permsCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelloThere implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (permsCore.check(event) >= 6) {

            if (!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("Hello " + event.getMember().getUser().getName() + "!").queue();

            }
        }
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!Hello' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
