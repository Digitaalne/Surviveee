package main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Bullet {
    public Rectangle getBulletRect() {
        return bulletRect;
    }
    public enum bulletType{NATO, WINCHESTER, SEVEN_MM, FORTY_FIVE}
    private Rectangle bulletRect;
    private bulletType bulType;
    private double plusx;
    private double plusy;
    private static double SPEED = 5;
    private double x;
    private double y;
    private int damage;
    private Timeline timeline = new Timeline();
    public Bullet(bulletType bulType, double x, double y, double plusx, double plusy)
    {
        this.bulType = bulType;
        this.x = x;
        this.y = y;
        bulletRect = new Rectangle();
        bulletRect.setHeight(2);
        bulletRect.setWidth(5);
        if (bulType == bulletType.SEVEN_MM)
        {
            bulletRect.setFill(Paint.valueOf("blue"));
            damage = 10;
        }
        else if (bulType.equals(bulletType.NATO))
        {
            damage = 25;
            bulletRect.setFill(Paint.valueOf("red"));
        }
        else if(bulType.equals(bulletType.FORTY_FIVE))
        {
            damage = 15;
            bulletRect.setFill(Paint.valueOf("cyan"));
        }
        else if(bulType.equals(bulletType.WINCHESTER))
        {
            damage = 45;
            bulletRect.setFill(Paint.valueOf("purple"));
        }
        bulletRect.setX(x);
        bulletRect.setY(y);
        this.plusx = plusx;
        this.plusy = plusy;
        fly();

    }
    private void fly()
    {
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(10),
                        event -> {
                            bulletRect.setX(bulletRect.getX() + plusx*SPEED);
                            bulletRect.setY(bulletRect.getY() + plusy*SPEED);
                        }
                )
        );
        timeline.setCycleCount(1000);
        timeline.play();

    }
    public void deleteBulletAfterTime(Main main)
    {
        if(timeline.getStatus().equals(Timeline.Status.STOPPED))
        {
            deleteBullet(main);
        }
    }
    public void deleteBullet(Main main)
    {
        List<Bullet> bullets = new ArrayList<>(main.getBullets());
        bullets.remove(this);
        main.root.getChildren().remove(bulletRect);
        main.setBullets(bullets);
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getDamage() {
        return damage;
    }

}
