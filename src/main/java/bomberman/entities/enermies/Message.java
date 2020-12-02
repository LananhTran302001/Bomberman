package bomberman.entities.enermies;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import javafx.scene.paint.Color;

import java.util.Date;

public class Message extends Entity {
    protected String message;
    protected Color color;
    protected int textSize;
    protected int duration;
    private Date startTimer;

    public Message(Vector2 position, String message, int duration, int textSize, Color color) {
        super(position);
        this.message = message;
        this.textSize = textSize;
        this.color = color;
        this.duration = duration;
        init();
    }

    public Message(Vector2 position, String message) {
        super(position);
        this.message = message;
        this.color = Color.WHITE;
        this.textSize = 14;
        this.duration = GameConstants.MESSAGE_TIME;
        init();
    }

    public String getName() {
        return "message";
    }

    public void init() {
        setLayer(GameConstants.MESSAGE_LAYER);
        startTimer = new Date();
    }

    public void draw() {
        Renderer.renderString(this);
    }

    public void update() {

    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public boolean notUsed() {
        return new Date().getTime() - startTimer.getTime() > duration;
    }

    public Color getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
