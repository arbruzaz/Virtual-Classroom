import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Profile extends BorderPane {
    private Label name, surname, address, email, tel;
    private TextField nField, surField, adField, emField, tField;
    private Button enter, close;

    public Profile(Stage window, User user) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        name = new Label("Name : ");
        nField = new TextField();
        nField.setPromptText("Name");

        grid.add(name, 0, 0);
        grid.add(nField, 1, 0);

        surname = new Label("Surname : ");
        surField = new TextField();
        surField.setPromptText("Surname");

        grid.add(surname, 0, 1);
        grid.add(surField, 1, 1);

        address = new Label("Address : ");
        adField = new TextField();
        adField.setPromptText("Address");

        grid.add(address, 0, 2);
        grid.add(adField, 1, 2);

        email = new Label("E-mail : ");
        emField = new TextField();
        emField.setPromptText("Email");

        grid.add(email, 0, 3);
        grid.add(emField, 1, 3);

        tel = new Label("Tel : ");
        tField = new TextField();
        tField.setPromptText("Tel");

        grid.add(tel, 0, 4);
        grid.add(tField, 1, 4);
        grid.setAlignment(Pos.CENTER);

        enter = new Button("Enter");
        enter.setOnAction(e -> {
            if (!nField.getText().equals(null) && !(nField.getText().equals(user.getName()))) {
                user.setName(nField.getText());
            }
            if (!surField.getText().equals(null) && !(surField.getText().equals(user.getSurname()))) {
                user.setSurname(surField.getText());
            }
            if (!adField.getText().equals(null) && !(adField.getText().equals(user.getAddress()))) {
                user.setAddress(adField.getText());
            }
            if (!emField.getText().equals(null) && !(emField.getText().equals(user.getEmail()))) {
                user.setEmail(emField.getText());
            }
            if (!tField.getText().equals(null) && !(tField.getText().equals(user.getTel()))) {
                user.setTel(tField.getText());
            }
            try {
                AboutUser.updateUser(user);
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            try {
                window.setScene(new Scene(new Dashboard(window, user), 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        close = new Button("Close");
        close.setOnAction(e -> {
            try {
                window.setScene(new Scene(new Dashboard(window, user), 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        window.setOnCloseRequest(e -> {
            try {
                window.setScene(new Scene(new Dashboard(window, user), 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        
        HBox btn = new HBox(10, enter,close);
        btn.setAlignment(Pos.CENTER);
        
        VBox vb = new VBox(15);
        vb.getChildren().addAll(grid,btn);
        vb.setAlignment(Pos.CENTER);
        setCenter(vb);
    }
    
}