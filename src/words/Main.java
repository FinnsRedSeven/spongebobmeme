package words;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {


    private Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createScene());
        stage.setTitle("Text Modifiers");
        stage.show();

    }

    private Scene createScene(){

        StackPane root = new StackPane();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        HBox textAreas = new HBox();
        VBox box = new VBox();
        TextBox pasteBox = new TextBox();
        TextBox copyBox = new TextBox();
        pasteBox.setPrefHeight(600);
        copyBox.setPrefHeight(600);
        pasteBox.prefWidthProperty().bind(stage.widthProperty().divide(2));
        copyBox.prefWidthProperty().bind(stage.widthProperty().divide(2));
        ComboBox<Option> comboBox = new ComboBox<>();
        copyBox.setEditable(Boolean.FALSE);
        Button translateText = new Button("Transform");
        textAreas.getChildren().addAll(pasteBox, copyBox);
        box.getChildren().addAll(textAreas,comboBox,translateText);
        root.getChildren().add(box);
        pasteBox.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode().equals(KeyCode.ENTER) || (event.isControlDown() && event.getCode().equals(KeyCode.V))){
                event.consume();
                translateText.fire();

            }
        });
        pasteBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(Boolean.TRUE)) Platform.runLater(pasteBox::selectAll);
        });
        comboBox.getItems().add(new RandomCase());
        comboBox.getSelectionModel().select(0);
        translateText.setOnAction(event -> {
            String translated;
            translated = comboBox.getSelectionModel().getSelectedItem().translateText(pasteBox.getText());
            content.putString(translated);
            clipboard.setContent(content);
            copyBox.setText(translated);
        });

        Scene scene = new Scene(root,1280,560);
        scene.getStylesheets().addAll("style1.css");

        return scene;
    }



}
