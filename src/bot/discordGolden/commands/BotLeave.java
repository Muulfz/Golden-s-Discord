package bot.discordGolden.commands;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class BotLeave extends ListenerAdapter {
    public void onDisconnect(DisconnectEvent event) {

        if (event.getClientCloseFrame().isCloseFrame()) {
            EmbedBuilder botStatus = new EmbedBuilder();
            botStatus.setColor(Color.RED);
            botStatus.setDescription(event.getJDA().getUserById("484061916160065547").getName() + " has been disconnected!");
            botStatus.setDescription(event.getDisconnectTime().format(DateTimeFormatter.ofPattern("EEEE " + "hh:mm a")));
            event.getJDA().getTextChannelById("487029704193212426").sendMessage(botStatus.build()).queue();

        }

    }
}
