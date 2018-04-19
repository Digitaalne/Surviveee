package weapons;

import main.Bullet;
import main.Character;
import main.Main;


import static main.Character.CHARACTER_LENGTH;

public class Weapon {
    //private Bullet bullet;
    private double shootingSpeed;
    private double lastShot = 0;
    public Weapon(double shootingSpeed)
    {
        //this.bullet = bullet;
        this.shootingSpeed = shootingSpeed;
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
        if(System.currentTimeMillis() - lastShot >= shootingSpeed) {
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
            Bullet bullet = new Bullet(Bullet.bulletType.NORMAL, bulletX, bulletY, plusx, plusy);
            main.addBullet(bullet);
            main.root.getChildren().add(bullet.getBulletRect());
            lastShot = System.currentTimeMillis();
        }
    }
}
