import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.geometry.Insets;

public class Reset extends BorderPane {
    private String showpass;
    private boolean stage;

    public Reset(Stage window, User user, boolean stage) {
        this.stage = stage;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Reset Password");
        grid.add(title, 0, 0, 2, 1);

        Label userlab = new Label("Username : ");
        grid.add(userlab, 0, 1);

        TextField userin = new TextField();
        userin.setText(null);
        userin.setPromptText("Username");
        grid.add(userin, 1, 1);

        Label passlab1 = new Label("New - Password :");
        grid.add(passlab1, 0, 2);

        PasswordField passin = new PasswordField();
        passin.setText(null);
        passin.setPromptText("Password");
        grid.add(passin, 1, 2);

        Label passlab = new Label("Confirm - Password : ");
        grid.add(passlab, 0, 3);

        PasswordField passinc = new PasswordField();
        passinc.setText(null);
        passinc.setPromptText("Password");
        grid.add(passinc, 1, 3);
        grid.setAlignment(Pos.CENTER);

        Label emaillLabel = new Label("Email : ");
        grid.add(emaillLabel, 0, 4);

        TextField emailc = new TextField();
        emailc.setText(null);
        emailc.setPromptText("Email");
        grid.add(emailc, 1, 4);
        grid.setAlignment(Pos.CENTER);
        Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            if ((passin.getText().equals(null)) || (passin.getText().equals("")) || (passinc.getText().equals(null))
                    || (passinc.getText().equals("")) || (emailc.getText().equals(null))
                    || (emailc.getText().equals("")) || (userin.getText().equals(null))
                    || (userin.getText().equals(""))) {
                AlertBoxError.display("Error", "Please Fillin Boxes.");
            } else if (passin.getText().equals(user.getPassword()) || passinc.getText().equals(user.getPassword())) {
                AlertBoxError.display("Error", "Cannot use old password.");
            } else if (!(userin.getText().equals(user.getUsername())) || !(emailc.getText().equals(user.getEmail()))) {
                AlertBoxError.display("Error", "Wrong Username Or Email.");
            } else if (passin.getText().equals(passinc.getText())) {
                user.setPassword(passin.getText());
                try {
                    AboutUser.updateUser(user);
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                AlertBoxError.display("Please Login", "Please Login Again!");
                try {
                    window.setScene(new Scene(new Loginpage(window), 400, 400));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Button exit = new Button("Close");
        exit.setOnAction(e -> {
            if (this.stage == true) {
                try {
                    window.setScene(new Scene(new Dashboard(window, user), 1280, 720));
                } catch (ClassNotFoundException | IOException e1) {

                    e1.printStackTrace();
                }
            } else {
                try {
                    window.setScene(new Scene(new Loginpage(window), 400, 400));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        Hyperlink showpasslink = new Hyperlink("Show password");
        
        showpasslink.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            showpass = passin.getText();
            passin.setText(null);
            passin.setPromptText(showpass);
        });

        showpasslink.addEventFilter(MouseEvent.MOUSE_RELEASED, e ->{
            passin.setText(showpass);
            passin.setPromptText("Password");
        });
        grid.add(showpasslink, 0, 5);


        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(enter, exit);
        grid.add(hbBtn, 1, 5);

        setCenter(grid);
    }
    
}