package main;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bullet {
    public Rectangle getBulletRect() {
        return bulletRect;
    }
    public enum bulletType{NORMAL}
    private Rectangle bulletRect;
    private bulletType bulType;
    double x;
    double y;
    private int damage;
    public Bullet(bulletType bulType, double x, double y, int damage)
    {
        this.bulType = bulType;
        this.x = x;
        this.damage = damage;
        this.y = y;
        if (bulType == bulletType.NORMAL)
        {
            bulletRect = new Rectangle();
            bulletRect.setHeight(2);
            bulletRect.setWidth(5);
            bulletRect.setFill(Paint.valueOf("blue"));
        }
        bulletRect.setX(x);
        bulletRect.setY(y);
    }


    public int getDamage() {
        return damage;
    }

}
