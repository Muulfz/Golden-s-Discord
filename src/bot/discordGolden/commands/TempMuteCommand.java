package bot.discordGolden.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TempMuteCommand implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length <= 2) {
            sendErrorMessage(event.getChannel(), event.getMember());
        } else {
            Member target = event.getMessage().getMentionedMembers().get(0);
            tempmute(target, parseTimeAmount(args[2]), parseTimeUnit(args[2]));
            if (args.length >= 4) {
                String reason = "";
                for (int i = 3; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                log(target, event.getMember(), reason, event.getGuild().getTextChannelById("484773329849417738"));
            } else {
                log(target, event.getMember(), "", event.getGuild().getTextChannelById("484773329849417738"));
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!tempMute' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }


    int counter = 0;

    public void sendErrorMessage(MessageChannel channel, Member member) {
        EmbedBuilder error = new EmbedBuilder();
        error.setTitle("Uso Inválido!");
        error.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        error.setColor(Color.RED);
        error.setDescription("**Uso Correto:** !tempMute {@user} {time (ex: 48s ou m)} {reason}");
        channel.sendMessage(error.build()).complete().delete().queueAfter(11, TimeUnit.SECONDS);
    }

    public void log(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat adf = new SimpleDateFormat("EEEE " + "hh:mm a");
        SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Mute Reporte ");
        log.setColor(Color.decode("#0652DD"));
        log.addField("Usuário Mutado", muted.getAsMention(), false);
        log.addField("Por", muter.getAsMention(), false);
        log.addField("Razão", reason, false);
        log.addField("Data", stf.format(date), false);
        log.addField("Quando", adf.format(date), false);
        channel.sendMessage(log.build()).queue();
    }

    private int parseTimeAmount(String time) {
        //12h = 12 hours
        TimeUnit unit = TimeUnit.SECONDS;
        char[] t = time.toCharArray();
        int breakPoint = 0;
        String amount = "";
        int parsedAmount = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 's' || t[i] == 'S') {
                unit = TimeUnit.SECONDS;
                breakPoint = i;
                break;
            } else if (t[i] == 'm' || t[i] == 'M') {
                unit = TimeUnit.MINUTES;
                breakPoint = i;
                break;
            }
        }

        for (int i = 0; i < breakPoint; i++) {
            amount += t[i];
        }
        parsedAmount = Integer.parseInt(amount);
        return parsedAmount;
    }

    private TimeUnit parseTimeUnit(String time) {
        //12h = 12 hours
        TimeUnit unit = TimeUnit.SECONDS;
        char[] t = time.toCharArray();
        int breakPoint = 0;
        String amount = "";
        int parsedAmount = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 's' || t[i] == 'S') {
                unit = TimeUnit.SECONDS;
                breakPoint = i;
                break;
            } else if (t[i] == 'm' || t[i] == 'M') {
                unit = TimeUnit.MINUTES;
                breakPoint = i;
                break;
            }
        }
        return unit;
    }

    private void tempmute(Member target, int time, TimeUnit unit) {
        Role Mutado = target.getGuild().getRolesByName("Mutado", true).get(0);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;
                target.getGuild().getController().addSingleRoleToMember(target, Mutado).queue();
                if (counter == 2) {
                    target.getGuild().getController().removeSingleRoleFromMember(target, Mutado).queue();
                    this.cancel();
                }
            }
        };
        switch (unit) {
            case SECONDS:
                timer.schedule(task, 0, time * 1000);
                break;

            case MINUTES:
                timer.schedule(task, 0, (time * 1000) * 60);
                break;
        }
    }


}
