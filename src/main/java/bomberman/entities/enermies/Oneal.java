package bomberman.entities.enermies;

import bomberman.animation.OnealAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Perceptive;
import bomberman.entities.Vector2;
import bomberman.scenes.EasyLevel;


public class Oneal extends Enemy implements Perceptive {

    private final int radiusView = 4;
    private Vector2 directionVector;

    /**
     * constructors.
     */
    public Oneal(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Oneal(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Oneal";
    }

    public void init() {
        directionVector = findDirection(radiusView);
    }

    public void update() {
        move();
    }

    private boolean checkCollision(Vector2 p) {
        int i = p.getY() / GameConstants.TILE_SIZE;
        int j = p.getX() / GameConstants.TILE_SIZE;
        if (directionVector.getX() > 0) {
            j++;
        }
        if (directionVector.getY() > 0) {
            i++;
        }
        return EasyLevel.getStaticMapAt(i, j) != ' ';
    }

    public int countProfit(int i, int j) {
        int profit = 0;
        switch (EasyLevel.getStaticMapAt(i, j)) {
            case ' ':
                profit++;
                break;

            case 'B':
                profit -= 4;
                break;

            default:
                profit--;
        }

        Vector2 playerPosition = EasyLevel.getPlayer().getPosition();
        if (playerPosition.getY() / GameConstants.TILE_SIZE == i
                && playerPosition.getX() / GameConstants.TILE_SIZE == j) {
            profit += 3;
        }

        return profit;
    }

    public Vector2 findDirection(int radiusArea) {
        int mapX = position.getX() / GameConstants.TILE_SIZE;
        int mapY = position.getY() / GameConstants.TILE_SIZE;

        // RIGHT
        int profitRight = 0;
        int i = 1;
        if (EasyLevel.getStaticMapAt(mapY, mapX + i) != ' ') {
            profitRight -= 100;
        }
        while (i < radiusArea && mapX + i < GameConstants.NUM_OF_COLUMNS) {
            profitRight += countProfit(mapY, mapX + i);
            i++;
        }

        // LEFT
        int profitLeft = 0;
        i = 1;
        if (EasyLevel.getStaticMapAt(mapY, mapX - i) != ' ') {
            profitRight -= 100;
        }
        while (i < radiusArea && mapX - i >= 0) {
            profitLeft += countProfit(mapY, mapX - i);
            i++;
        }

        // UP
        int profitUp = 0;
        i = 1;
        if (EasyLevel.getStaticMapAt(mapY - i, mapX) != ' ') {
            profitRight -= 100;
        }
        while (i < radiusArea && mapY - i >= 0) {
            profitUp += countProfit(mapY - i, mapX);
            i++;
        }

        // DOWN
        int profitDown = 0;
        i = 1;
        if (EasyLevel.getStaticMapAt(mapY + i, mapX) != ' ') {
            profitRight -= 100;
        }
        while (i < radiusArea && mapY + i < GameConstants.NUM_OF_ROWS) {
            profitDown += countProfit(mapY + i, mapX);
            i++;
        }

        int max = profitUp;
        Vector2 d = new Vector2(0, -1);
        setCurrentAnimation(OnealAnimation.getMoveLeft());

        if (max < profitDown) {
            max = profitDown;
            d = new Vector2(0, 1);
            setCurrentAnimation(OnealAnimation.getMoveRight());
        }

        if (max < profitLeft) {
            max = profitLeft;
            d = new Vector2(-1, 0);
            setCurrentAnimation(OnealAnimation.getMoveLeft());
        }

        if (max < profitRight) {
            max = profitRight;
            d = new Vector2(1, 0);
            setCurrentAnimation(OnealAnimation.getMoveRight());
        }

        return d;
    }


    public void move() {
        Vector2 newPosition = new Vector2(position).add(directionVector);
        if (!checkCollision(newPosition)) {
            this.setPosition(newPosition);
        } else {
            directionVector = findDirection(radiusView);
        }

    }

}
