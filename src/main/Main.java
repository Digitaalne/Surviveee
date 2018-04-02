package main;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

    private Rectangle charRec;

    private Character character;
    Group root;


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
        charRec = new Rectangle();
        charRec.setHeight(80);
        charRec.setWidth(50);
        Image img = new Image("main/mainCharacter.png");
        charRec.setFill(new ImagePattern(img));
        character = new Character(charRec, 3, 500, 500);
        root.getChildren().add(character.getCharacter());

        root.setOnKeyPressed(event-> {
            if(event.getCode() == KeyCode.UP)
            {
                character.setDeltas(0, -1);
            }
            if(event.getCode() == KeyCode.DOWN)
            {
                character.setDeltas(0, 1);
            }
            if(event.getCode() == KeyCode.LEFT)
            {
                character.setDeltas(-1, 0);
            }
            if(event.getCode() == KeyCode.RIGHT)
            {
                character.setDeltas(1, 0);
            }
        });
        root.setOnKeyReleased(event -> {
            character.setDeltas(0, 0);
        });
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                character.move();
            }
        }.start();
        character.setDeltas(0,  0);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
