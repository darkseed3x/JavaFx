package ru.vvzl.wordpad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.vvzl.wordpad.view.RootLayoutController;
import ru.vvzl.wordpad.view.TextAreaController;

import java.io.*;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private TextArea textArea;
//    private File file = null;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Блокнот");

        this.primaryStage.getIcons().add(new Image("file:Resources/images/if_Wordpad_68003.png"));

        initRootLayout();

        initTextArea();


    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void initTextArea(){
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/TextAreaLayout.fxml"));

        AnchorPane anchorPane = (AnchorPane)loader.load();
        rootLayout.setCenter(anchorPane);

        TextAreaController controller = loader.getController();
        textArea = controller.getTextArea();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File saveFile(){

        String s = textArea.getText();
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.getExtensionFilters().addAll(
//                    new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt")
//            );
//            file = fileChooser.showSaveDialog(primaryStage);
//            if(file != null)
//                saveFile(s);
        File temp = null;
        FileWriter fileWriter;
        try {
            temp = File.createTempFile("temp-file",".tmp");
            fileWriter = new FileWriter(temp);

            fileWriter.write(s);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public void cleaFile(){
        textArea.clear();
    }
    public void openFile(File file) {
        try (FileInputStream bf = new FileInputStream(file)) {
            byte[] buffer = new byte[bf.available()];
            bf.read(buffer);
            String s = new String(buffer, "Windows-1251");
            textArea.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
