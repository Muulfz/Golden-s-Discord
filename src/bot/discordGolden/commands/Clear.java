package bot.discordGolden.commands;

import bot.discordGolden.main.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clear implements Command {

    EmbedBuilder error = new EmbedBuilder().setColor(Color.RED);

    private int getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (permsCore.check(event) >= 4) {
            if (args.length < 1) {
                event.getTextChannel().sendMessage(
                        error.setDescription("Argumentos faltando!").build()
                ).queue();
                return;
            }
            int numb = 0;
            try {
                numb = getInt(args[0]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                event.getTextChannel().sendMessage(
                        error.setDescription("Please enter a number  valid of messages you want to delete!").build()
                ).queue();
            }
            if (numb > 1 && numb <= 100) {

                try {

                    MessageHistory history = new MessageHistory(event.getTextChannel());
                    List<Message> msgs;

                    event.getMessage().delete().queue();

                    msgs = history.retrievePast(numb).complete();
                    event.getTextChannel().deleteMessages(msgs).queue();

                    Message msg = event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(10366060).setDescription("Foram " + args[0] + " menssagens deletadas!").build()
                    ).complete();

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            msg.delete().queue();
                        }
                    }, 3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                event.getTextChannel().sendMessage(
                        error.setDescription("Por favor escolha um numero entre 2 a 100!").build()
                ).queue();
            }
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!clear' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}

