package bot.discordGolden.audioSrc;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Member;

public class AudioInfo {

    private final AudioTrack TRACK;
    private final Member AUTHOR;

    /**
     * Erstellt eine Instanz der Klasse AudioInfo.
     * @param track AudioTrack
     * @param author Member, der den Track eingereiht hat
     */
    public AudioInfo(AudioTrack track, Member author) {
        this.TRACK = track;
        this.AUTHOR = author;
    }

    public AudioTrack getTrack() {
        return TRACK;
    }

    public Member getAuthor() {
        return AUTHOR;
    }

}