package bot.discordGolden.listeners;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.time.format.DateTimeFormatter;

public class WelcomeListener extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        EmbedBuilder welcomeJoin = new EmbedBuilder();
        welcomeJoin.setColor(256);
        welcomeJoin.addField("Player: ", "O Jogador " + event.getMember().getUser().getName() + " entrou!! No " + event.getGuild().getName(), true);
        welcomeJoin.addField("Quando: ", "Em: " + event.getMember().getJoinDate().format(DateTimeFormatter.ofPattern(" hh:mm:ss " + "dd/MM/yyyy")) + " ", false);
        event.getGuild().getTextChannelById("484516643251290114").sendMessage(welcomeJoin.build()).queue();
    }
}
