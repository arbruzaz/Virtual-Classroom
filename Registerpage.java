import java.io.FileNotFoundException;
import java.io.IOException;

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
import javafx.geometry.Insets;

public class Registerpage extends BorderPane {
    private PasswordField passin;
    private String showpass;

    Loginpage loginpage ;
    Loginpage loginpage2;
    public Registerpage(Stage window) throws FileNotFoundException, IOException, ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Welcome to Virtual Classroom");
        grid.add(title, 0, 0, 2, 1);

        Label userlab = new Label("Username:");
        grid.add(userlab, 0, 1);

        TextField userin = new TextField();
        userin.setText(null);
        userin.setPromptText("Username");
        grid.add(userin, 1, 1);

        Label passlab = new Label("Password:");
        grid.add(passlab, 0, 2);

        passin = new PasswordField();
        passin.setText(null);
        passin.setPromptText("Password");
        grid.add(passin, 1, 2);

        Label passlabcon = new Label("Confirm-Password:");
        grid.add(passlabcon, 0, 3);

        PasswordField passincon = new PasswordField();
        passincon.setText(null);
        passincon.setPromptText("Confirm Password");
        grid.add(passincon, 1, 3);

        Button regisbtn = new Button("Register");
        regisbtn.setOnAction(e -> {
            try {
                if (userin.getText() == null || passin.getText() == null || passincon.getText() == null) {
                    AlertBoxError.display("Please fillin Username or Password", "Please Fillin Username or Password");
                } else if( passin.getText().equals(passincon.getText())){
                    AboutUser.register(userin.getText(), passin.getText());
                    userin.setText(null);
                    passin.setText(null);
                    passincon.setText(null);
                    loginpage = new Loginpage(window);
                    loginpage.getStylesheets().add("Gui.css");
                    window.setScene(new Scene(loginpage, 400, 400));
                }  else {
                    AlertBoxError.display("Error", "Password has to same with Confirm Password");
                }
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        Button exit = new Button("Back");
        exit.setOnAction(e -> {
            try {
                loginpage2 = new Loginpage(window);
                loginpage2.getStylesheets().add("Gui.css");
                window.setScene(new Scene(loginpage2, 400, 400));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        window.setOnCloseRequest(e -> {
            e.consume();
            boolean exor = AlertBoxError.confirm("Exit", "Are you sure?");
            if (exor)
                window.close();
        });

        Hyperlink showpasslink = new Hyperlink("Show password");
        showpasslink.addEventFilter(MouseEvent.MOUSE_PRESSED, e ->{
            showpass = passin.getText();
            passin.setText(null);
            passin.setPromptText(showpass);
        });

        showpasslink.addEventFilter(MouseEvent.MOUSE_RELEASED, e ->{
            passin.setText(showpass);
            passin.setPromptText("Password");
        });
        grid.add(showpasslink, 0, 4);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(regisbtn);
        grid.add(hbBtn, 1, 4);
        HBox exitbtn = new HBox(10);
        exitbtn.setAlignment(Pos.CENTER);
        exitbtn.getChildren().addAll(exit);
        grid.add(exitbtn, 1, 5);

        setCenter(grid);
    }

}