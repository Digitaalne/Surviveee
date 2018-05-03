package weapons;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import character.Character;
import main.Main;


import static character.Character.CHARACTER_LENGTH;

public class Weapon {
    //private Bullet bullet;
    public enum guns {PISTOL, RIFLE, SUBMACHINE, BOLTACTION}
    private double shootingSpeed;
    private double lastShot = 0;
    private double bullets;
    private Rectangle weapRec;
    private Bullet.bulletType bulletType;
    private guns gun;
    public Weapon(guns gun)
    {
        this.gun = gun;
        if (gun.equals(guns.PISTOL))
        {
            bulletType = Bullet.bulletType.SEVEN_MM;
            bullets = Double.POSITIVE_INFINITY;
            shootingSpeed = 500;
            bullets = Double.POSITIVE_INFINITY;
            weapRec = new Rectangle(10, 10);
        }
        else if (gun.equals(guns.BOLTACTION))
        {
            bulletType = Bullet.bulletType.WINCHESTER;
            shootingSpeed = 1250;
            bullets = 10;
            weapRec = new Rectangle(75, 50);

        }
        else if (gun.equals(guns.RIFLE))
        {
            bulletType = Bullet.bulletType.NATO;
            shootingSpeed = 450;
            bullets = 100;
            weapRec = new Rectangle(75, 25);
        }
        else if( gun.equals(guns.SUBMACHINE))
        {
            bulletType = Bullet.bulletType.FORTY_FIVE;
            shootingSpeed = 100;
            bullets = 250;
            weapRec = new Rectangle(25, 25);
        }
    }

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

    public Rectangle getWeapRec() {
        return weapRec;
    }

    public guns getGun() {
        return gun;
    }

    public double getBullets() {
        return bullets;
    }

    public void setBullets(double bullets) {
        this.bullets = bullets;
    }
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
}
