import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Participant extends BorderPane {

    Stage window;
    User user;
    Center center;
    // ---------------------------------------------------<< PANE LAYOUT
    // >>---------------------------------------------------------//

    VBox middleLayout_right = new VBox(50);
    VBox middleLayout_Left = new VBox(20);

    HBox middleLayout_Center = new HBox();
    HBox insideLayout_Center = new HBox(40);
    ScrollPane scrollPaneMiddleLayout_left = new ScrollPane(middleLayout_Left);

    HBox middleLayout_Bottom = new HBox(10);
    // ------------------------------------------------------<< BUTTON
    // >>------------------------------------------------------//

    Button addTopicButton = new Button("Add Topic");
    Button addSubjectButton = new Button("Add subject");
    Button deleteTopic = new Button("delete topic");
    Button deleteSubject = new Button("delete subject");
    Button goToDashBoard = new Button("Back");

    ArrayList<RadioButton> topicRadiobuttonArraylist = new ArrayList<>();
    ToggleGroup toggleGroup = new ToggleGroup();
    private ArrayList<Topic> allTopic;
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    ArrayList<Button> buttonArrayList = new ArrayList<>();

    // ------------------------------------------------------<< NOT ALREADY SET
    // >>------------------------------------------------------//
    ArrayList<VBox> layoutInsideCenter_vBoxArrayList = new ArrayList<>();
    ArrayList<EventHandler<ActionEvent>> actionEventArrayList = new ArrayList<>();
    ArrayList<ImageView> imageInside_Vbox_imageViewArrayList = new ArrayList<>();
    ArrayList<VBox> middleLayoutVbox_Center = new ArrayList<>();
    ArrayList<Label> labelArrayList = new ArrayList<>();
    ArrayList<HBox> rollHBoxArrayList = new ArrayList<>();
    ArrayList<SubjectDetail> subjectDetailArrayList = new ArrayList<>();
    ArrayList<Scene> sceneArrayList = new ArrayList<>();
    VBox[] columnVbox = new VBox[5];
    Label[] label = new Label[5];
    int numberOfvBox = 0;
    int columnNumber = 0;
    int rollNumber = 0;
    int count = 0;



    ScrollPane scrollPaneMiddleLayout_Center = new ScrollPane();


    public void setVbox(Image image, Button button, EventHandler<ActionEvent> eventEventHandler) {
        button.setOnAction(eventEventHandler);
        this.buttonArrayList.add(button);
        this.actionEventArrayList.add(eventEventHandler);

        try {
            this.imageInside_Vbox_imageViewArrayList.add(new ImageView(image));
        } catch (Exception e) {
            System.out.println("cannot");
        }

    }

    public void createHbox(VBox vBox) {
        this.middleLayoutVbox_Center.add(vBox);

    }


    // << CONSTRUCTURE >>//
    public Participant(Stage window, User user) throws IOException, ClassNotFoundException {
        this.user = user;
        this.allTopic = new ArrayList<>();
        this.allTopic = Course.readoldtopic();
        System.out.println("TOPIC SIZE : " + this.allTopic.size());
        this.window = window;
        this.getStylesheets().add("Gui.css");

        for (int i = 0; i < this.allTopic.size(); i++) {
            this.topicRadiobuttonArraylist.add(new RadioButton(this.allTopic.get(i).getTopicName()));
            this.topicRadiobuttonArraylist.get(i).setToggleGroup(toggleGroup);
            topicRadiobuttonArraylist.get(i).getStyleClass().addAll("topicRadiobuttonArraylist");
            middleLayout_Left.getChildren().addAll(this.topicRadiobuttonArraylist.get(i));
            createHbox(new VBox(30));


            int finalI = i;

            topicRadiobuttonArraylist.get(i).setOnAction(actionEvent -> {
                try {
                    center = new Center(window, user,allTopic.get(finalI));
                    center.getStylesheets().add("Gui.css");
                    center.getStyleClass().addAll("middleLayoutCenter");
                    center.setMinSize(1750,1250);
                    center.resize(600,600);


                    scrollPaneMiddleLayout_Center.setContent(center);                
                    scrollPaneMiddleLayout_Center.getStylesheets().add("Gui.css");
                    scrollPaneMiddleLayout_Center.getStyleClass().addAll("middleLayoutCenter");
                    middleLayout_Center.getStylesheets().add("Gui.css");
                    middleLayout_Center.getStyleClass().addAll("middleLayoutCenter");
                    middleLayout_Center.setAlignment(Pos.TOP_CENTER);

                    setCenter(scrollPaneMiddleLayout_Center);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("topicRadiobutton Number :   [" + finalI + "]    is choosing now");

            });
        }

        middleLayout_Left.setPadding(new Insets(5, 20, 5, 5));
        middleLayout_Left.setMinSize(30,1020);
        middleLayout_Left.setStyle("-fx-border-width: 2px; -fx-border-color: green");
        middleLayout_Left.getStylesheets().add("Gui.css");
        middleLayout_Left.getStyleClass().addAll("vbox-yellow");


        // ------------------------------------------------------------------------------------------------------------//



        addTopicButton.setRotate(-45);
        addSubjectButton.setRotate(-45);
        deleteTopic.setRotate(-45);
        deleteSubject.setRotate(-45);
        middleLayout_right.setAlignment(Pos.CENTER);
        middleLayout_right.getChildren().addAll(addTopicButton, addSubjectButton, deleteTopic);
        middleLayout_right.setStyle("-fx-border-width: 2px; -fx-border-color: green");

        // GO TO ALERTBOX CLASS FOR ADD TOPIC
        addTopicButton.setOnAction(actionEvent -> {
            if (user.getTeacher() == true) {
                window.setScene(new Scene(new AlertBox(window, user), 700, 700));
            } else {
                AlertBoxError.display("Error", "You are not a teacher.");
            }
        });

        // CHECK TOPIC(RADIO BUTTON) IS CHOOSING
        addSubjectButton.setOnAction(actionEvent1 -> {
            if (user.getTeacher() == true) {
                boolean check = false;
                Topic editTopic = null;
                for (int i = 0; i < this.allTopic.size(); i++) {
                    if (topicRadiobuttonArraylist.get(i).isSelected()) {
                        check = true;
                        editTopic = this.allTopic.get(i);
                        break;
                    }
                }
                // IF YOU DID'T CHOOSE TOPIC(RADIO BUTTON) BEFORE. YOU WILL SEE PANE IN STATIC
                // METHOD IN Alertbox Class line[141 - 164]
                if (check == false) {
                    AlertBox.alertBoxAddCourse();
                }
                // IF YOU CHOOSE TOPIC(RADIO BUTTON) BEFORE. YOU WILL SEE PANE IN CONSTRUCTOR IN
                // Alertbox Class line [71-137]
                else {
                    System.out.println("Can Add Subject in Topic");
                    window.setScene(new Scene(new AddSubjectPane(window, editTopic, user), 1280, 720));
                }
            } else {
                AlertBoxError.display("Error", "You are not a teacher.");
            }
        });

        deleteTopic.setOnAction(e -> {
            if (user.getTeacher() == true) {
                boolean check = false;
                Topic selected = null;
                for (int i = 0; i < this.allTopic.size(); i++) {
                    if (topicRadiobuttonArraylist.get(i).isSelected()) {
                        check = true;
                        selected = this.allTopic.get(i);
                        break;
                    }
                }
                if (check == false) {
                    AlertBox.alertBoxAddCourse();
                } else {
                    boolean ok = AlertBoxError.confirm("Please Read!", "Are you sure?");
                    if(ok == true){
                        try {
                            Course.deleteTopic(selected);
                            window.setScene(new Scene(new Participant(window, user)));
                        } catch (ClassNotFoundException | IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else {
                AlertBoxError.display("Error", "You are not a teacher.");
            }
        });

        // ------------------------------------------------------IN CENTER OF
        // Pane------------------------------------------------------//

        insideLayout_Center.setStyle("-fx-border-width: 2px; -fx-border-color: red");
        insideLayout_Center.setAlignment(Pos.CENTER);
        middleLayout_Center.setAlignment(Pos.CENTER);
        middleLayout_Center.getChildren().addAll(insideLayout_Center);
        middleLayout_Center.setStyle("-fx-border-width: 2px; -fx-border-color: yellow");
        middleLayout_Center.setMinSize(500, 500);
        middleLayout_Center.getStylesheets().add("Gui.css");
        middleLayout_Center.getStyleClass().addAll("middleLayoutCenter");

        // -------------------------------------------------------IN BOTTOM
        // PANE-----------------------------------------------------//

        middleLayout_Bottom.setStyle("-fx-border-width: 2px; -fx-border-color: yellow");
        middleLayout_Bottom.setAlignment(Pos.CENTER_LEFT);
        middleLayout_Bottom.getChildren().addAll(goToDashBoard);

        goToDashBoard.setOnAction(actionEvent -> {
            try {
                window.setScene(new Scene(new Dashboard(window, user), 1280, 720));
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });



        setBottom(middleLayout_Bottom);
        setLeft(scrollPaneMiddleLayout_left);
        setCenter(middleLayout_Center);




        setRight(middleLayout_right);


    }
}



