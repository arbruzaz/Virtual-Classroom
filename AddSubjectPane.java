import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddSubjectPane extends BorderPane {

    Participant participant;
    Participant participant2;


    Stage window;
    // ----------------------------------------------------<< LABEL AND TEXT STRING
    // >>--------------------------------------------------------//
    Label teacherName = new Label("                 Teacher Name : ");
    // Label topicName = new Label(" : ");
    Label subjectName = new Label("                    Subject : ");
    Label subjectID = new Label("       SubjectID : ");
    Label coursePicture = new Label("         Course Pic. : ");
    Label teacherPicture = new Label("    Teacher Pic : ");
    // Label topicPicture = new Label(" : ");
    Label detailCourse = new Label(" Course Detail : ");

    TextField teacherNameText = new TextField();
    TextField topicNameText = new TextField();
    TextField subjectNameText = new TextField();
    TextField subjectIDText = new TextField();
    TextField coursePictureText = new TextField();
    TextField teacherPictureText = new TextField();
    // TextField topicPictureText = new TextField();
    TextField detailCourseText = new TextField();

    String setTeacherName;
    String setTopicName;
    String setSubjectName;
    String setSubjectID;
    String setCoursePicture;
    String setTeacherPicture;
    // String setTopicPicture;
    String setDetailCourse;
    // ------------------------------------------------------------------------------------------------------------//

    // ----------------------------------------------------<< PANE LAYOUT
    // >>--------------------------------------------------------//

    BorderPane outSideLayOut_Center = new BorderPane();
    VBox middleLayout_Center = new VBox(25);
    HBox hBox = new HBox();

    HBox inSideLayOut_1 = new HBox(10);
    HBox inSideLayOut_2 = new HBox(10);
    HBox inSideLayOut_3 = new HBox(10);
    HBox inSideLayOut_4 = new HBox();
    HBox middleLayout_Bottom = new HBox();
    // --------------------------------------------------<< BUTTON
    // >>----------------------------------------------------------//

    Button backToParticipant = new Button("Back");
    Button addSubject = new Button("add Subject");

    // ------------------------------------------------------------------------------------------------------------//

    public AddSubjectPane(Stage window, Topic editTopic, User user) {
        this.window = window;

        this.getStylesheets().add("Gui.css");
        this.getStyleClass().addAll("middleLayoutCenter");


        // ------------------------------------------------------------------------------------------------------------//

        inSideLayOut_1.setAlignment(Pos.CENTER_LEFT);
        inSideLayOut_1.getChildren().addAll(teacherName, teacherNameText, teacherPicture, teacherPictureText);

        inSideLayOut_2.setAlignment(Pos.CENTER_LEFT);
        inSideLayOut_2.getChildren().addAll(subjectName, subjectNameText, subjectID, subjectIDText, coursePicture,
                coursePictureText);

        inSideLayOut_3.setAlignment(Pos.CENTER_LEFT);
        inSideLayOut_3.getChildren().addAll(detailCourse, detailCourseText);
        detailCourseText.setMinSize(650, 50);

        inSideLayOut_4.getChildren().addAll(addSubject);
        inSideLayOut_4.setAlignment(Pos.CENTER);

        addSubject.setOnAction(actionEvent -> {
            if (teacherNameText.getText().equals(null) || teacherNameText.getText().equals("")
                    || teacherPictureText.getText().equals(null) || teacherPictureText.getText().equals("")
                    || subjectNameText.getText().equals(null) || subjectNameText.getText().equals("")
                    || subjectIDText.getText().equals(null) || subjectIDText.getText().equals("")
                    || coursePictureText.getText().equals(null) || coursePictureText.getText().equals("")
                    || detailCourseText.getText().equals(null) || detailCourseText.getText().equals("")) {
                AlertBoxError.display("Error", "Please Fillin Boxes");
            } else
                try {
                    if (Course.checkSubject(subjectNameText.getText()) == false) {
                        AlertBoxError.display("Error", "This subject already exist.");
                    } else {
                        try {
                            setTopicName = editTopic.getTopicName();
                            setTeacherName = teacherNameText.getText();
                            setTeacherPicture = teacherPictureText.getText();
                            setSubjectName = subjectNameText.getText();
                            setSubjectID = subjectIDText.getText();
                            setCoursePicture = coursePictureText.getText();
                            setDetailCourse = detailCourseText.getText();
                            Subject a = new Subject(setTopicName, setTeacherName, setTeacherPicture, setSubjectName,
                                        setSubjectID, setCoursePicture, setDetailCourse);
                            editTopic.setSubjectArrayList(a);
                            Course.addNewTopic(a, editTopic.getTopicName());
                            System.out.println("Add subject");

                            participant = new Participant(window, user);
                            participant.getStylesheets().add("Gui.css");
                            window.setScene(new Scene(participant, 1280, 720));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
        });


        //------------------------------------------------------------------------------------------------------------//


         middleLayout_Center.setAlignment(Pos.CENTER);
        middleLayout_Center.getChildren().addAll(inSideLayOut_1, inSideLayOut_2, inSideLayOut_3, inSideLayOut_4);
        middleLayout_Center.setStyle("-fx-border-width: 2px; -fx-border-color: red");

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(middleLayout_Center);

        //------------------------------------------------------------------------------------------------------------//

        middleLayout_Bottom.getChildren().addAll(backToParticipant);
        middleLayout_Bottom.setAlignment(Pos.BOTTOM_LEFT);
        middleLayout_Bottom.setStyle("-fx-border-width: 2px; -fx-border-color: red");

        backToParticipant.setOnAction(actionEvent -> {
            try {
                participant2 = new Participant(window, user);
                participant2.getStylesheets().add("Gui.css");
                window.setScene(new Scene(participant2,1280,720));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        //------------------------------------------------------------------------------------------------------------//

        outSideLayOut_Center.setStyle("-fx-border-width: 2px; -fx-border-color: green");
        outSideLayOut_Center.setCenter(hBox);
        outSideLayOut_Center.setBottom(middleLayout_Bottom);

        //------------------------------------------------------------------------------------------------------------//

        setCenter(outSideLayOut_Center);


    }
}
