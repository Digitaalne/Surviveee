package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import weapons.Weapon;

import java.util.Random;

public class Enemy {
    Rectangle enemyRect;
    enum Type {BASIC, BOSS}
    private double dx;
    private Type type;
    private double dy;
    private double speed;
    private int damage = 10;
    private int health = 50;
    public Enemy(int posx, int posy)
    {
        this.type = Type.BASIC;
        enemyRect = new Rectangle();
        enemyRect.setY(posy);
        enemyRect.setX(posx);
        //ajutine
        enemyRect.setWidth(50);
        enemyRect.setHeight(80);
        enemyRect.setFill(Paint.valueOf("red"));
        //
        speed = 0.5;
    }
    public void move()
    {
        enemyRect.setX(enemyRect.getX() + dx*speed);
        enemyRect.setY(enemyRect.getY() + dy*speed);
    }
    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public void moveTowardsCharacter(double x, double y)
    {
        if (enemyRect.getX() < x)
        {
            setDeltas(1, dy);
        }
        else
        {
            setDeltas(-1, dy);
        }
        if(enemyRect.getY() < y)
        {
            setDeltas(dx, 1);
        }
        else
        {
            setDeltas(dx, -1);
        }
        if(enemyRect.getX() == x)
        {
            setDeltas(0, dy);
        }
        else if(enemyRect.getY() == y)
        {
            setDeltas(dx, 0);
        }
    }

    public void pushBack()
    {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(1),
                        event -> {
                            System.out.println(dx);
                            System.out.println(dy);
                            enemyRect.setX(enemyRect.getX() - dx*1);
                            enemyRect.setY(enemyRect.getY() - dy*1);
                        }
                )
        );
        timeline.setCycleCount(100);
        timeline.play();
    }
    public void deathDrop(Main main)
    {
        //relvade kukutamine surma korral
        Random random = new Random();
        if(random.nextInt(10) == 9)
        {
            Weapon weapon = null;
            int randm = random.nextInt(3);
            switch (randm) {
                case 0:
                    weapon = (new Weapon(Weapon.guns.RIFLE));
                    break;
                case 1:
                    weapon = (new Weapon(Weapon.guns.SUBMACHINE));
                    break;
                case 2:
                    weapon = new Weapon(Weapon.guns.BOLTACTION);
                    break;
            }
            main.addWeapon(weapon);
            main.root.getChildren().add(weapon.getWeapRec());
            weapon.getWeapRec().setX(enemyRect.getX());
            weapon.getWeapRec().setY(enemyRect.getY());
        }
    }


    public Rectangle getEnemyRect() {
        return enemyRect;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Type getType() {
        return type;
    }
}
