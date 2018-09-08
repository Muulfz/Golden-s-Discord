package bot.discordGolden.commands;

import bot.discordGolden.main.permsCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class say implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (permsCore.check(event) >= 4) {

            List argsList = Arrays.asList(args);
            StringBuilder sb = new StringBuilder();
            argsList.forEach(s -> sb.append(s + " "));
            send(sb.toString(), event);
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!say' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }

    private void send(String msg, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(msg).queue();
    }
}
