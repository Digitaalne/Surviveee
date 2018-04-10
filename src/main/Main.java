package main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {

    private Rectangle charRec;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private Character character;
    Group root;
    //punktiosa
    private IntegerProperty points = new SimpleIntegerProperty(0);
    private Label pointsLabel = new Label();


    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        //Parent rootload = FXMLLoader.load(getClass().getResource("BasicLevel.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
        primaryStage.setResizable(false);
        root.setFocusTraversable(true);
        root.requestFocus();
        //ajutine
        charRec = new Rectangle();
        charRec.setHeight(80);
        charRec.setWidth(50);
        Image img = new Image("main/mainCharacter.png");
        //
        charRec.setFill(new ImagePattern(img));
        character = new Character(charRec, 3, 500, 500);
        //hiire klikkimiseks
        Canvas canvas = new Canvas();
        canvas.setHeight(10000);
        canvas.setWidth(10000);
        root.getChildren().add(canvas);
        root.getChildren().add(character.getCharacter());
        //tulistamine - mitte enam
        root.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();
            character.shoot(x, y);
        });
        //liikumine
        root.setOnKeyPressed(event-> {
            if(event.getCode() == KeyCode.UP)
            {
                character.setDeltas(character.getDx(), -1);
            }
            if(event.getCode() == KeyCode.DOWN)
            {
                character.setDeltas(character.getDx(), 1);
            }
            if(event.getCode() == KeyCode.LEFT)
            {
                character.setDeltas(-1, character.getDy());
            }
            if(event.getCode() == KeyCode.RIGHT)
            {
                character.setDeltas(1, character.getDy());
            }
        });

        root.setOnKeyReleased(event -> {
            character.setDeltas(0, 0);
        });
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                character.move();
                character.playerDirection();
                for (Enemy e: enemies) {
                    e.move();
                    e.moveTowardsCharacter(character.getCharacter().getX(), character.getCharacter().getY());
                    enemiesCollision(e);
                }
            }
        }.start();
        character.setDeltas(0,  0);
        //Enemies spawning
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(5),
                        event -> {
                            generateEnemies();
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void generateEnemies()
    {
        Enemy enemy = new Enemy(ThreadLocalRandom.current().nextInt(-1024,-10),
                ThreadLocalRandom.current().nextInt(-768,-10));
        enemies.add(enemy);
        root.getChildren().add(enemy.getEnemyRect());
    }

    public void enemiesCollision(Enemy e)
    {

        //Kui inimene saab pihta zombiga
        if(e.getEnemyRect().getBoundsInParent().intersects(character.getCharacter().getBoundsInParent()))
        {
            character.setHealth(character.getHealth() - e.getDamage());
            e.pushBack();
        }
        //Kui vaenlane saab pihta kuuliga
        for(Bullet bullet : bullets) {
            if (e.getEnemyRect().getBoundsInParent().intersects(bullet.getBulletRect().getBoundsInParent())) {
                e.setHealth(e.getHealth() - bullet.getDamage());
            }
        }

    }
    public void zombieDeath(Enemy enemy)
    {
        if (enemy.getHealth()<= 0)
        {
            enemy.deathDrop();
            enemies.remove(enemy);
            root.getChildren().remove(enemy.getEnemyRect());
            if(enemy.getType().equals(Enemy.Type.BASIC))
                points.add(1);
            else if(enemy.getType().equals(Enemy.Type.BOSS))
                points.add(10);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
