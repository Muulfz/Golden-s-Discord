package bot.discordGolden.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class help implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        SimpleDateFormat adf = new SimpleDateFormat("EEEE " + "hh:mm a");
        Date date = new Date(System.currentTimeMillis());

        EmbedBuilder help = new EmbedBuilder();
        help.setColor(Color.decode("#3ffff5"));
        help.setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
        help.setTitle("Help!");

        // Comandos Utilitarios
        help.setDescription("**Utilitários**" + "\n" + "\n");
        help.appendDescription("`!avatar` - Mostra o seu avatar" + "\n");
        help.appendDescription("`!botinfo` - Mostra infomações sobre o " + event.getGuild().getMemberById("484061916160065547").getUser().getName() + "\n");
        help.appendDescription("`!perfil` - Mostra seu perfil." + "\n");
        help.appendDescription("`!clear` - !clear {numero}, o numero será o tanto de mensagens que irão ser apagadas. " + "\n");
        help.appendDescription("`!hello` - Diz Hello {@User} para você." + "\n");
        help.appendDescription("`!clima` - !clima {@cidade}, mostra o clima da cidade." + "\n");

        // Music bot comandos
        help.appendDescription("\n" + "**Music Bot**" + "\n" + "\n");
        help.appendDescription("`!music play` - !music play {URL}" + "\n");
        help.appendDescription("`!music stop` - !music stop, ele para de tocar a musica." + "\n");
        help.appendDescription("`!music queue` - Bot irá mostra a Queue." + "\n");
        help.appendDescription("`!music shuffle` - Bot embaralha a Queue." + "\n");
        help.appendDescription("`!music skip` - Pula a musica que está tocando." + "\n");
        help.appendDescription("`!music now` - Musica que está tocando no momento." + "\n");
        help.appendDescription("Outra alternativa você pode usar !m play ou !m p e !m skip ou !m s" + "\n");

        // Comandos de Admins e Staff
        help.appendDescription("\n" +"**Comandos de Admins & Staffs**" + "\n" + "\n");
        help.appendDescription("`!mute` - !mute {@user}, para mutar o usuário." + "\n");
        help.appendDescription("`!online` - Quantos player tem, e quantos estão online no server." + "\n");
        help.appendDescription("`!ping` - Mostra o ping" + "\n");
        help.appendDescription("`!say` - !say {mensagem}, Bot fala por você." + "\n");
        help.appendDescription("`!tempMute` - !tempMute {@user} {time (ex: 48s ou m)} {reason}" + "\n");
        help.appendDescription("`!xp` - `Em Manutenção`" + "\n");

        // Footer do !Help
        help.setFooter(event.getGuild().getMemberById("484061916160065547").getUser().getName() + " • " + adf.format(date), event.getGuild().getMemberById("484061916160065547").getUser().getAvatarUrl());

        // Mensagem que é enviada.
        event.getChannel().sendMessage(help.build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!help' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}
