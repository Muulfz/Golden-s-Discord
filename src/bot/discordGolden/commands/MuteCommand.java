package bot.discordGolden.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MuteCommand extends ListenerAdapter implements Command {
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder error = new EmbedBuilder();
        error.setTitle("Uso Inválido!");
        error.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        error.setColor(Color.RED);
        error.setDescription("**Uso Correto:** !mute {@user} {reason}");
        channel.sendMessage(error.build()).complete().delete().queueAfter(11, TimeUnit.SECONDS);
    }

    public void log(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat adf = new SimpleDateFormat("EEEE " + "hh:mm a");
        SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Mute Reporte");
        log.setColor(Color.decode("#0652DD"));
        log.addField("Usuário Mutado", muted.getAsMention(), false);
        log.addField("Por", muter.getAsMention(), false);
        log.addField("Razão", reason, false);
        log.addField("Data", stf.format(date), false);
        log.addField("Quando", adf.format(date), false);
        channel.sendMessage(log.build()).queue();

    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args.length <= 1) {
            sendErrorMessage((TextChannel) event.getChannel(), event.getMember());
        } else {
            Member target = event.getMessage().getMentionedMembers().get(0);
            Role Mutado = event.getGuild().getRolesByName("Mutado", true).get(0);

            event.getGuild().getController().addSingleRoleToMember(target, Mutado).queue();

            if(args.length >= 3) {
                String reason = "";
                for(int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                log(target, event.getMember(), reason, event.getGuild().getTextChannelById("484773329849417738"));
            }else {
                log(target, event.getMember(), "", event.getGuild().getTextChannelById("484773329849417738"));
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
