package bot.discordGolden.listeners;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class voiceListener extends ListenerAdapter {
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {

        EmbedBuilder voiceJoin = new EmbedBuilder();
        voiceJoin.setColor(Color.MAGENTA);
        voiceJoin.setTitle("Voice Log!");
        voiceJoin.setDescription(event.getVoiceState().getMember().getAsMention() + " entrou no channel " + event.getChannelJoined().getName() + ".");
        event.getGuild().getTextChannelsByName("voicelog", true).get(0).sendMessage(voiceJoin.build()).queue();
    }
}
