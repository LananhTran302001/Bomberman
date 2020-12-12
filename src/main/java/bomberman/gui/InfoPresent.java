package bomberman.gui;

import bomberman.constants.GameImages;
import bomberman.entities.tiles.items.*;
import bomberman.scenes.GameScene;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoPresent {
    int points;
    VBox background = new VBox(10);
    Label gameLevel = new Label();
    Label playerLives = new Label();
    Label playerPoints = new Label();

    ItemBar blockPassBar = new ItemBar(GameImages.ITEM_BLOCK_PASS_IMG);
    ItemBar fireSuitBar = new ItemBar(GameImages.ITEM_FIRE_SUIT_IMG);
    ItemBar fireUpBar = new ItemBar(GameImages.ITEM_FIRE_UP_IMG);
    ItemBar speedUpBar = new ItemBar(GameImages.ITEM_SPEED_UP_IMG);

    public InfoPresent() {
        background.setPadding(new Insets(10, 10, 10, 30));
        background.getChildren().addAll(gameLevel, playerLives, playerPoints,
                blockPassBar.getView(), fireSuitBar.getView(),
                fireUpBar.getView(), speedUpBar.getView());
        gameLevel.setText("Level " + GameScene.getLevel());
        playerPoints.setText("Points: 0");
        points = 0;
    }

    public VBox getView() {
        return background;
    }

    public void addPoints(int value) {
        points += value;
        playerPoints.setText("Points: " + points);
    }

    public void reset() {
        points = 0;
        blockPassBar.reset();
        fireUpBar.reset();
        fireSuitBar.reset();
        speedUpBar.reset();
    }

    public int getPoints() {
        return points;
    }

    public void update() {
        blockPassBar.update();
        fireUpBar.update();
        fireSuitBar.update();
        speedUpBar.update();
        playerLives.setText("LEFT: " + GameScene.getPlayer().getLives());
    }

    public void setLevel(int level) {
        gameLevel.setText("Level " + level);
    }

    public void addNewItem(Item i) {
        if (i instanceof BlockPass) {
            blockPassBar.addNewItem();
        } else if (i instanceof FireSuit) {
            fireSuitBar.addNewItem();
        } else if (i instanceof FireUp) {
            fireUpBar.addNewItem();
        } else if (i instanceof SpeedUp){
            speedUpBar.addNewItem();
        }
    }

}
