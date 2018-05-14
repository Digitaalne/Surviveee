package enemy;

import character.Character;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EnemyBoss extends Enemy {
    /**
     * creation of enemy pos
     *
     * @param posx creation coordinate x
     * @param posy creation coordinate y
     */
    public EnemyBoss(int posx, int posy) {
        super(posx, posy);
        this.type = Type.BOSS;
        enemyRect = new Rectangle();
        enemyRect.setY(posy);
        enemyRect.setX(posx);
        enemyRect.setWidth(100);
        enemyRect.setHeight(100);
        speed = 0.5;
        health = 125;
        damage = 20;
    }

    /**
     * Enemy image based on direction
     */
    @Override
    public void zombieDirection() {
        if (dx == 1 && dy == 1) {
            Image img = new Image("resources/boss/SE.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.SE;
        } else if (dx == 1 && dy == 0) {
            Image img = new Image("resources/boss/right.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.E;
        } else if (dx == 1 && dy == -1) {
            Image img = new Image("resources/boss/NE true.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.NE;
        } else if (dx == 0 && dy == 1) {
            Image img = new Image("resources/boss/down.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.S;
        } else if (dx == 0 && dy == -1) {
            Image img = new Image("resources/boss/up.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.N;
        } else if (dx == -1 && dy == 1) {
            Image img = new Image("resources/boss/SW.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.SW;
        } else if (dx == -1 && dy == 0) {
            Image img = new Image("resources/boss/left.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.W;
        } else if (dx == -1 && dy == -1) {
            Image img = new Image("resources/boss/NW true.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.NW;
        }
    }

    @Override
    public void beginHit() {
        Image image = null;
        if (dir.equals(Character.direction.N)) {
            image = new Image("resources/boss/bloody/up_bloody.png");
        } else if (dir.equals(Character.direction.E)) {
            image = new Image("resources/boss/bloody/right_bloody.png");
        } else if (dir.equals(Character.direction.W)) {
            image = new Image("resources/boss/bloody/left_bloody.png");
        } else if (dir.equals(Character.direction.S)) {
            image = new Image("resources/boss/bloody/down_bloody.png");
        } else if (dir.equals(Character.direction.NE)) {
            image = new Image("resources/boss/bloody/NE_bloody.png");
        } else if (dir.equals(Character.direction.NW)) {
            image = new Image("resources/boss/bloody/NW_bloody.png");
        } else if (dir.equals(Character.direction.SW)) {
            image = new Image("resources/boss/bloody/SW_bloody.png");
        } else if (dir.equals(Character.direction.SE)) {
            image = new Image("resources/boss/bloody/SE_bloody.png");
        }
        enemyRect.setFill(new ImagePattern(image));
    }

    /**
     * For testing purposes
     */
    public EnemyBoss() {
    }
}
