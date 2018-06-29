package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PartsOfApp parts = new PartsOfApp();
        BorderPane bPane = new BorderPane();
        MenuBar menuBar = parts.addMenu();

        bPane.setTop(menuBar);
        bPane.setCenter(parts.addStackPane());


        primaryStage.setTitle("Блокнот");
        primaryStage.setScene(new Scene(bPane, 700, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
