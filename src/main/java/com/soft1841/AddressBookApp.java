package com.soft1841;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.soft1841.factory.ConText;

public class
AddressBookApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String[] styles = {"/css/style.css","org/kordamp/bootstrapfx/bootstrapfx.css"};
        primaryStage = ConText.stageFactory.creatStage("通讯录","/fxml/homePage.fxml",styles,440,930);
        primaryStage.getIcons().add(new Image("/img/通讯录.png"));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
