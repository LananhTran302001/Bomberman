package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.animation.FlameAnimation;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.scenes.EasyLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    Date startDate;
    int range;
    private boolean exploding = false;
    private List<Flame> bombFlame = new ArrayList<Flame>();

    protected enum STATE {
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(Vector2 position) {
        super(position);
        startDate = new Date();
    }

    public Bomb(int x, int y) {
        super(x, y);
        startDate = new Date();
    }

    public String getName() {
        return "Bomb";
    }

    public void setRange(int a) {
        range = a;
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public void explode() {
        if (!exploding) {
            int i = position.getY() / GameConstants.TILE_SIZE;
            int j = position.getX() / GameConstants.TILE_SIZE;

            // CENTER FLAME
            bombFlame.add(new Flame(FlameAnimation.getCenterFlame(), position));
            //Renderer.playAnimation(FlameAnimation.getCenterFlame(), 4, position, size);

            // RIGHT FLAME
            int k = 1;
            while (k < range && (j + k + 1 < GameConstants.NUM_OF_COLUMNS) && EasyLevel.getStaticMapAt(i, j + k + 1) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getHorizontalFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getHorizontalFlame(), 4,
                    new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE), size);*/
                k++;
            }
            if (EasyLevel.getStaticMapAt(i, j + k) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getRightFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getRightFlame(), 4,
                    new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE), size);*/
            }

            // LEFT FLAME
            k = 1;
            while (k < range && (j - k > 1) && EasyLevel.getStaticMapAt(i, j - k - 1) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getHorizontalFlame(), new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getHorizontalFlame(), 4,
                    new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE), size);*/
                k++;
            }
            if (EasyLevel.getStaticMapAt(i, j - k) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getLeftFlame(), new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getLeftFlame(), 4,
                    new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE), size);*/
            }

            // BOTTOM FLAME
            k = 1;
            while (k < range && (j + k + 1 < GameConstants.NUM_OF_ROWS) && EasyLevel.getStaticMapAt(i + k + 1, j) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getVerticalFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getVerticalFlame(), 4,
                    new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE), size);*/
                k++;
            }
            if (EasyLevel.getStaticMapAt(i + k, j) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getBottomFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getBottomFlame(), 4,
                    new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE), size);*/
            }

            // TOP FLAME
            k = 1;
            while (k < range && (i - k > 1) && EasyLevel.getStaticMapAt(i - k - 1, j) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getVerticalFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getVerticalFlame(), 4,
                    new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE), size);*/
                k++;
            }
            if (EasyLevel.getStaticMapAt(i - k, j) != '#') {
                bombFlame.add(new Flame(FlameAnimation.getTopFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE)));
            /*Renderer.playAnimation(FlameAnimation.getTopFlame(), 4,
                    new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE), size);*/
            }

            exploding = true;

        } else {
            for (Flame f : bombFlame) {
                f.draw();
            }
        }

    }

    public boolean hitFlame(Entity e) {
        for (Flame f : bombFlame) {
            if (f.collideWith(e)) {
                return true;
            }
        }
        return false;
    }

    public STATE getState() {
        long lifeTime = new Date().getTime() - startDate.getTime();
        if (lifeTime > 3000) {
            return STATE.DEAD;
        } else if (lifeTime > 2000) {
            return STATE.EXPLODING;
        } else {
            return STATE.ACTIVE;
        }
    }

    public boolean dead() {
        //return getState() == STATE.DEAD;
        return new Date().getTime() - startDate.getTime() > 4000;
    }


}
