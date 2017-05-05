package com.sda.collection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FxDemo extends Application {

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("hello world");
        Scene scene = new Scene(button, 100, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
