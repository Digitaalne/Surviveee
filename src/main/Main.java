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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    /**
     * background of game
     */
    private ImageView backGround = new ImageView("resources/background.png");
    /**
     * UI - hearth/health
     */
    private ImageView hearth = new ImageView("resources/misc/hearth2.png");
    /**
     * Image of bullets
     */
    private ImageView bulletsImg = new ImageView("resources/misc/sniper bullets.png");
    /**
     * The label of characters health
     */
    private Label charHealth = new Label();
    /**
     * List of alive enemies.
     */
    private List<Enemy> enemies = new ArrayList<>();
    /**
     * Label of death text
     */
    private Label deathText = new Label();
    /**
     * List of flying bullets
     */
    private List<Bullet> bullets = new ArrayList<>();
    /**
     * List of current dropped weapons on the floore
     */
    private List<Weapon> droppedWeapons = new ArrayList<>();
    /**
     * The main character
     */
    private Character character;
    /**
     * The Group
     */
    public Group root = new Group();
    /**
     * Points what player has achieved
     */
    private IntegerProperty points = new SimpleIntegerProperty(0);
    /**
     * label for points
     */
    private Label pointsLabel = new Label();
    /**
     * main menu instance
     */
    private MainMenu mainMenu;
    /**
     * Image of your current gun
     */
    private Rectangle currentGun = new Rectangle(45, 30);
    /**
     * Bullets amount label
     */
    private Label bulletLabel = new Label();

    /**
     * The instance of Main
     *
     * @param mainMenu the instance of Main Menu
     */
    public Main(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Start procedure and the "brain"
     *
     * @param hardness the difficulty level
     */
    public void starterino(int hardness) {

        root.setFocusTraversable(true);
        root.requestFocus();
        character = new Character(3, 500, 500, new Weapon(Weapon.guns.PISTOL), Main.this);
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && character.getCharacter().getY() > 0) {
                character.setDeltas(character.getDx(), -1);
            } else if (event.getCode() == KeyCode.DOWN && character.getCharacter().getY() < 768 - CHARACTER_LENGTH) {
                character.setDeltas(character.getDx(), 1);
            } else if (event.getCode() == KeyCode.LEFT && character.getCharacter().getX() > 0) {
                character.setDeltas(-1, character.getDy());
            } else if (event.getCode() == KeyCode.RIGHT && character.getCharacter().getX() < 1024 - CHARACTER_WIDTH) {
                character.setDeltas(1, character.getDy());
            }
            if (character.getCharacter().getX() < 0) {
                character.getCharacter().setX(0);
            } else if (character.getCharacter().getX() > 1024 - CHARACTER_WIDTH) {
                character.getCharacter().setX(1024 - CHARACTER_WIDTH);
            }
            if (character.getCharacter().getY() < 0) {
                character.getCharacter().setY(0);
            } else if (character.getCharacter().getY() > 768 - CHARACTER_LENGTH) {
                character.getCharacter().setY(768 - CHARACTER_LENGTH);
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
                character.deathAndHealth(deathText, charHealth, Main.this);
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
        Button exit = new Button("X");
        exit.setLayoutX(980);
        root.getStylesheets().add("main/buttons.css");
        exit.getStyleClass().add("buttons");
        exit.setOnAction(event -> cleanUp());
        pointsLabel.setFont(new Font("Arial", 50));
        charHealth.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 17));
        bulletLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 17));
        bulletLabel.setTextFill(Paint.valueOf("red"));
        charHealth.setTextFill(Paint.valueOf("red"));
        pointsLabel.textProperty().bind(points.asString());
        bulletLabel.textProperty().setValue(Double.toString(character.getWeapon().getBullets())
                .substring(0, Double.toString(character.getWeapon().getBullets()).length() - 2));
        root.getChildren().addAll(backGround, character.getCharacter(),
                pointsLabel, deathText, charHealth, bulletLabel, currentGun, bulletsImg, hearth, exit);
        deathText.setTranslateX(500);
        deathText.setTranslateY(500);
        charHealth.setTranslateX(230);
        charHealth.setLayoutY(15);
        bulletLabel.setLayoutX(325);
        bulletLabel.setLayoutY(15);
        currentGun.setFill(character.getWeapon().getWeapRec().getFill());
        currentGun.setX(380);
        bulletsImg.setX(280);
        hearth.setX(180);

    }

    /**
     * Generate enemies
     */
    public void generateEnemies() {
        Random dir = new Random();
        int dir1 = dir.nextInt(4);
        int zombieLevel = dir.nextInt(10);
        Enemy enemy = null;
        if (dir1 == 0) {
            //Up
            if (zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(-400, -100));
            } else {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(-400, -100));
            }
        } else if (dir1 == 1) {
            //Down
            if (zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(900, 1300));
            } else {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-400, 1424),
                        ThreadLocalRandom.current().nextInt(900, 1300));
            }
        } else if (dir1 == 2) {
            //Right
            if (zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(1100, 1400),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            } else {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(1100, 1400),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
        } else if (dir1 == 3) {
            //left
            if (zombieLevel < 9) {
                enemy = new Enemy(ThreadLocalRandom.current().nextInt(-300, -100),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            } else {
                enemy = new EnemyBoss(ThreadLocalRandom.current().nextInt(-300, -100),
                        ThreadLocalRandom.current().nextInt(-768, 768));
            }
        }

        enemies.add(enemy);
        root.getChildren().add(enemy.getEnemyRect());
    }

    /**
     * Checks collision for zombies
     *
     * @param e - enemy instance
     */
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

    /**
     * Zombies death procedure
     *
     * @param enemy - enemy instance
     */
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

    /**
     * Wipes all the lists and info
     */
    public void cleanUp() {
        droppedWeapons = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        points = new SimpleIntegerProperty(0);
        root.getChildren().removeAll();
        character.setHealth(10000);
        mainMenu.cleanUp();
    }

    /**
     * @return The active bullets list
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Add bullets to the list
     *
     * @param bullet Bullet instance
     */
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    /**
     * Adds dropped weapon the to list
     *
     * @param weapon Weapon instance
     */
    public void addWeapon(Weapon weapon) {
        droppedWeapons.add(weapon);
    }

    /**
     * Sets Bullet list
     *
     * @param bullets Bullet List
     */
    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     * @return Main menu instance
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @return label of Bullet amount
     */
    public Label getBulletLabel() {
        return bulletLabel;
    }

    public void setCurrentGun(Rectangle currentGun) {
        this.currentGun.setFill(currentGun.getFill());
    }
}
