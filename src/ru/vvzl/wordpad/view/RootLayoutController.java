package ru.vvzl.wordpad.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ru.vvzl.wordpad.Main;

import java.io.File;


public class RootLayoutController {
    private File file = null;
    private Main mainApp;


    public void setMain(Main mainApp){
        this.mainApp = mainApp;
    }
    @FXML
    private void handleCreate(){
        file = null;
        mainApp.cleaFile();
    }
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые документы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );
        file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if(file != null)
            mainApp.openFile(file);
    }

    @FXML
    private void handleSave(){

    }

    @FXML
    private void handleSaveAs(){
        file = mainApp.saveFile();

    }

    @FXML
    private void handleExit(){
        System.exit(0);
    }


}

//        MenuItem itemCreate = new MenuItem("Создать");
//        itemCreate.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
//        MenuItem itemOpen = new MenuItem("Открыть...");
//        itemOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

//        MenuItem itemSave = new MenuItem("Сохранить");
//        itemSave.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
//        itemSave.setOnAction((event)->{
//            String s = textArea.getText();
//            if(file != null ){
//
//                try (FileWriter fileWriter = new FileWriter(file) ){
//                    fileWriter.write(s);
//
//                } catch (IOException ex) {
//                    System.exit(0);
//                }
//            }else{
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt")
//                );
//                file = fileChooser.showSaveDialog(primaryStage);
//                if(file != null)
//                    saveFile(s);
//                }
//
//        });
//
//        MenuItem itemSaveAs = new MenuItem("Сохранить как");
//        itemSaveAs.setOnAction((event)->{
//
//        });
//
//        MenuItem itemProperties = new MenuItem("Параметры Страницы");
//        itemProperties.setOnAction((event)->System.exit(0));
//
//        MenuItem itemPrint = new MenuItem("Печать...");
//        itemPrint.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
//        itemPrint.setOnAction((event)-> {
//            Printer printer = Printer.getDefaultPrinter();
//            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL_OPPOSITES);
//            double scaleX = pageLayout.getPrintableWidth() / textArea.getBoundsInParent().getWidth();
//            double scaleY = pageLayout.getPrintableHeight() / textArea.getBoundsInParent().getHeight();
//            textArea.getTransforms().add(new Scale(scaleX, scaleY));
//
//
//            PrinterJob job = PrinterJob.createPrinterJob();
//            if (job != null && job.showPrintDialog(primaryStage.getScene().getWindow())){
//                boolean success = job.printPage(textArea);
//                if (success) {
//                    job.endJob();
//                }
//            }
//        });
//
//        MenuItem itemExit = new MenuItem("Выход");
//        itemExit.setOnAction((event)->System.exit(0));
//
//        menuFile.getItems().addAll(itemCreate,itemOpen,itemSave,itemSaveAs,
//                new SeparatorMenuItem(),itemProperties,itemPrint,new SeparatorMenuItem(),
//                itemExit);
//
//
//        Menu menuEdit = new Menu("Правка");
//
//        MenuItem itemCancel = new MenuItem("Отмена");
//        itemCancel.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
//        itemCancel.setOnAction((event)->System.exit(0));
//
//        MenuItem itemCut = new MenuItem("Вырезать");
//        itemCut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
//        itemCut.setOnAction((event)->System.exit(0));
//
//        MenuItem itemCopy = new MenuItem("Копировать");
//        itemCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
//        itemCopy.setOnAction((event)->System.exit(0));
//
//        MenuItem itemInsert = new MenuItem("Вставить");
//        itemInsert.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
//        itemInsert.setOnAction((event)->System.exit(0));
//
//
//        MenuItem itemDel = new MenuItem("Удалить");
//        itemDel.setAccelerator(KeyCombination.keyCombination("Del"));
//        itemDel.setOnAction((event)->System.exit(0));
//
//        MenuItem itemFind = new MenuItem("Найти...");
//        itemFind.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
//        itemFind.setOnAction((event)->System.exit(0));
//
//        MenuItem itemFindFurther = new MenuItem("Найти далее");
//        itemFindFurther.setAccelerator(KeyCombination.keyCombination("F3"));
//        itemFindFurther.setOnAction((event)->System.exit(0));
//
//        MenuItem itemChange = new MenuItem("Заменить...");
//        itemChange.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
//        itemChange.setOnAction((event)->System.exit(0));
//
//        MenuItem itemGoOver = new MenuItem("Перейти...");
//        itemGoOver.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
//        itemGoOver.setOnAction((event)->System.exit(0));
//
//        MenuItem itemHighlight = new MenuItem("Выделить все");
//        itemHighlight.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
//        itemHighlight.setOnAction((event)->System.exit(0));
//
//        MenuItem itemTimeAndDate = new MenuItem("Время и дата");
//        itemTimeAndDate.setAccelerator(KeyCombination.keyCombination("F5"));
//        itemTimeAndDate.setOnAction((event)->System.exit(0));
//
//        menuEdit.getItems().addAll(itemCancel, new SeparatorMenuItem(), itemCut, itemCopy,
//                itemInsert, itemDel, new SeparatorMenuItem(), itemFind, itemFindFurther,
//                itemChange, itemGoOver, new SeparatorMenuItem(), itemHighlight, itemTimeAndDate);
//
//        Menu menuFormat = new Menu("Формат");
//
//        MenuItem itemChWord = new MenuItem("Перенос по словам");
//        itemChWord.setOnAction((event)->System.exit(0));
//
//        MenuItem itemFont = new MenuItem("Шрифт...");
//        itemFont.setOnAction((event)->System.exit(0));
//
//        menuFormat.getItems().addAll(itemChWord, itemFont);
//
//        Menu menuView = new Menu("Вид");
//        MenuItem itemStatus = new MenuItem("Строка состояния");
//        itemStatus.setOnAction((event)->System.exit(0));
//        menuView.getItems().addAll(itemStatus);
//
//        Menu menuHelp = new Menu("Справка");
//
//        MenuItem itemWatchHelp = new MenuItem("Посмотреть справку");
//        itemWatchHelp.setOnAction((event)->System.exit(0));
//
//        MenuItem itemAbout = new MenuItem("О программе");
//        itemAbout.setOnAction((event)->{
//            Stage aboutStage = new Stage();
//            aboutStage.initStyle(StageStyle.UNDECORATED);
//            aboutStage.setTitle("О программе");
//
//            aboutStage.setScene(new Scene(new StackPane(),300,300));
//            aboutStage.show();
//        });
//
//        menuHelp.getItems().addAll(itemWatchHelp, new SeparatorMenuItem(), itemAbout);
//
//        menu.getMenus().addAll(menuFile,menuEdit,menuFormat,menuView,menuHelp);
//
//        bPane.setTop(menu);
//
//
//        bPane.setCenter(stackPane);
//
//
//
//        primaryStage.setTitle("Блокнот");
//        primaryStage.setScene(new Scene(bPane, 700, 600));
//        primaryStage.show();