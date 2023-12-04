import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Guide extends BorderPane {
    private String subjectName;
    private ArrayList<Post> oldPost;
    private User user;

    public Guide(String subjectName, User user, Stage window) throws ClassNotFoundException, IOException {
        this.subjectName = subjectName;
        this.user = user;
        oldPost = new ArrayList<>();
        oldPost = Course.getPost();

        Label title = new Label("Clink to post.");
        Button postbtn = new Button("Post");
        Button backbtn = new Button("Back");

        postbtn.setOnAction(e -> {
            forpost();
            try {
                window.setScene(new Scene(new Guide(subjectName, user, window), 1280, 720));
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        backbtn.setOnAction(e -> {
            try {
                window.setScene(new Scene(new Dashboard(window, user)));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        VBox panel = new VBox(10, title, postbtn, backbtn);
        panel.setPadding(new Insets(50, 50, 50, 50));
        panel.setAlignment(Pos.CENTER);
        setRight(panel);

        VBox displaypost = new VBox(10);
        for (int i = 0; i < oldPost.size(); i++) {
            if (oldPost.get(i).getSubject().equals(subjectName)) {
                displaypost.getChildren().add(new Text(oldPost.get(i).getPost()));
            }
        }
        displaypost.setAlignment(Pos.TOP_CENTER);
        displaypost.setPadding(new Insets(100, 100, 100, 100));
        ScrollPane scrollpg = new ScrollPane(displaypost);
        setCenter(scrollpg);
    }

    public void forpost() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        Label titlescene = new Label("Please write the post.");

        TextField getPost = new TextField();
        getPost.setPromptText("Write post here.");

        Button postbtn = new Button("Post");
        postbtn.setOnAction(e -> {
            try {
                Course.addPost(
                        new Post(this.subjectName, "Post : " + getPost.getText() + "  by : " + user.getUsername() + " Date : " + new Date()));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            window.close();
        });
        Button closebtn = new Button("Close");
        closebtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titlescene,getPost,postbtn, closebtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}