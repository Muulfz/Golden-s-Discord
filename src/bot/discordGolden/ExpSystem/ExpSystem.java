package bot.discordGolden.ExpSystem;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ExpSystem extends ListenerAdapter {

    private HashMap<Member, Integer> playerXp = new HashMap<>();
    private HashMap<Member, Integer> playerTimer = new HashMap<>();

    protected int getPlayerXp(Member member) {
        return playerXp.get(member);
    }

    private void setPlayerXp(Member member, int numXp) {
        playerXp.put(member, numXp);
    }

    private int getPlayerTime(Member member) {
        return playerTimer.get(member);
    }

    protected void setPlayerTime(Member member, int num) {
        playerTimer.put(member, num);
    }

    protected void randXp(Member member) {
        Random r = new Random();
        if(!playerXp.containsKey(member))
            setPlayerXp(member, 0);
        setPlayerXp(member, getPlayerXp(member) + r.nextInt(25));
    }

    protected boolean canGetXp(Member member) {
        return !playerTimer.containsKey(member);
    }

    public void startTimer(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for(Member member : playerTimer.keySet()) {
                    setPlayerTime(member, getPlayerTime(member) - 1);
                    if(getPlayerTime(member) == 0) {
                        playerTimer.remove(member);
                    }
                }
            }
        };
        timer.schedule(task, 1000,1000);
    }
}
