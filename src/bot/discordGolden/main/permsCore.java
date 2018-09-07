package bot.discordGolden.main;

import bot.discordGolden.Util.STATIC;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */
public class permsCore {

    public static int check(MessageReceivedEvent event) {

        for (Role r : event.getGuild().getMember(event.getAuthor()).getRoles()) {
            if (Arrays.stream(STATIC.ALTONIVEL).parallel().anyMatch(r.getName()::contains)) {
                return 8;
            } else if (Arrays.stream(STATIC.PERMS).parallel().anyMatch(r.getName()::contains)) {
                return 7;
            } else if (Arrays.stream(STATIC.DEVELOPERS).parallel().anyMatch(r.getName()::contains)) {
                return 6;
            } else if (Arrays.stream(STATIC.STAFF).parallel().anyMatch(r.getName()::contains)) {
                return 5;
            } else if (Arrays.stream(STATIC.HELPERS).parallel().anyMatch(r.getName()::contains)) {
                return 4;
            } else if (Arrays.stream(STATIC.DEVS).parallel().anyMatch(r.getName()::contains)) {
                return 3;
            } else if (Arrays.stream(STATIC.MEMBROS).parallel().anyMatch(r.getName()::contains)) {
                return 2;
            } else if (Arrays.stream(STATIC.Outros).parallel().anyMatch(r.getName()::contains)) {
                return 1;
            } else {
                event.getTextChannel().sendMessage("Desculpe, " + event.getAuthor().getAsMention() + ", voce não tem permissão para usar este comando!").queue();
            }
        }
        return 0;
    }

}