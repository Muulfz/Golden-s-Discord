package bot.discordGolden.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Perfil implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder Perfil = new EmbedBuilder();
        Perfil.setColor(Color.CYAN);
        Perfil.setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
        Perfil.setThumbnail(event.getAuthor().getAvatarUrl());
        Perfil.setTitle("Informaçoes");
        Perfil.setDescription("**ID:** " + event.getMember().getUser().getId() + "\n");
        Perfil.appendDescription("**Data de entrada:** " + event.getMember().getJoinDate().format(DateTimeFormatter.ofPattern(" hh/MM/yyyy " + "hh:mm a")) + "\n\n");
        if (event.getMember().getOnlineStatus() == OnlineStatus.ONLINE) {
            Perfil.appendDescription("**Status:** Online." + "\n");
        } else if (event.getMember().getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
            Perfil.appendDescription("**Status:** Ocupado." + "\n");
        } else if (event.getMember().getOnlineStatus() == OnlineStatus.IDLE) {
            Perfil.appendDescription("**Status:** Ausente." + "\n");
        } else if (event.getMember().getOnlineStatus() == OnlineStatus.INVISIBLE || event.getMember().getOnlineStatus() == OnlineStatus.OFFLINE) {
            Perfil.appendDescription("**Status:** Invisivel." + "\n");
        } else if (event.getMember().getOnlineStatus() == OnlineStatus.UNKNOWN) {
            Perfil.appendDescription("**Status:** Desconhecido." + "\n");
        }
        if (event.getMember().getNickname() == null) {
            Perfil.appendDescription("**Apelido:** " + event.getAuthor().getName() + " não possui um apelido." + "\n");
        } else {
            Perfil.appendDescription("**Apelido:** " + event.getMember().getNickname() + "\n");
        }
        Perfil.appendDescription("**Quantidade de cargos:** " + event.getMember().getRoles().size() + "\n");
        if (event.getMember().getOnlineStatus() == OnlineStatus.INVISIBLE || event.getMember().getOnlineStatus() == OnlineStatus.OFFLINE) {
            Perfil.appendDescription("**Atividade:** Usuário não está em nenhuma atividade." + "\n\n");
        } else if(event.getMember().getGame() == null){
            Perfil.appendDescription("**Atividade:** Usuário não está em nenhuma atividade." + "\n\n");
        } else {
            Perfil.appendDescription("**Atividade:** " + event.getMember().getGame() + "." + "\n\n");
        }
        Perfil.appendDescription("**Cargos:** ");
        for(int i = 0; i < event.getMember().getRoles().size(); i++){
            Perfil.appendDescription( "`" + event.getMember().getRoles().get(i).getName() + "`" + "\n");
        }
        Perfil.setFooter("Pedido por: " + event.getAuthor().getName() + "  -  " +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE" + " hh:mm a ")), event.getMember().getUser().getAvatarUrl());
        event.getChannel().sendMessage(Perfil.build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!perfil' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
