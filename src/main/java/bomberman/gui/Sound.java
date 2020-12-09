package bomberman.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Sound {

    private static boolean mute = false;
    private static List<Sound> soundList = new ArrayList<Sound>();
    private MediaPlayer mediaPlayer;
    private boolean playing = false;

    public Sound(String url, boolean repeat) {
        mediaPlayer = new MediaPlayer(new Media(url));
        if (repeat) {
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
        }
        soundList.add(this);
    }

    public Sound(String url) {
        mediaPlayer = new MediaPlayer(new Media(url));
        soundList.add(this);
    }

    public void play() {
        if (!mute && !playing) {
            mediaPlayer.play();
            playing = true;
        }
    }

    public void stop() {
        mediaPlayer.stop();
        playing = false;
    }

    public void pause() {
        mediaPlayer.pause();
        playing = false;
    }

    public static void turnOnSound() {
        mute = false;
    }

    public static void turnOffSound() {
        mute = true;
        stopAll();
    }

    public boolean isPlaying() {
        return playing;
    }

    public static void stopAll() {
        for (Sound s : soundList) {
            s.stop();
        }
    }
}
