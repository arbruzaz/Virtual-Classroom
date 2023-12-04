import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Loginpage extends BorderPane {
    private PasswordField passin;
    private String showpass;
    Dashboard dashboard;
    Registerpage registerpage;
    Reset reset ;

    public Loginpage(Stage window) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.getStylesheets().add("Gui.css");
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

        Button loginbtn = new Button("Sign in");
        loginbtn.setOnAction(e -> {

            try {
                if (userin.getText() == null || passin.getText() == null) {
                    AlertBoxError.display("Error", "Please fillin Username or Password");
                } else {
                    if (AboutUser.login(userin.getText(), passin.getText()) == true) {
                        User user = null;
                        user = getUser(userin.getText(), passin.getText());
                        dashboard = new Dashboard(window,user);
                        dashboard.getStylesheets().add("Gui.css");
                        window.setScene(new Scene(dashboard, 1280, 720));
                    } else {
                        AlertBoxError.display("Error", "Invalid Username or Password");
                    }
                }
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        Hyperlink click = new Hyperlink("Click here");
        TextFlow flow = new TextFlow(new Text("Don't have an account? "), click);
        click.setOnAction(e -> {
            try {
                registerpage = new Registerpage(window);
                registerpage.getStylesheets().add("Gui.css");
                window.setScene(new Scene(registerpage, 400, 400));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        Hyperlink clickf = new Hyperlink("Click here");
        TextFlow flowf = new TextFlow(new Text("Forgot a Password? "), clickf);
        clickf.setOnAction(e -> {
            try {
               String name = AlertBoxError.getUsernameforgot();
               if(name.equals(null) || name.equals("") || !AlertBoxError.check(name)){

               }
               else{
                   reset = new Reset(window,AboutUser.forgotPassword(name),false);
                   reset.getStylesheets().add("Gui.css");

                   window.setScene(new Scene(reset));
               }
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        HBox forget = new HBox(10);
        forget.setAlignment(Pos.CENTER);
        forget.getChildren().addAll(flowf);
        grid.add(forget, 1, 5);

        Button exit = new Button("Close the program");
        exit.setOnAction(e -> {
            boolean exor = AlertBoxError.confirm("Exit", "Are you sure?");
            if (exor)
                window.close();
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
        hbBtn.getChildren().addAll(loginbtn, exit);
        grid.add(hbBtn, 1, 4);
        HBox regis = new HBox(10);
        regis.setAlignment(Pos.CENTER);
        regis.getChildren().addAll(flow);
        grid.add(regis, 1, 6);

        setCenter(grid);
    }

    private User getUser(String username, String password) throws IOException, ClassNotFoundException {
        File file = new File("userdata.dat");
        User a = null;
        if(file.exists()){
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin); 
            while(fin.available() != 0){
                a = (User)in.readObject();
                if(username.equals(a.getUsername()) && password.equals(a.getPassword())){
                    break;
                }
            }
            in.close();
        }
        return a;
    }
}