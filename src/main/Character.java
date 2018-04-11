package main;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Character {

    //
    private static int CHARACTER_WIDTH = 50;
    private static int CHARACTER_LENGTH = 80;
    //
    private enum direction {N, NE, E, SE, S, SW, W, NW}
    private direction dir;
    private int speed;
    private int health = 100;
    //Karakteri 50 laius 80px pikkus
    Rectangle character;
    private double dx;
    private double dy;

    public Character(int speed, int x , int y)
    {
        this.speed = speed;
        Rectangle charRec;
        charRec = new Rectangle();
        charRec.setHeight(CHARACTER_LENGTH);
        charRec.setWidth(CHARACTER_WIDTH);
        Image img = new Image("main/mainCharacter.png");
        charRec.setFill(new ImagePattern(img));
        this.character = charRec;
        this.character.setX(x);
        this.character.setY(y);
    }
    public void move()
    {
        character.setX(character.getX() + dx*speed);
        character.setY(character.getY() + dy*speed);
        //System.out.println(dx+ " " + dy);
    }

    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public void shoot(Group root)
    {
        //hiire järgi sihtimine
        /*System.out.println(x + " " + y);
        double xDiff = posx - x;
        double yDiff = posy - y;
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        System.out.println(angle);*/
        double bulletX = 0;
        double bulletY = 0;
        //ümber teha
        if (dir.equals(direction.N))
        {

        }
        else if(dir.equals(direction.E))
        {
            bulletX = character.getX()+CHARACTER_WIDTH;
            bulletY = getCharacterMidY();
            System.out.println(bulletX + " " + bulletY);
        }
        else if(dx == 1 && dy == -1)
        {

        }
        else if (dx == 0 && dy == 1)
        {
        }
        else if(dx == 0 && dy == -1)
        {

        }
        else if (dx == -1 && dy == 1)
        {

        }
        else if(dx == -1 && dy == 0)
        {

        }
        else if(dx == -1 && dy == -1)
        {

        }
        Bullet bullet = new Bullet(Bullet.bulletType.NORMAL, bulletX, bulletY,20);
        root.getChildren().add(bullet.getBulletRect());
    }
    public void playerDirection()
    {
        if (dx == 1 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy.png");
            character.setFill(new ImagePattern(img));
            dir = direction.SE;
        }
        else if(dx == 1 && dy == 0)
        {
            Image img = new Image("main/mainCharacter.png");
            character.setFill(new ImagePattern(img));
            dir = direction.E;
        }
        else if(dx == 1 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (2).png");
            character.setFill(new ImagePattern(img));
            dir = direction.NE;
        }
        else if (dx == 0 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy (3).png");
            character.setFill(new ImagePattern(img));
            dir = direction.S;
        }
        else if(dx == 0 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (4).png");
            character.setFill(new ImagePattern(img));
            dir = direction.N;
        }
        else if (dx == -1 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy (5).png");
            character.setFill(new ImagePattern(img));
            dir = direction.SW;
        }
        else if(dx == -1 && dy == 0)
        {
            Image img = new Image("main/mainCharacter - Copy (6).png");
            character.setFill(new ImagePattern(img));
            dir = direction.W;
        }
        else if(dx == -1 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (7).png");
            character.setFill(new ImagePattern(img));
            dir = direction.NW;
        }

    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
    public Rectangle getCharacter() {
        return character;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public double getCharacterMidX()
    {
        return character.getX()+CHARACTER_WIDTH/2;
    }
    public double getCharacterMidY()
    {
        return character.getY() + CHARACTER_LENGTH/2;
    }
}
