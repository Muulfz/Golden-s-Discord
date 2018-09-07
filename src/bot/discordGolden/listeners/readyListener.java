package bot.discordGolden.listeners;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class readyListener extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        String out = "\nO bot esta rodando nesses servers: \n";
        for (Guild g : event.getJDA().getGuilds()) {
            out += g.getName() + " ( ID: " + g.getId() + ") \n";
        }

        System.out.println(out);

        for (Guild g : event.getJDA().getGuilds()) {
            EmbedBuilder botStatus = new EmbedBuilder();
            botStatus.setColor(Color.GREEN);
            botStatus.setDescription(g.getMemberById("484061916160065547").getUser().getName() + " is now online!");
//            botStatus.setDescription(event.getUser().getJoinDate().format(DateTimeFormatter.ofPattern("hh:mm " + " dd/MM/yyyy")));
//            botStatus.addField("Bot Status!", bot.getUserById("484061916160065547").getName() + " is now online", false);
            g.getTextChannelsByName("bot-status", true).get(0).sendMessage(botStatus.build()).queue();
        }
    }
}
