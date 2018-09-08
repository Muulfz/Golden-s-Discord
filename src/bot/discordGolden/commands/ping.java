package bot.discordGolden.commands;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.main.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class ping implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (permsCore.check(event) >= 6) {

            if (!event.getMember().getUser().isBot()) {
                EmbedBuilder builder = new EmbedBuilder();
                if (event.getMember().getJDA().getPing() > 200) {
                    builder.setColor(Color.RED);
                } else if (event.getMember().getJDA().getPing() >= 90 && event.getMember().getJDA().getPing() <= 200) {
                    builder.setColor(Color.ORANGE);
                } else if (event.getMember().getJDA().getPing() <= 89) {
                    builder.setColor(Color.GREEN);
                }
                builder.addField("Ping: ", +event.getMember().getJDA().getPing() + " ms", true);
                event.getChannel().sendMessage(builder.build()).queue();
            }
        }
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!ping' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
