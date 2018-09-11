package bot.discordGolden.commands;

import bot.discordGolden.Util.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.format.DateTimeFormatter;

public class BotInfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder botInfo = new EmbedBuilder();
        botInfo.setColor(16762624);
        botInfo.addField("Meu Nome:", event.getJDA().getUserById("484061916160065547").getName() , false);
        botInfo.addField("Minha versão: ", STATIC.VERSION , false);
        botInfo.addField("Meu desenvolvedor:", event.getGuild().getMemberById("206914118731431937").getAsMention() + " @_zentrynetwork.", false);
        botInfo.addField("Inicio de meu desenvolvimento: ", "Terça-Feira, Setembro 4, 2018 as 9:17:36 PM", false);
        botInfo.addField("Data de entrada:", event.getGuild().getMemberById("484061916160065547").getJoinDate().format(DateTimeFormatter.ofPattern("EEEE " + "hh:mm a " + "dd/MM/yyyy")) , false);
        botInfo.setThumbnail(event.getGuild().getMemberById("484061916160065547").getUser().getAvatarUrl());
        event.getMessage().getChannel().sendMessage(botInfo.build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
