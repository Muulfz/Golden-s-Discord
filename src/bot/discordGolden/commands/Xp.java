package bot.discordGolden.commands;

import bot.discordGolden.ExpSystem.ExpSystem;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Xp extends ExpSystem implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getChannel().sendMessage("You have " + getPlayerXp(event.getMember()) + "xp").queue();

        if(canGetXp(event.getMember())) {
            randXp(event.getMember());
            setPlayerTime(event.getMember(), 3);
        }
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!xp' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
