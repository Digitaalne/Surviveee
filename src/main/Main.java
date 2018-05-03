package main;

import character.Character;
import enemy.Enemy;
import enemy.EnemyBoss;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.util.Duration;
import weapons.Bullet;
import weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static character.Character.CHARACTER_LENGTH;
import static character.Character.CHARACTER_WIDTH;


public class Main {
    private Label charHealth = new Label();
    private List<Enemy> enemies = new ArrayList<>();
    private Label deathText = new Label();
    private List<Bullet> bullets = new ArrayList<>();
    private List<Weapon> droppedWeapons = new ArrayList<>();
    private Character character;
    public Group root = new Group();
    //punktiosa
    private IntegerProperty points = new SimpleIntegerProperty(0);
    private Label pointsLabel = new Label();
    private MainMenu mainMenu;
    private Label bulletLabel = new Label();

    public Main(MainMenu mainMenu)
    {
        this.mainMenu = mainMenu;
    }

    public void starterino(int hardness) {

        root.setFocusTraversable(true);
        root.requestFocus();
        character = new Character(3, 500, 500, new Weapon(Weapon.guns.RIFLE), Main.this);
        root.getChildren().add(character.getCharacter());
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && character.getCharacter().getY() > 0) {
                character.setDeltas(character.getDx(), -1);
            }
            else if (event.getCode() == KeyCode.DOWN && character.getCharacter().getY() < 768-CHARACTER_LENGTH) {
                character.setDeltas(character.getDx(), 1);
            }
            else if (event.getCode() == KeyCode.LEFT && character.getCharacter().getX() > 0) {
                character.setDeltas(-1, character.getDy());
            }
            else if (event.getCode() == KeyCode.RIGHT && character.getCharacter().getX() < 1024-CHARACTER_WIDTH) {
                character.setDeltas(1, character.getDy());
            }
            if(character.getCharacter().getX() < 0)
            {
                character.getCharacter().setX(0);
            }
            else if(character.getCharacter().getX() > 1024-CHARACTER_WIDTH)
            {
                character.getCharacter().setX(1024-CHARACTER_WIDTH);
            }
            if(character.getCharacter().getY() < 0)
            {
                character.getCharacter().setY(0);
            }
            else if(character.getCharacter().getY() > 768-CHARACTER_LENGTH)
            {
                character.getCharacter().setY(768-CHARACTER_LENGTH);
            }
            if (event.getCode() == KeyCode.SPACE) {
                character.getWeapon().shoot(Main.this, character);
                //character.getWeapon().shoot(Main.this, character);
                character.bulletZeroCheck();
            }
        });

        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP)
                character.setDeltas(character.getDx(), 0);
            else if (event.getCode() == KeyCode.DOWN)
                character.setDeltas(character.getDx(), 0);
            else if (event.getCode() == KeyCode.LEFT)
                character.setDeltas(0, character.getDy());
            else if (event.getCode() == KeyCode.RIGHT)
                character.setDeltas(0, character.getDy());
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                character.move();
                character.playerDirection();
                try {
                    character.deathAndHealth(deathText, charHealth, Main.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Enemy e : enemies) {
                    e.move();
                    e.zombieDirection();
                    e.moveTowardsCharacter(character.getCharacter().getX(), character.getCharacter().getY());
                    enemiesCollision(e);
                    zombieDeath(e);
                }
                for (Bullet bullet : bullets) {
                    //bulletite kaotamine
                    bullet.deleteBulletAfterTime(Main.this);
                    //System.out.println(bullets.size());

                }
                List<Weapon> weapons2 = new ArrayList<>(droppedWeapons);
                boolean changed = false;
                for (Weapon weapon : droppedWeapons) {
                    if (weapon.getWeapRec().getBoundsInParent().intersects(character.getCharacter().getBoundsInParent())) {
                        character.setWeapon(weapon);
                        weapons2.remove(weapon);
                        root.getChildren().remove(weapon.getWeapRec());
                        changed = true;
                    }
                }
                if (changed) {
                    droppedWeapons = new ArrayList<>(weapons2);
                }
            }
        }.start();
        character.setDeltas(0, 0);
        //Enemies spawning
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(hardness),
                        event -> {
                            generateEnemies();
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //UI
        pointsLabel.setFont(new Font("Racer", 50));
        pointsLabel.textProperty().bind(points.asString());
        root.getChildren().addAll(pointsLabel, deathText, charHealth, bulletLabel);
        deathText.setTranslateX(500);
        deathText.setTranslateY(500);
        charHealth.setTranslateX(20);
        bulletLabel.setLayoutX(500);
        bulletLabel.setLayoutY(500);
    }

    public void generateEnemies() {
        Random dir = new Random();
        int dir1 = dir.nextInt(4);
        int zombieLevel = dir.nextInt(10);
        Enemy enemy = null;
        if (dir1 == 0) {
            //Up
            if(zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(-400, -100));
            }
            else
            {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(-400, -100));
            }
        } else if (dir1 == 1) {
            //Down
            if(zombieLevel<9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(900, 1300));
            }
            else
            {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(900, 1300));
            }
        } else if (dir1 == 2) {
            //Right
            if(zombieLevel<9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(1100, 1400),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
            else
            {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(1100, 1400),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
        } else if (dir1 == 3) {
            //left
            if(zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-300, -100),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
            else
            {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-300, -100),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
        }

        enemies.add(enemy);
        root.getChildren().add(enemy.getEnemyRect());
    }

    public void enemiesCollision(Enemy e) {

        //Kui inimene saab pihta zombiga
        if (e.getEnemyRect().getBoundsInParent().intersects(character.getCharacter().getBoundsInParent())) {
            character.setHealth(character.getHealth() - e.getDamage());
            e.pushBack();
        }
        //Kui vaenlane saab pihta kuuliga
        for (Bullet bullet : bullets) {
            if (e.getEnemyRect().getBoundsInParent().intersects(bullet.getBulletRect().getBoundsInParent())) {
                e.setHealth(e.getHealth() - bullet.getDamage());
                bullet.deleteBullet(Main.this);
                e.beginHit();
            }
        }

    }

    public void zombieDeath(Enemy enemy) {
        if (enemy.getHealth() <= 0) {
            List<Enemy> enemies1 = new ArrayList<>(enemies);
            enemy.deathDrop(Main.this);
            enemies1.remove(enemy);
            root.getChildren().remove(enemy.getEnemyRect());
            if (enemy.getType().equals(Enemy.Type.BASIC)) {
                points.setValue(points.get() + 1);
            } else if (enemy.getType().equals(Enemy.Type.BOSS)) {
                points.setValue(points.get() + 10);
            }
            enemies = enemies1;
        }
    }
    public void cleanUp()
    {
     droppedWeapons = new ArrayList<>();
     bullets = new ArrayList<>();
     enemies = new ArrayList<>();
     points = new SimpleIntegerProperty(0);
     root.getChildren().removeAll();
     character.setHealth(10000);
     mainMenu.cleanUp();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void addWeapon(Weapon weapon) {
        droppedWeapons.add(weapon);
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Label getBulletLabel() {
        return bulletLabel;
    }
}
