package main;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bullet {
    public Rectangle getBulletRect() {
        return bulletRect;
    }

    Rectangle bulletRect;
    double x;
    double y;
    public Bullet(Rectangle rect, double x, double y)
    {
        this.x = x;
        this.y = y;
        bulletRect = rect;
    }
    public Bullet(double x, double y)
    {
        this.x = x;
        this.y = y;
        bulletRect = new Rectangle();
        bulletRect.setHeight(2);
        bulletRect.setWidth(5);
        bulletRect.setFill(Paint.valueOf("blue"));
    }
}
