package main;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class Character {

    private int speed;

    public Rectangle getCharacter() {
        return character;
    }

    Rectangle character;
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
    }

    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
