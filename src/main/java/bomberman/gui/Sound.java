package bomberman.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound {
    private static boolean mute = false;
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
    }

    public Sound(String url) {
        mediaPlayer = new MediaPlayer(new Media(url));
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

    public static void turnOnSound() {
        mute = false;
    }

    public static void turnOffSound() {
        mute = true;
    }

    public boolean isPlaying() {
        return playing;
    }
}