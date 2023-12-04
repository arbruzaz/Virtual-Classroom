import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertBox extends BorderPane {

    // THIS CLASS HAVE 2 IMPORTANT
    // 1.IF PARTICIPANT CLICK ADD TOPIC CONSTRUCTOR Alertbox(Stage window) will be
    // work
    // 2.IF PARTICIPANT CLICK ADD TOPIC AND DIDN'T CHOOSE TOPIC BEFORE(choose
    // radiobutton) public static void alertBoxAddCourse() WILL BE WORK
    Participant participant;
    Participant participant2;

    Stage window;
    // ----------------------------------------------------<< LABEL AND TEXT STRING
    // >>--------------------------------------------------------//
    Label topicName = new Label("               Topic Name : ");

    TextField topicNameText = new TextField();

    // ----------------------------------------------------<< PANE LAYOUT
    // >>--------------------------------------------------------//

    BorderPane outSideLayOut_Center = new BorderPane();
    VBox middleLayout_Center = new VBox(10);

    HBox inSideLayOut_Roll1 = new HBox(10);
    HBox inSideLayOut_Roll2 = new HBox(10);
    HBox inSideLayOut_Roll3 = new HBox();
    HBox middleLayout_Bottom = new HBox();
    // --------------------------------------------------<< BUTTON
    // >>----------------------------------------------------------//

    Button backToCourse = new Button("Back");
    Button addTopic = new Button("add Topic");

    // ------------------------------------------------------------------------------------------------------------//

    public AlertBox(Stage window, User user) {
        this.window = window;
        this.getStylesheets().add("Gui.css");

        topicNameText.setPromptText(" Add topic for study"); //

        // ------------------------------------------------------------------------------------------------------------//

        inSideLayOut_Roll2.setAlignment(Pos.CENTER_LEFT);
        inSideLayOut_Roll2.getChildren().addAll(topicName, topicNameText);

        inSideLayOut_Roll3.getChildren().addAll(addTopic);
        inSideLayOut_Roll3.setAlignment(Pos.CENTER);

        addTopic.setOnAction(actionEvent -> {
            if (topicNameText.getText().equals(null) || topicNameText.getText().equals("")) {
                AlertBoxError.display("Error", "Please Fillin Topic Name");
            } else
                try {
                    if (Course.checkTopic(topicNameText.getText()) == false) {
                        AlertBoxError.display("Error", "This topic alreadu exist.");
                    } else {
                        try {
                            Course.createTopic(topicNameText.getText());
                            System.out.println("Add File");

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
        middleLayout_Center.getChildren().addAll(inSideLayOut_Roll2, inSideLayOut_Roll3);
        middleLayout_Center.setStyle("-fx-border-width: 2px; -fx-border-color: red");

        //------------------------------------------------------------------------------------------------------------//
        middleLayout_Bottom.getChildren().addAll(backToCourse);
        middleLayout_Bottom.setAlignment(Pos.BOTTOM_LEFT);
        middleLayout_Bottom.setStyle("-fx-border-width: 2px; -fx-border-color: red");

        backToCourse.setOnAction(actionEvent -> {
            try {
                participant2 = new Participant(window, user);
                participant2.getStylesheets().add("Gui.css");

                window.setScene(new Scene(participant2,700,700));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        //------------------------------------------------------------------------------------------------------------//

        outSideLayOut_Center.setCenter(middleLayout_Center);
        outSideLayOut_Center.setBottom(middleLayout_Bottom);

        //------------------------------------------------------------------------------------------------------------//

        setCenter(outSideLayOut_Center);


    }

        //------------------------------------------------------------------------------------------------------------//
        // IF YOU DID'T CHOOSE RADIOBUTTON AND YOU CLICK ADD SUBJECT,THIS FUNCTION WILL BE POPUP
    public static void alertBoxAddCourse(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning!");
        window.setMinWidth(450);
        window.setMinHeight(250);

        Label label = new Label();
        label.setText("Please Selected Topic to add your subject");
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(actionEvent -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.getStylesheets().add("Gui.css");
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}
