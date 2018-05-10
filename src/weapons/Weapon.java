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

public class Weapon {
    //private Bullet bullet;

    /**
     * Enum for different kind of weapons
     */
    public enum guns {PISTOL, RIFLE, SUBMACHINE, BOLTACTION}

    /**
     * The speed how fast gun can shoot, the lower the better
     */
    private double shootingSpeed;
    /**
     * Timestamp for last shot made
     */
    private double lastShot = 0;
    /**
     * bullet amount
     */
    private double bullets;
    /**
     * Rectangle of Weapon
     */
    private Rectangle weapRec;
    /**
     * Bullet type
     */
    private Bullet.bulletType bulletType;
    /**
     * this weapon type
     */
    private guns gun;

    /**
     * creation of gun
     * @param gun Gun type
     */
    public Weapon(guns gun)
    {
        this.gun = gun;
        if (gun.equals(guns.PISTOL))
        {
            bulletType = Bullet.bulletType.SEVEN_MM;
            bullets = Double.POSITIVE_INFINITY;
            shootingSpeed = 500;
            bullets = Double.POSITIVE_INFINITY;
            weapRec = new Rectangle(45, 34);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/45x34 pistol.png")));
        }
        else if (gun.equals(guns.BOLTACTION))
        {
            bulletType = Bullet.bulletType.WINCHESTER;
            shootingSpeed = 1250;
            bullets = 10;
            weapRec = new Rectangle(102, 46);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/102x46sniper.png")));

        }
        else if (gun.equals(guns.RIFLE))
        {
            bulletType = Bullet.bulletType.NATO;
            shootingSpeed = 450;
            bullets = 25;
            weapRec = new Rectangle(60, 40);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/60x40 ak47.png")));
        }
        else if( gun.equals(guns.SUBMACHINE))
        {
            bulletType = Bullet.bulletType.FORTY_FIVE;
            shootingSpeed = 100;
            bullets = 50;
            weapRec = new Rectangle(45, 40);
            weapRec.setFill(new ImagePattern(new Image("resources/weapons/45x40uzi.png")));
        }
    }

    /**
     * The shooting action
     * @param main Main class
     * @param character Main character
     */
    public void shoot(Main main, Character character)
    {
        double bulletX = 0;
        double plusx = 0;
        double plusy = 0;
        double bulletY = 0;
        double rotate = 0;
        if(System.currentTimeMillis() - lastShot >= shootingSpeed && bullets > 0) {
            if (character.getDir().equals(Character.direction.N)) {
                bulletX = character.getCharacterMidX();
                bulletY = character.getCharacter().getY();
                plusy = -1;
                rotate = 90;
            } else if (character.getDir().equals(Character.direction.E)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacterMidY();
                plusx = 1;
            } else if (character.getDir().equals(Character.direction.W)) {
                bulletX = character.getCharacter().getX();
                bulletY = character.getCharacterMidY();
                plusx = -1;
            } else if (character.getDir().equals(Character.direction.S)) {
                bulletX = character.getCharacterMidX();
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH;
                plusy = 1;
                rotate = 90;
            } else if (character.getDir().equals(Character.direction.NE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacter().getY();
                plusx = 1;
                plusy = -1;
                rotate = -45;
            } else if (character.getDir().equals(Character.direction.NW)) {
                bulletX = character.getCharacter().getX();
                bulletY = character.getCharacter().getY();
                plusx = -1;
                plusy = -1;
                rotate = 45;
            } else if (character.getDir().equals(Character.direction.SW)) {
                bulletX = character.getCharacter().getX();
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH;
                plusx = -1;
                plusy = 1;
                rotate = -45;
            } else if (character.getDir().equals(Character.direction.SE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH;
                plusx = 1;
                plusy = 1;
                rotate = 45;
            }
            Bullet bullet = new Bullet(bulletType, bulletX, bulletY, plusx, plusy, rotate);
            main.addBullet(bullet);
            main.root.getChildren().add(bullet.getBulletRect());
            lastShot = System.currentTimeMillis();
            muzzle(main, bulletX, bulletY);
            bullets--;
            main.getBulletLabel().textProperty().setValue(Double.toString(bullets).substring(0, Double.toString(bullets).length()-2));
        }
    }

    /**
     *
     * @return Weapon Rectangle
     */
    public Rectangle getWeapRec() {
        return weapRec;
    }

    /**
     *
     * @return this gun type
     */
    public guns getGun() {
        return gun;
    }

    /**
     *
     * @return amount of the bullets
     */
    public double getBullets() {
        return bullets;
    }

    /**
     * Set the bullet amount
     * @param bullets amount of bullets
     */
    public void setBullets(double bullets) {
        this.bullets = bullets;
    }

    /**
     * Creates muzzle for the gun.
     * @param main Main class
     * @param x coordinate x
     * @param y coordinate y
     */
    public void muzzle(Main main, double x, double y)
    {
        Rectangle rect = new Rectangle(10, 10);
        main.root.getChildren().add(rect);
        rect.setY(y-5);
        rect.setX(x-5);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(50),
                        event -> {
                            main.root.getChildren().remove(rect);
                        }
                )
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public double getShootingSpeed() {
        return shootingSpeed;
    }
}
