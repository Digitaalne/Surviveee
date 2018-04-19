package weapons;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import main.Bullet;
import main.Character;
import main.Main;


import static main.Character.CHARACTER_LENGTH;

public class Weapon {
    //private Bullet bullet;
    public enum guns {PISTOL, RIFLE, SUBMACHINE, BOLTACTION}
    private double shootingSpeed;
    private double lastShot = 0;
    private double bullets = Double.POSITIVE_INFINITY;
    private Rectangle weapRec;
    private Bullet.bulletType bulletType;
    public Weapon(guns gun)
    {
        if (gun.equals(guns.PISTOL))
        {
            bulletType = Bullet.bulletType.SEVEN_MM;
            bullets = Double.POSITIVE_INFINITY;
            shootingSpeed = 500;
            weapRec = new Rectangle(10, 10);
        }
        else if (gun.equals(guns.BOLTACTION))
        {
            bulletType = Bullet.bulletType.WINCHESTER;
            shootingSpeed = 1250;
            weapRec = new Rectangle(75, 50);

        }
        else if (gun.equals(guns.RIFLE))
        {
            bulletType = Bullet.bulletType.NATO;
            shootingSpeed = 450;
            weapRec = new Rectangle(75, 25);
        }
        else if( gun.equals(guns.SUBMACHINE))
        {
            bulletType = Bullet.bulletType.FORTY_FIVE;
            shootingSpeed = 100;
            weapRec = new Rectangle(25, 25);
        }
        //this.bullet = bullet;
    }

    public void shoot(Main main, Character character)
    {
        //hiire järgi sihtimine
        /*System.out.println(x + " " + y);
        double xDiff = posx - x;
        double yDiff = posy - y;
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        System.out.println(angle);*/
        double bulletX = 0;
        double plusx = 0;
        double plusy = 0;
        double bulletY = 0;
        //ümber teha
        if(System.currentTimeMillis() - lastShot >= shootingSpeed && bullets > 0) {
            if (character.getDir().equals(Character.direction.N)) {
                bulletX = character.getCharacterMidX();
                bulletY = character.getCharacter().getY();
                plusy = -1;
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
            } else if (character.getDir().equals(Character.direction.NE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacter().getY();
                plusx = 1;
                plusy = -1;
            } else if (character.getDir().equals(Character.direction.NW)) {
                bulletX = character.getCharacter().getX();
                bulletY = character.getCharacter().getY();
                plusx = -1;
                plusy = -1;
            } else if (character.getDir().equals(Character.direction.SW)) {
                bulletX = character.getCharacter().getX();
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH;
                plusx = -1;
                plusy = 1;
            } else if (character.getDir().equals(Character.direction.SE)) {
                bulletX = character.getCharacter().getX() + Character.CHARACTER_WIDTH;
                bulletY = character.getCharacter().getY() + CHARACTER_LENGTH;
                plusx = 1;
                plusy = 1;
            }
            Bullet bullet = new Bullet(bulletType, bulletX, bulletY, plusx, plusy);
            main.addBullet(bullet);
            main.root.getChildren().add(bullet.getBulletRect());
            lastShot = System.currentTimeMillis();
            bullets--;
        }
    }

    public Rectangle getWeapRec() {
        return weapRec;
    }
}
