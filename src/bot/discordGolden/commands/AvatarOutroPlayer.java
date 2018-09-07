package bot.discordGolden.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AvatarOutroPlayer implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        SimpleDateFormat adf = new SimpleDateFormat("EEEE " + "hh:mm a");
        Date date = new Date(System.currentTimeMillis());
        Member target = event.getMember();
        if (event.getMessage().getMentionedMembers().size() > 0) {
            target = event.getMessage().getMentionedMembers().get(0);
        }
        EmbedBuilder Avatar = new EmbedBuilder();
        if (event.getMessage().getMentionedRoles().size() > 0 || event.getMessage().getMentionedChannels().size() > 0) {
            Avatar.setColor(Color.RED);
            Avatar.setTitle("Erro");
            Avatar.setDescription("Groupos e Salas não tem avatar");
            event.getChannel().sendMessage(Avatar.build()).queue();
        } else {
            Avatar.setColor(Color.CYAN);
            Avatar.setTitle("Avatar");
            Avatar.setImage(target.getUser().getAvatarUrl());
            Avatar.setFooter("Pedido por: " + event.getAuthor().getName() + "  " + adf.format(date), event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendMessage(Avatar.build()).queue();
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!avatar' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
