package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class MainMenu extends Application {
    /**
     * The instance of main menu scene
     */
    private Scene scene;
    /**
     * The instance of Main
     */
    private Main main;
    /**
     * The stage
     */
    private Stage stage;
    /**
     * Game scene
     */
    private Scene scene2;
    /**
     * the background of the main menu
     */
    private ImageView backGround = new javafx.scene.image.ImageView("resources/mainMenu/mainMenuBackground.png");
    /**
     * Starts the game and shows the menu
     */

    @Override
    public void start(Stage stage){
        Button ezBtn = new Button("EASY");
        Button medBtn = new Button("MEDIUM");
        Button hardBtn = new Button("EXTREME");
        Pane layout = new Pane();
        layout.getChildren().addAll(backGround, ezBtn,medBtn,hardBtn);
        hardBtn.setMinSize(260, 60);
        hardBtn.setMaxSize(260, 60);
        ezBtn.setMinSize(260, 60);
        ezBtn.setMaxSize(260, 60);
        medBtn.setMinSize(260, 60);
        medBtn.setMaxSize(260, 60);
        ezBtn.setLayoutX(382);
        ezBtn.setLayoutY(377);
        hardBtn.setLayoutX(382);
        hardBtn.setLayoutY(559);
        medBtn.setLayoutX(382);
        medBtn.setLayoutY(468);
        this.stage = stage;
        Scene mainScene = new Scene(layout, 1024, 768);
        main = new Main(this);
        this.scene = mainScene;
        scene.getStylesheets().add("main/buttons.css");
        ezBtn.getStyleClass().add("buttons");
        medBtn.getStyleClass().add("buttons");
        hardBtn.getStyleClass().add("buttons");

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

    /**
     *
     * @return Main menu Scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     *
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Cleans up and creates new main
     */
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
