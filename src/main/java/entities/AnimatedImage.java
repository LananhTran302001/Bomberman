package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedImage {

    public Image[] frames;
    public double speed;

    public AnimatedImage(Image[] frames, double speed) {
        this.frames = frames;
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image[] getFrames() {
        return frames;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
    }

    public Image getCurrFrame(double time) {
        int index = (int) ((time % (frames.length * speed)) / speed);
        return frames[index];
    }
}
