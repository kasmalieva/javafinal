package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        System.out.println("Application started!!");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        stage.setTitle("Counter app");
        stage.setScene(new Scene(root,300, 600));

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Application was closed!");
    }

    public static void main(String args[]){
        launch(args);
    }

}
