package bot.discordGolden.main;

/**
 * GoldenKj 2018
 *
 * @author GoldenKj
 */

import bot.discordGolden.ExpSystem.ExpSystem;
import bot.discordGolden.Util.STATIC;
import bot.discordGolden.commands.*;
import bot.discordGolden.listeners.WelcomeListener;
import bot.discordGolden.listeners.commandListener;
import bot.discordGolden.listeners.readyListener;
import bot.discordGolden.listeners.voiceListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;


public class botMain {
    public static JDABuilder bot;

    public static void main(String[] args) {
        bot = new JDABuilder(AccountType.BOT);

        bot.setToken("NDg0MDYxOTE2MTYwMDY1NTQ3.DnGXEQ.8VprQtuua3uE44aujZ_3kbYFrR0");
        bot.setAutoReconnect(true);
        bot.setStatus(OnlineStatus.ONLINE);

        bot.setGame(new Game("") {
            @Override
            public String getName() {
                return "v." + STATIC.VERSION;
            }

            @Override
            public String getUrl() {
                return null;
            }

            @Override
            public GameType getType() {
                return GameType.DEFAULT;
            }
        });

        // Commands
        addListeners();
        addCommands();

        // ExpSystem, for Xp in Discord.
        ExpSystem system = new ExpSystem();
        system.startTimer();

        try {
            JDA jda = bot.buildBlocking();
        } catch (LoginException | InterruptedException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    public static void addCommands() {

//        commandHandler.commands.put("tempMute", new TempMuteCommand());
        commandHandler.commands.put("online", new OnlineCommand());
        commandHandler.commands.put("hello", new HelloThere());
        commandHandler.commands.put("mute", new MuteCommand());
        commandHandler.commands.put("botinfo", new BotInfo());
        commandHandler.commands.put("meuavatar", new MeuAvatar());
        commandHandler.commands.put("avatar", new Avatar());
        commandHandler.commands.put("clear", new Clear());
        commandHandler.commands.put("help", new help());
        commandHandler.commands.put("ping", new ping());
        commandHandler.commands.put("say", new say());
        commandHandler.commands.put("clima", new Weather());
        commandHandler.commands.put("xp", new Xp());
        commandHandler.commands.put("music", new Music());
        commandHandler.commands.put("m", new Music());
    }

    public static void addListeners() {
        bot.addEventListener(new commandListener());
        bot.addEventListener(new WelcomeListener());
        bot.addEventListener(new readyListener());
        bot.addEventListener(new voiceListener());
        bot.addEventListener(new ExpSystem());
        bot.addEventListener(new BotLeave());
    }
}
