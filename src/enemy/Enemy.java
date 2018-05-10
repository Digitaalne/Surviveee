package enemy;

import character.Character;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;
import weapons.Weapon;

import java.util.Random;

public class Enemy {
    /**
     * Rectangle of enemy
     */
    Rectangle enemyRect;



    /**
     * the type of zombie enum
     */
    public enum Type {BASIC, BOSS}

    /**
     * delta x of zombie
     */
    double dx;
    /**
     * zombie Type
     */
    Type type;
    /**
     * Delta y of zombie
     */
    double dy;
    /**
     * the movement speed of zombie
     */
    double speed;
    /**
     * the damage that zombie makes
     */
    int damage;
    /**
     * zombies health
     */
    int health;
    /**
     * Current moving direction of zombie
     */
    Character.direction dir;

    /**
     * Creation of zombie
     * @param posx x coordinate
     * @param posy y coordinate
     */
    public Enemy(int posx, int posy) {
        this.type = Type.BASIC;
        enemyRect = new Rectangle();
        enemyRect.setY(posy);
        enemyRect.setX(posx);
        health = 50;
        damage = 10;
        enemyRect.setWidth(50);
        enemyRect.setHeight(80);
        //enemyRect.setFill(Paint.valueOf("red"));
        //
        speed = 0.5;
        dir = Character.direction.E;
    }

    /**
     * moves the zombie depending on deltas
     */
    public void move() {
        enemyRect.setX(enemyRect.getX() + dx * speed);
        enemyRect.setY(enemyRect.getY() + dy * speed);
    }

    /**
     * Set deltas
     * @param dx x delta
     * @param dy y delta
     */
    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Picks direction towards character
     * @param x character Rectangle x
     * @param y Character Rectangle Y
     */
    public void moveTowardsCharacter(double x, double y) {
        if (enemyRect.getX() < x) {
            setDeltas(1, dy);
        } else {
            setDeltas(-1, dy);
        }
        if (enemyRect.getY() < y) {
            setDeltas(dx, 1);
        } else {
            setDeltas(dx, -1);
        }
        if (enemyRect.getX() == x) {
            setDeltas(0, dy);
        } else if (enemyRect.getY() == y) {
            setDeltas(dx, 0);
        }
    }

    /**
     * Pushes zombie back
     */
    public void pushBack() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(1),
                        event -> {
                            enemyRect.setX(enemyRect.getX() - dx * 1);
                            enemyRect.setY(enemyRect.getY() - dy * 1);
                        }
                )
        );
        timeline.setCycleCount(100);
        timeline.play();
    }

    /**
     * Changes the pictures and says which way zombie is really going.
     */
    public void zombieDirection() {
        if (dx == 1 && dy == 1) {
            Image img = new Image("resources/zombi/SE.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.SE;
        } else if (dx == 1 && dy == 0 ) {
            Image img = new Image("resources/zombi/right.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.E;
        } else if (dx == 1 && dy == -1 ) {
            Image img = new Image("resources/zombi/NE.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.NE;
        } else if (dx == 0 && dy == 1 ) {
            Image img = new Image("resources/zombi/south.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.S;
        } else if (dx == 0 && dy == -1 ) {
            Image img = new Image("resources/zombi/north.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.N;
        } else if (dx == -1 && dy == 1 ) {
            Image img = new Image("resources/zombi/SW2.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.SW;
        } else if (dx == -1 && dy == 0) {
            Image img = new Image("resources/zombi/left.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.W;
        } else if (dx == -1 && dy == -1) {
            Image img = new Image("resources/zombi/NW.png");
            enemyRect.setFill(new ImagePattern(img));
            dir = Character.direction.NW;
        }
    }

    /**
     * Changes zombie Rectangle picture if he gets hit with bullets
     */
    public void beginHit()
    {
        Image image= null;
        if (dir.equals(Character.direction.N)) {
            image = new Image("resources/zombi/zombi got shot/blody north.png");
        } else if (dir.equals(Character.direction.E)) {
            image = new Image("resources/zombi/zombi got shot/blody right.png");
        } else if (dir.equals(Character.direction.W)) {
            image = new Image("resources/zombi/zombi got shot/blody left.png");
        } else if (dir.equals(Character.direction.S)) {
            image = new Image("resources/zombi/zombi got shot/blody south.png");
        } else if (dir.equals(Character.direction.NE)) {
            image = new Image("resources/zombi/zombi got shot/blody NE.png");
        } else if (dir.equals(Character.direction.NW)) {
            image = new Image("resources/zombi/zombi got shot/blody NW.png");
        } else if (dir.equals(Character.direction.SW)) {
            image = new Image("resources/zombi/zombi got shot/blody SW.png");
        } else if (dir.equals(Character.direction.SE)) {
            image = new Image("resources/zombi/zombi got shot/blody SE.png");
        }
        enemyRect.setFill(new ImagePattern(image));
    }

    /**
     * Drops the weapon on the floor if luck is on players side
     * @param main Main class
     */
    public void deathDrop(Main main) {
        //relvade kukutamine surma korral
        Random random = new Random();
        if (random.nextInt(10) == 9) {
            Weapon weapon = null;
            int randm = random.nextInt(3);
            switch (randm) {
                case 0:
                    weapon = (new Weapon(Weapon.guns.RIFLE));
                    break;
                case 1:
                    weapon = (new Weapon(Weapon.guns.SUBMACHINE));
                    break;
                case 2:
                    weapon = new Weapon(Weapon.guns.BOLTACTION);
                    break;
            }
            main.addWeapon(weapon);
            main.root.getChildren().add(weapon.getWeapRec());
            weapon.getWeapRec().setX(enemyRect.getX());
            weapon.getWeapRec().setY(enemyRect.getY());
        }
    }

    /**
     *
     * @return Enemy Rectangle
     */

    public Rectangle getEnemyRect() {
        return enemyRect;
    }

    /**
     *
     * @return Zombie damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return Zombies health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets zombies health to ...
     * @param health health amount
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return zombie delta x
     */
    public double getDx() {
        return dx;
    }

    /**
     *
     * @return zombie delta y
     */
    public double getDy() {
        return dy;
    }

    /**
     *
     * @return Zombie Type
     */
    public Type getType() {
        return type;
    }

    /**
     * For testing purposes
     */
    public Enemy() {
    }
}
