import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectDetail extends BorderPane {
    Text topicText = new Text(20, 20, "Topic");
    Text mainText = new Text(20, 20, "Detail of Course");


    VBox layoutOutside_Top = new VBox(5);
    HBox layoutOutside_Right = new HBox();
    HBox layoutOutside_Left = new HBox();

    HBox layoutMiddle_Top = new HBox();
    HBox layoutMiddle_CenterTop = new HBox(60);
    HBox layoutMiddle_CenterBottom = new HBox();

    Button goToParticipant = new Button("Back");
    Button addCourse = new Button("Add Course");

    Image imageSubject = new Image("Image/error404.jpg");
    Image imageTeacher = new Image("Image/error404.jpg");
    ImageView imageSubjectView = new ImageView(imageSubject);
    ImageView imageTeacherView = new ImageView(imageTeacher);

    Subject subject;

    public void setTopicText(String topicText) {
        this.topicText.setText(topicText);
    }

    public void setMainText(String mainText) {
        this.mainText.setText(mainText);
    }

    public void setImageSubjectView(Image imageSubjectView) {
        this.imageSubjectView.setImage(imageSubjectView);
    }

    public void setImageTeacherView(Image imageTeacherView) {
        this.imageTeacherView.setImage(imageTeacherView);
    }

    public SubjectDetail(Stage window, User user) {
        this.getStylesheets().add("Gui.css");
        this.getStyleClass().addAll("middleLayoutCenter");


        // ---------------------------------------------------<< Middle Layout
        // >>---------------------------------------------------//
        // -----------------------[ TOP ]--------------------------//
        goToParticipant.getStyleClass().add("button-yellow");

        topicText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
        topicText.setFill(Color.rgb(0, 0, 0));
        topicText.setUnderline(true); // Underline for text3

        layoutMiddle_Top.setStyle("-fx-border-width: 2px; -fx-border-color: green");

        layoutMiddle_Top.setPadding(new Insets(20, 10, 25, 70));
        layoutMiddle_Top.getChildren().addAll(topicText);
        layoutMiddle_Top.setStyle("-fx-background-color: rgb(227, 17, 17),  linear-gradient(to bottom, rgb(255, 233, 33) 0%, rgb(255, 174, 33) 100%);");
        // -----------------------[ Center ]--------------------------//
        imageSubjectView.setFitHeight(200);
        imageSubjectView.setFitWidth(250);
        imageTeacherView.setFitHeight(200);
        imageTeacherView.setFitWidth(250);
        layoutMiddle_CenterTop.setStyle("-fx-border-width: 2px; -fx-border-color: red");
        layoutMiddle_CenterTop.setAlignment(Pos.TOP_CENTER);
        layoutMiddle_CenterTop.setPadding(new Insets(0, 0, 25, 70));
        layoutMiddle_CenterTop.getChildren().addAll(imageSubjectView, imageTeacherView);
        // -----------------------[ BOTTOM ]--------------------------//
        mainText.setFont(Font.font("Courier", FontWeight.THIN, FontPosture.ITALIC, 20));
        mainText.setFill(Color.rgb(255, 255, 255));
        layoutMiddle_CenterBottom.setStyle("-fx-border-width: 2px; -fx-border-color: #fcfcfc ;-fx-background-color:rgb(99,125,217) ");
        layoutMiddle_CenterBottom.setPadding(new Insets(30, 0, 0, 30));
        layoutMiddle_CenterBottom.getChildren().addAll(mainText);

        // ---------------------------------------------------<< OUTSIDE LAYOUT
        // >>---------------------------------------------------//
        // -----------------------[BOTTOM LEFT ]--------------------------//
        goToParticipant.setOnAction(actionEvent -> {
            try {
                window.setScene(new Scene(new Participant(window, user), 1280, 720));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        addCourse.setOnAction(e -> {
            boolean same = false;
            for(int i = 0;i < user.getUserSubject().size(); i++){
                if(user.getUserSubject().get(i).getSubjectString().equals(this.subject.getSubjectString())){
                    AlertBoxError.display("Error", "You already add this Subject.");
                    same = true;
                    break;
                }
            }
            
            if(same == false){
                user.addUserSubject(this.subject);
                try {
                    AboutUser.updateUser(user);
                } catch (ClassNotFoundException | IOException e2) {
                    e2.printStackTrace();
                }
                AlertBoxError.display("Finished", "Add Subject Succesfully");
                try {
                    window.setScene(new Scene(new Participant(window, user), 1280, 720));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        layoutOutside_Left.setStyle("-fx-border-width: 5px; -fx-border-color: purple");
        layoutOutside_Left.setAlignment(Pos.BOTTOM_LEFT);
        layoutOutside_Left.getChildren().addAll(goToParticipant);
        //-----------------------[  BOTTOM RIGHT]--------------------------//
        layoutOutside_Right.setStyle("-fx-border-width: 5px; -fx-border-color: green");
        layoutOutside_Right.setAlignment(Pos.BOTTOM_RIGHT);
        layoutOutside_Right.getChildren().addAll(addCourse);
        //-----------------------[  TOP ]--------------------------//
        layoutOutside_Top.setStyle("-fx-border-width: 2px; -fx-border-color: yellow");
        layoutOutside_Top.getChildren().addAll(layoutMiddle_Top,layoutMiddle_CenterTop,layoutMiddle_CenterBottom);


        //---------------------------------------------------<< SET PANE >>---------------------------------------------------//
        setTop(layoutOutside_Top);
        setLeft(layoutOutside_Left);
        setRight(layoutOutside_Right);
        //<------------------------------------------------->>





    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }













}
