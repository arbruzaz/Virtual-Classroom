import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox
 */
public class AlertBoxError {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label alertlogin = new Label(message);

        Button closebtn = new Button("Close the window");
        closebtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getStylesheets().add("Gui.css");
        layout.getChildren().addAll(alertlogin, closebtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static String getUsernameforgot() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Forgot Password?");
        TextField getuser = new TextField();
        Label alertlogin = new Label("Forgot Your Password? Type you Username.");

        Button closebtn = new Button("Enter");
        closebtn.setOnAction(e -> {
            if (getuser.getText().equals("") || getuser.getText().equals(null)) {
                AlertBoxError.display("Error", "Please Fillin Username");
            } else
                try {
                    if (check(getuser.getText()) == false) {
                        AlertBoxError.display("Error", "Wrong Username");
                    } else
                        window.close();
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
        });

        Button exit = new Button("Back");
        exit.setOnAction(e -> window.close());
        
        window.setOnCloseRequest(e -> window.close());

        VBox layout = new VBox(10);
        layout.getStylesheets().add("Gui.css");
        layout.getChildren().addAll(getuser,alertlogin,closebtn,exit);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,400,300);
        window.setScene(scene);
        window.showAndWait();
        return getuser.getText();
    }

    public static boolean check(String username) throws IOException, ClassNotFoundException {
        File file = new File("userdata.dat");
        User a = null;
        boolean userexist = false;
        if (file.exists()) {
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin);
            while (fin.available() != 0) {
                a = (User) in.readObject();
                if (username.equals(a.getUsername())) {
                    userexist = true;
                    break;
                }
            }
            in.close();
        }
        if(userexist == false){return userexist;}
        else  {return userexist;}
    }

    static Boolean exor;
    
    public static Boolean confirm(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label alert = new Label(message);

        Button yesbtn = new Button("Yes");
        yesbtn.setOnAction(e -> {
            exor = true;
            window.close();
        });
        
        Button nobtn = new Button("No");
        nobtn.setOnAction(e -> {
            exor = false;
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(alert,yesbtn,nobtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,200);
        layout.getStylesheets().add("Gui.css");
        window.setScene(scene);
        window.showAndWait();
        
        return exor;
    }

    public static String post() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Set Picture");
        TextField getuser = new TextField();
        Label alertlogin = new Label("Image URL");

        Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            if (getuser.getText().equals("") || getuser.getText().equals(null)) {
                AlertBoxError.display("Error", "Please Fillin Box");
            }
            else window.close();
        });

        Button exit = new Button("Back");
        exit.setOnAction(e -> window.close());
        
        window.setOnCloseRequest(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(getuser,alertlogin,enter,exit);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,400,300);
        layout.getStylesheets().add("Gui.css");
        window.setScene(scene);
        window.showAndWait();
        return getuser.getText();
    }

} 