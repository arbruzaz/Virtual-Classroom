import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Center extends BorderPane {
    Stage window;
    User user;
    private Topic topic;

    ArrayList<Button> buttonArrayList = new ArrayList<>();

    ArrayList<ImageView> imageInside_Vbox_imageViewArrayList = new ArrayList<>();

    VBox middleLayoutVbox_Center = new VBox();
    ArrayList<Label> labelArrayList = new ArrayList<>();
    ArrayList<HBox> rollHBoxArrayList = new ArrayList<>();
    ArrayList<SubjectDetail> subjectDetailArrayList = new ArrayList<>();
    VBox[] columnVbox = new VBox[5];
    int numberOfvBox = 0;
    int columnNumber = 0;
    int rollNumber = 0;


    public void setVbox(Image image, Button button, EventHandler<ActionEvent> eventEventHandler) {
        button.setOnAction(eventEventHandler);
        this.buttonArrayList.add(button);


        try {
            this.imageInside_Vbox_imageViewArrayList.add(new ImageView(image));
        } catch (Exception e) {
            System.out.println("cannot");
        }

    }




    public Center(Stage window ,User user,Topic topic) throws IOException, ClassNotFoundException  {
        this.user = user;
        this.topic = topic;

        this.window = window;

        this.getStylesheets().add("Gui.css");
        this.getStyleClass().addAll("middleLayoutCenter");

            for (int i = 0; i < topic.getSubjectArrayList().size(); i++) {
                if (topic.getSubjectArrayList().get(i).getTopicName().equals(topic.getTopicName())) {
                    subjectDetailArrayList.add(new SubjectDetail(window, user));
                    subjectDetailArrayList.get(i).getStylesheets().add("Gui.css");
                    subjectDetailArrayList.get(i).setTopicText(("YoLo Topic"));
                    subjectDetailArrayList.get(i).setTopicText(topic.getSubjectArrayList().get(i).getSubjectString());
                    subjectDetailArrayList.get(i).setMainText(topic.getSubjectArrayList().get(i).getDetailSubjectString());

                    try {
                        subjectDetailArrayList.get(i).setImageSubjectView(new Image(topic.getSubjectArrayList().get(i).getSubjectPicture()));
                    } catch (IllegalArgumentException e) {
                        AlertBoxError.display("Error", "Cannot find you directory file.\nI set other picture instead.");
                        topic.getSubjectArrayList().get(i).setSubjectPicture("Image/error404.jpg");
                        subjectDetailArrayList.get(i).setImageSubjectView(new Image(topic.getSubjectArrayList().get(i).getSubjectPicture()));
                        Course.updateTopic(topic.getSubjectArrayList().get(i));
                    }
                    try {
                        subjectDetailArrayList.get(i).setImageTeacherView(new Image(topic.getSubjectArrayList().get(i).getSubjectPicture()));
                    } catch (IllegalArgumentException e) {
                        AlertBoxError.display("Error", "Cannot find you directory file.\nI set other picture instead.");
                        topic.getSubjectArrayList().get(i).setSubjectPicture("Image/error404.jpg");
                        subjectDetailArrayList.get(i).setImageTeacherView(new Image(topic.getSubjectArrayList().get(i).getSubjectPicture()));
                        Course.updateTopic(topic.getSubjectArrayList().get(i));
                    }
                    subjectDetailArrayList.get(i).setSubject(topic.getSubjectArrayList().get(i));
                    System.out.println(topic.getSubjectArrayList().get(i).toString());

                    int finalI = i;
                    setVbox(new Image(topic.getSubjectArrayList().get(i).getSubjectPicture()),
                            new Button(topic.getSubjectArrayList().get(i).getSubjectString()),
                            actionEvent -> {
                                window.setScene(new Scene(subjectDetailArrayList.get(finalI)));
                            });
                    imageInside_Vbox_imageViewArrayList.get(i).setFitWidth(300);
                    imageInside_Vbox_imageViewArrayList.get(i).setFitHeight(300);


                    if (columnNumber % 5 == 0) {
                        columnNumber = 0;
                    }
                    if (columnNumber % 5 == 0) {
                        rollHBoxArrayList.add(new HBox(30));
                    }
                    rollNumber = numberOfvBox / 5;

                    // HAVE ENOUGH 4 COLUMNS IN 1 ROLLS
                    columnVbox[columnNumber] = new VBox(20);
                    columnVbox[columnNumber].getStylesheets().add("Gui.css");
                    columnVbox[columnNumber].getStyleClass().addAll("vbox-yellow");
                    columnVbox[columnNumber].setAlignment(Pos.CENTER);
                    columnVbox[columnNumber].setStyle("-fx-border-width: 4px; -fx-border-color: #ffa200");
                    columnVbox[columnNumber].getChildren().addAll(imageInside_Vbox_imageViewArrayList.get(i), buttonArrayList.get(i));
                    rollHBoxArrayList.get(rollNumber).getChildren().addAll(columnVbox[columnNumber]);
                    rollHBoxArrayList.get(rollNumber).setAlignment(Pos.TOP_LEFT);
                    rollHBoxArrayList.get(rollNumber).setPadding(new Insets(30,20,50,50));

                    if (columnNumber % 5 == 0) {
                        middleLayoutVbox_Center.getChildren().addAll(rollHBoxArrayList.get(rollNumber));
                    }
                    columnNumber++;
                    numberOfvBox++;

                    System.out.println(rollHBoxArrayList.size());

                    System.out.println("modnumber" + columnNumber);
                    System.out.println("numberofVbox" + numberOfvBox);
                    System.out.println("DEtailsubject size" + subjectDetailArrayList.size());




                }





            }

        setCenter(middleLayoutVbox_Center);

    }
}
