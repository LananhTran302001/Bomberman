package bomberman.gui;

import bomberman.constants.GameConstants;
import bomberman.entities.tiles.items.Item;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Date;

public class ItemBar {
    HBox view = new HBox(10);
    ImageView imageView = new ImageView();
    ProgressBar bar = new ProgressBar();
    Date startTime;
    final double period = GameConstants.ITEM_POWER_TIME;

    public ItemBar() {
        view.getChildren().addAll(imageView, bar);
        bar.setProgress(0);
        startTime = null;
    }

    public ItemBar(String itemImg) {
        this();
        imageView.setImage(new Image(itemImg));
    }

    public void addNewItem() {
        startTime = new Date();
    }

    public HBox getView() {
        return view;
    }

    public void update() {
        if (startTime != null) {
            double percent = (new Date().getTime() - startTime.getTime()) / period;
            if (percent < 1) {
                bar.setProgress(percent);
            } else {
                startTime = null;
                bar.setProgress(1);
            }
        }
    }
}
