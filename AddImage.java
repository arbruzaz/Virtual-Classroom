import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddImage extends BorderPane {
    Dashboard dashboard;
    Dashboard dashboard2;
    Dashboard dashboard3;
    public AddImage(Stage window, User user) {
        window.setTitle("Set Picture");
        TextField getuser = new TextField();
        Label alertlogin = new Label("Image URL");

        Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            if (getuser.getText().equals("") || getuser.getText().equals(null)) {
                AlertBoxError.display("Error", "Please Fillin Box");
            } else {
                user.setPicture(getuser.getText());
                try {
                    AboutUser.updateUser(user);
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    dashboard = new Dashboard(window, user);
                    dashboard.getStylesheets().add("Gui.css");
                    window.setScene(new Scene(dashboard, 1280, 720));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Button exit = new Button("Back");
        exit.setOnAction(e -> {
            try {
                dashboard2 = new Dashboard(window, user);
                dashboard2.getStylesheets().add("Gui.css");
                window.setScene(new Scene(dashboard2, 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        
        window.setOnCloseRequest(e -> {
            try {
                dashboard3 = new Dashboard(window, user);
                dashboard3.getStylesheets().add("Gui.css");
                window.setScene(new Scene(dashboard3, 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(getuser,alertlogin,enter,exit);
        layout.setAlignment(Pos.CENTER);

        setCenter(layout);
    }
}