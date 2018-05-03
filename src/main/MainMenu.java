package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private Scene scene;
    private Main main;
    private Stage stage;
    private Scene scene2;
    @Override
    public void start(Stage stage){
        Button ezBtn = new Button();
        Button medBtn = new Button();
        Button hardBtn = new Button();
        Pane layout = new Pane();
        layout.getChildren().addAll(ezBtn,medBtn,hardBtn);
        ezBtn.textProperty().setValue("EASY");
        medBtn.textProperty().setValue("MEDIUM");
        hardBtn.textProperty().setValue("XTREME");
        hardBtn.setLayoutY(64);
        medBtn.setLayoutY(32);
        this.stage = stage;
        Scene mainScene = new Scene(layout, 1024, 768);
        main = new Main(this);
        this.scene = mainScene;
        scene2 = new Scene(main.root, 1024, 768);
        stage.setResizable(false);
        ezBtn.setOnAction(e -> {
            main.starterino(5);
            stage.setScene(scene2);
            stage.show();
        });
        medBtn.setOnAction(e -> {
            main.starterino(3);
            stage.setScene(scene2);
            stage.show();
        });
        hardBtn.setOnAction(e -> {
            main.starterino(1);
            stage.setScene(scene2);
            stage.show();
        });
        stage.setScene(mainScene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
    public void cleanUp()
    {
        stage.setScene(scene);
        this.main = new Main(this);
        scene2 = new Scene(main.root, 1024, 768);
        System.out.println(main);
        System.out.println(main.root);
        System.out.println(scene2);
        System.out.println("CLEANUP2");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
