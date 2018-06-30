package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class Main extends Application {
    private File file = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane bPane = new BorderPane();
        MenuBar menu = new MenuBar();
        menu.setPadding(Insets.EMPTY);
        StackPane stackPane = new StackPane();
        TextArea textArea = new TextArea();
        stackPane.getChildren().addAll(textArea);


        Menu menuFile = new Menu("Файл");


        MenuItem itemCreate = new MenuItem("Создать");
        itemCreate.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        itemCreate.setOnAction((event)-> {
            textArea.setText("");
            file = null;
        });

        MenuItem itemOpen = new MenuItem("Открыть...");

        itemOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        itemOpen.setOnAction((event) -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Текстовые документы", "*.txt"),
                    new FileChooser.ExtensionFilter("Все файлы", "*.*")
            );

            file = fileChooser.showOpenDialog(primaryStage);
            if(file != null) {
                try (FileInputStream bf = new FileInputStream(file)) {
                    byte[] buffer = new byte[bf.available()];
                    bf.read(buffer);
                    String s = new String(buffer, "Windows-1251");
                    textArea.setText(s);
                } catch (Exception e) {
                    System.exit(0);
                }
            }

        });

        MenuItem itemSave = new MenuItem("Сохранить");
        itemSave.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        itemSave.setOnAction((event)->{
            String s = textArea.getText();
            if(file != null ){

                try (FileWriter fileWriter = new FileWriter(file) ){
                    fileWriter.write(s);

                } catch (IOException ex) {
                    System.exit(0);
                }
            }else{
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt")
                );
                file = fileChooser.showSaveDialog(primaryStage);
                if(file != null)
                    saveFile(s);
                }

        });

        MenuItem itemSaveAs = new MenuItem("Сохранить как");
        itemSaveAs.setOnAction((event)->{
            String s = textArea.getText();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt")
            );
            file = fileChooser.showSaveDialog(primaryStage);
            if(file != null)
                saveFile(s);
        });

        MenuItem itemProperties = new MenuItem("Параметры Страницы");
        itemProperties.setOnAction((event)->System.exit(0));

        MenuItem itemPrint = new MenuItem("Печать...");
        itemPrint.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        itemPrint.setOnAction((event)-> {
            Printer printer = Printer.getDefaultPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
            double scaleX = pageLayout.getPrintableWidth() / textArea.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / textArea.getBoundsInParent().getHeight();
            textArea.getTransforms().add(new Scale(scaleX, scaleY));

            PrinterJob job = PrinterJob.createPrinterJob();

            if (job != null) {
                boolean success = job.showPrintDialog(primaryStage);
                if (success) {
                    job.endJob();
                }
            }
        });

        MenuItem itemExit = new MenuItem("Выход");
        itemExit.setOnAction((event)->System.exit(0));

        menuFile.getItems().addAll(itemCreate,itemOpen,itemSave,itemSaveAs,
                new SeparatorMenuItem(),itemProperties,itemPrint,new SeparatorMenuItem(),
                itemExit);


        Menu menuEdit = new Menu("Правка");

        MenuItem itemCancel = new MenuItem("Отмена");
        itemCancel.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
        itemCancel.setOnAction((event)->System.exit(0));

        MenuItem itemCut = new MenuItem("Вырезать");
        itemCut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        itemCut.setOnAction((event)->System.exit(0));

        MenuItem itemCopy = new MenuItem("Копировать");
        itemCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        itemCopy.setOnAction((event)->System.exit(0));

        MenuItem itemInsert = new MenuItem("Вставить");
        itemInsert.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        itemInsert.setOnAction((event)->System.exit(0));


        MenuItem itemDel = new MenuItem("Удалить");
        itemDel.setAccelerator(KeyCombination.keyCombination("Del"));
        itemDel.setOnAction((event)->System.exit(0));

        MenuItem itemFind = new MenuItem("Найти...");
        itemFind.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        itemFind.setOnAction((event)->System.exit(0));

        MenuItem itemFindFurther = new MenuItem("Найти далее");
        itemFindFurther.setAccelerator(KeyCombination.keyCombination("F3"));
        itemFindFurther.setOnAction((event)->System.exit(0));

        MenuItem itemChange = new MenuItem("Заменить...");
        itemChange.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        itemChange.setOnAction((event)->System.exit(0));

        MenuItem itemGoOver = new MenuItem("Перейти...");
        itemGoOver.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
        itemGoOver.setOnAction((event)->System.exit(0));

        MenuItem itemHighlight = new MenuItem("Выделить все");
        itemHighlight.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        itemHighlight.setOnAction((event)->System.exit(0));

        MenuItem itemTimeAndDate = new MenuItem("Время и дата");
        itemTimeAndDate.setAccelerator(KeyCombination.keyCombination("F5"));
        itemTimeAndDate.setOnAction((event)->System.exit(0));

        menuEdit.getItems().addAll(itemCancel, new SeparatorMenuItem(), itemCut, itemCopy,
                itemInsert, itemDel, new SeparatorMenuItem(), itemFind, itemFindFurther,
                itemChange, itemGoOver, new SeparatorMenuItem(), itemHighlight, itemTimeAndDate);

        Menu menuFormat = new Menu("Формат");

        MenuItem itemChWord = new MenuItem("Перенос по словам");
        itemChWord.setOnAction((event)->System.exit(0));

        MenuItem itemFont = new MenuItem("Шрифт...");
        itemFont.setOnAction((event)->System.exit(0));

        menuFormat.getItems().addAll(itemChWord, itemFont);

        Menu menuView = new Menu("Вид");
        MenuItem itemStatus = new MenuItem("Строка состояния");
        itemStatus.setOnAction((event)->System.exit(0));
        menuView.getItems().addAll(itemStatus);

        Menu menuHelp = new Menu("Справка");

        MenuItem itemWatchHelp = new MenuItem("Посмотреть справку");
        itemWatchHelp.setOnAction((event)->System.exit(0));

        MenuItem itemAbout = new MenuItem("О программе");
        itemAbout.setOnAction((event)->{
            Stage aboutStage = new Stage();
            aboutStage.initStyle(StageStyle.UNDECORATED);
            aboutStage.setTitle("О программе");

            aboutStage.setScene(new Scene(new StackPane(),300,300));
            aboutStage.show();
        });

        menuHelp.getItems().addAll(itemWatchHelp, new SeparatorMenuItem(), itemAbout);

        menu.getMenus().addAll(menuFile,menuEdit,menuFormat,menuView,menuHelp);

        bPane.setTop(menu);


        bPane.setCenter(stackPane);



        primaryStage.setTitle("Блокнот");
        primaryStage.setScene(new Scene(bPane, 700, 600));
        primaryStage.show();
    }
    private void saveFile(String s){

        try (FileWriter fileWriter = new FileWriter(file) ){
            fileWriter.write(s);

        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
