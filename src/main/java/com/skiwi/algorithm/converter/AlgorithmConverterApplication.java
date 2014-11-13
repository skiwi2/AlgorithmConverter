package com.skiwi.algorithm.converter;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


final public class AlgorithmConverterApplication extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Algorithm Converter");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
