package main;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class Character {

    private int speed;

    public Rectangle getCharacter() {
        return character;
    }

    Rectangle character;

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    //Karakterid 50 laius 80px pikkus
    private double dx;
    private double dy;
    private double posx;
    private double posy;

    public Character(Rectangle character, int speed, int x , int y)
    {
        this.posx = x;
        this.posy = y;
        this.character = character;
        this.character.setX(x);
        this.character.setY(y);
        this.speed = speed;
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
    public void shoot(double x, double y)
    {
        //hiire järgi sihtimine
        /*System.out.println(x + " " + y);
        double xDiff = posx - x;
        double yDiff = posy - y;
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        System.out.println(angle);*/
    }
    public void playerDirection()
    {
        if (dx == 1 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy.png");
            character.setFill(new ImagePattern(img));
        }
        else if(dx == 1 && dy == 0)
        {
            Image img = new Image("main/mainCharacter.png");
            character.setFill(new ImagePattern(img));
        }
        else if(dx == 1 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (2).png");
            character.setFill(new ImagePattern(img));
        }
        else if (dx == 0 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy (3).png");
            character.setFill(new ImagePattern(img));
        }
        else if(dx == 0 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (4).png");
            character.setFill(new ImagePattern(img));
        }
        else if (dx == -1 && dy == 1)
        {
            Image img = new Image("main/mainCharacter - Copy (5).png");
            character.setFill(new ImagePattern(img));
        }
        else if(dx == -1 && dy == 0)
        {
            Image img = new Image("main/mainCharacter - Copy (6).png");
            character.setFill(new ImagePattern(img));
        }
        else if(dx == -1 && dy == -1)
        {
            Image img = new Image("main/mainCharacter - Copy (7).png");
            character.setFill(new ImagePattern(img));
        }

    }
}
