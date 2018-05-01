package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button();
        Pane layout = new Pane();
        layout.getChildren().add(button);
        button.textProperty().setValue("START GAME");
        Scene mainScene = new Scene(layout, 1024, 768);
        Main main = new Main();
        Scene scene2 = new Scene(main.root, 1024, 768);
        stage.setResizable(false);
        button.setOnAction(e -> {
            main.starterino();
            stage.setScene(scene2);
            stage.show();
            System.out.println("hii");
        });
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
