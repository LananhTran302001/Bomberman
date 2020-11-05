package bomberman.animation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AnimatedImage {

    public List<Image> frames = new ArrayList<Image>();
    public int speed;
    private ImageView imageView = new ImageView();
    private Transition animation = new Transition() {
        {
            setCycleCount(speed);
        }
        protected void interpolate(double frac) {
            imageView.setImage(getCurrFrame(frac));
        }
    };

    public AnimatedImage(List<String> urls, int speed) {
        this.speed = speed;
        for (String imgPath : urls) {
            frames.add(new Image(imgPath));
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Image> getFrames() {
        return frames;
    }

    public void setFrames(List<Image> frames) {
        this.frames = frames;
    }

    public Image getCurrFrame(double time) {
        // speed = cycles of playing per second
        int index = (int) ((time % (frames.size() / speed)) * speed);
        return frames.get(index);
    }

    public void play() {
        animation.play();
    }
}
