package weapons;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;

import java.util.ArrayList;
import java.util.List;

public class Bullet {

    /**
     * different types of bullets
     */
    public enum bulletType {NATO, WINCHESTER, SEVEN_MM, FORTY_FIVE}

    /**
     * Rectangle of bullet
     */
    private Rectangle bulletRect;
    /**
     * bullet Type
     */
    private bulletType bulType;
    /**
     * delta x
     */
    private double plusx;
    /**
     * delta y
     */
    private double plusy;
    /**
     * the speed of the bullet
     */
    private static double SPEED = 5;
    /**
     * the damage of the bullet
     */
    private int damage;
    /**
     * timeline for moving
     */
    private Timeline timeline = new Timeline();

    /**
     *
     * @param bulType - The bullet type
     * @param x - x coordinate, where to create
     * @param y - y coordinate, where to create
     * @param plusx - delta x for bullet moving
     * @param plusy - delta y for bullet moving
     * @param rotate - rotation of bullet
     */
    public Bullet(bulletType bulType, double x, double y, double plusx, double plusy, double rotate) {
        this.bulType = bulType;

        bulletRect = new Rectangle();
        bulletRect.setHeight(2);
        bulletRect.setWidth(5);
        if (bulType == bulletType.SEVEN_MM) {
            bulletRect.setFill(Paint.valueOf("blue"));
            damage = 10;
        } else if (bulType.equals(bulletType.NATO)) {
            damage = 25;
            bulletRect.setFill(Paint.valueOf("red"));
        } else if (bulType.equals(bulletType.FORTY_FIVE)) {
            damage = 15;
            bulletRect.setFill(Paint.valueOf("cyan"));
        } else if (bulType.equals(bulletType.WINCHESTER)) {
            damage = 45;
            bulletRect.setFill(Paint.valueOf("purple"));
        }
        bulletRect.setX(x);
        bulletRect.setY(y);
        bulletRect.setRotate(rotate);
        this.plusx = plusx;
        this.plusy = plusy;
        fly();

    }

    /**
     * The bullet flight
     */
    private void fly() {
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(10),
                        event -> {
                            bulletRect.setX(bulletRect.getX() + plusx * SPEED);
                            bulletRect.setY(bulletRect.getY() + plusy * SPEED);
                        }
                )
        );
        timeline.setCycleCount(1000);
        timeline.play();

    }

    /**
     * delete bullet after some Timeline has stopped
     * @param main Main class
     */
    public void deleteBulletAfterTime(Main main) {
        if (timeline.getStatus().equals(Timeline.Status.STOPPED)) {
            deleteBullet(main);
        }
    }

    /**
     * Delete bullet after collision
     * @param main Main class
     */
    public void deleteBullet(Main main) {
        List<Bullet> bullets = new ArrayList<>(main.getBullets());
        bullets.remove(this);
        main.root.getChildren().remove(bulletRect);
        main.setBullets(bullets);
    }

    /**
     *
     * @return the bullet's Timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     *
     * @return the damage of the bullet
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return Bullet Rectangle
     */
    public Rectangle getBulletRect() {
        return bulletRect;
    }

}
