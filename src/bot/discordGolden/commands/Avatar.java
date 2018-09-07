package bot.discordGolden.commands;

import com.kitfox.svg.A;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class Avatar implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder Avatar = new EmbedBuilder();
        Avatar.setColor(Color.CYAN);
        Avatar.setTitle("Seu Avatar");
        Avatar.setImage(event.getMember().getUser().getAvatarUrl());
        Avatar.setFooter("Pedido por:" + event.getAuthor().getName(), event.getMember().getUser().getAvatarUrl());
        event.getChannel().sendMessage(Avatar.build()).queue();
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
