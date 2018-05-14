package weapons;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import character.Character;
import main.Main;


import static character.Character.CHARACTER_LENGTH;
import static character.Character.CHARACTER_WIDTH;

public class Weapon {
    /**
     * for testing puropses
     */
    public Weapon() {
    }
    //private Bullet bullet;

    /**
     * Enum for different kind of weapons
     */
    public enum guns {
        PISTOL, RIFLE, SUBMACHINE, BOLTACTION
    }

    /**
     * The speed how fast gun can shoot, the lower the better
     */
    protected double shootingSpeed;
    /**
     * Timestamp for last shot made
     */
    private double lastShot = 0;
    /**
     * bullet amount
     */
    protected double bullets;
    /**
     * Rectangle of Weapon
     */
    private Rectangle weapRec;
    /**
     * Bullet type
     */
    protected Bullet.bulletType bulletType;
    /**
     * this weapon type
     */
    protected guns gun;

    /**
     * creation of gun
     *
     * @param gun Gun type
     */
    public Weapon(guns gun) {
        this.gun = gun;
        if (gun.equals(guns.PISTOL)) {
            bulletType = Bullet.bulletType.SEVEN_MM;
            bullets = Double.POSITIVE_INFINITY;
            shootingSpeed = 500;
            weapRec = new Rectangle(45, 34);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/45x34 pistol.png")));
        } else if (gun.equals(guns.BOLTACTION)) {
            bulletType = Bullet.bulletType.WINCHESTER;
            shootingSpeed = 1250;
            bullets = 10;
            weapRec = new Rectangle(102, 46);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/102x46sniper.png")));

        } else if (gun.equals(guns.RIFLE)) {
            bulletType = Bullet.bulletType.NATO;
            shootingSpeed = 450;
            bullets = 25;
            weapRec = new Rectangle(60, 40);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/60x40 ak47.png")));
        } else if (gun.equals(guns.SUBMACHINE)) {
            bulletType = Bullet.bulletType.FORTY_FIVE;
            shootingSpeed = 120;
            bullets = 150;
            weapRec = new Rectangle(45, 40);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/45x40uzi.png")));
        }
    }

    /**
     * The shooting action
     *
     * @param main      Main class
     * @param character Main character
     */
    public void shoot(Main main, Character character) {
        double bulletX = 0;
        double plusX = 0;
        double plusY = 0;
        double bulletY = 0;
        double rotate = 0;
        double flip = 1;
        double muzzleX = 0;
        double muzzleY = 0;
        if (System.currentTimeMillis() - lastShot >= shootingSpeed && bullets > 0) {
            if (character.getDir().equals(Character.direction.N)) {
                bulletX = character.getCharacter().getX() + CHARACTER_WIDTH - 17;
                bulletY = character.getCharacter().getY() + 18;
                plusY = -1;
                rotate = 90;
                flip = -1;

            } else if (character.getDir().equals(Character.direction.E)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacterMidY() - 9;
                muzzleY = -3;
                plusX = 1;
            } else if (character.getDir().equals(Character.direction.W)) {
                bulletX = character.getCharacter().getX() - 5;
                bulletY = character.getCharacterMidY() - 9;
                plusX = -1;
                flip = -1;
                muzzleY = -3;
            } else if (character.getDir().equals(Character.direction.S)) {
                bulletX = character.getCharacter().getX() + 4;
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH - 50;
                plusY = 1;
                rotate = 90;
            } else if (character.getDir().equals(Character.direction.NE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH + 3;
                bulletY = character.getCharacter().getY() + 3;
                plusX = 1;
                plusY = -1;
                rotate = -45;
                flip = -1;
            } else if (character.getDir().equals(Character.direction.NW)) {
                bulletX = character.getCharacter().getX() - 12;
                bulletY = character.getCharacter().getY() + 3;
                plusX = -1;
                plusY = -1;
                rotate = 45;
                flip = -1;
            } else if (character.getDir().equals(Character.direction.SW)) {
                bulletX = character.getCharacter().getX() + 8;
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH - 28;
                plusX = -1;
                plusY = 1;
                rotate = -45;
                flip = -1;
            } else if (character.getDir().equals(Character.direction.SE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH - 10;
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH - 20;
                plusX = 1;
                plusY = 1;
                rotate = 45;
            }
            Bullet bullet = new Bullet(bulletType, bulletX, bulletY, plusX, plusY, rotate, flip);
            main.addBullet(bullet);
            main.root.getChildren().add(bullet.getBulletRect());
            lastShot = System.currentTimeMillis();
            muzzle(main, bulletX, bulletY, rotate, flip, muzzleX, muzzleY);
            bullets--;
            main.getBulletLabel().textProperty().setValue(Double.toString(bullets).substring(0, Double.toString(bullets).length() - 2));
        }
    }

    /**
     * @return Weapon Rectangle
     */
    public Rectangle getWeapRec() {
        return weapRec;
    }

    /**
     * @return this gun type
     */
    public guns getGun() {
        return gun;
    }

    /**
     * @return amount of the bullets
     */
    public double getBullets() {
        return bullets;
    }

    /**
     * Set the bullet amount
     *
     * @param bullets amount of bullets
     */
    public void setBullets(double bullets) {
        this.bullets = bullets;
    }

    /**
     * Creates muzzle for the gun.
     *
     * @param main Main class
     * @param x    coordinate x
     * @param y    coordinate y
     */
    public void muzzle(Main main, double x, double y, double rotate, double flip, double muzzleX, double muzzleY) {
        Rectangle rect = new Rectangle(10, 10);
        rect.setFill(new ImagePattern(new Image("resources/misc/muzzel flash.png")));
        main.root.getChildren().add(rect);
        rect.setRotate(rotate);
        rect.setScaleX(flip);
        rect.setY(y + muzzleY);
        rect.setX(x + muzzleX);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(30),
                        event -> main.root.getChildren().remove(rect)
                )
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public double getShootingSpeed() {
        return shootingSpeed;
    }
}
