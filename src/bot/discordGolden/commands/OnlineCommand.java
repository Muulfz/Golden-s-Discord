package bot.discordGolden.commands;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.main.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class OnlineCommand extends ListenerAdapter implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (permsCore.check(event) >= 4) {


            int online = 0;
            for (int i = 0; i < event.getGuild().getMembers().size(); i++) {
                if (event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.ONLINE || event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                    online++;
                }
            }

            EmbedBuilder membersOnline = new EmbedBuilder();
            membersOnline.setColor(16772096);
            membersOnline.setTitle("Membros do " + event.getGuild().getName());
            membersOnline.addField("Membros Online: ", online + " Jogadores Online. ", true);
            membersOnline.addField("Total de Membros no " + event.getGuild().getName() + ": ", +event.getGuild().getMembers().size() + " membros no servidor.", false);
            membersOnline.setThumbnail(event.getGuild().getIconUrl());
            event.getChannel().sendMessage(membersOnline.build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!online' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
