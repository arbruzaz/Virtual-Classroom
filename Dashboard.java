import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dashboard extends BorderPane {
    Participant participant;
    Dashboard dashboard;
    Loginpage loginpage;
    AddImage addImage;
    Profile profile;
    Reset resetPane;
    HBox centerTable;
    ArrayList<Exam> oldExam;
    ExamCreationModule createExam;

    public Dashboard(Stage window, User user) throws ClassNotFoundException, IOException {

        this.getStylesheets().add("Gui.css");
        this.getStyleClass().addAll("middleLayoutCenter");

        TableView<Subject> tablecourse;
        for (int i = 0; i < user.getUserSubject().size(); i++) {
            System.out.println(user.getUserSubject().get(i).toString());
        }
        Menu setting = new Menu("settings");

        MenuItem edit = new MenuItem("Edit Profile");
        edit.setOnAction(e -> {
            profile = new Profile(window, user);
            profile.getStylesheets().add("Gui.css");
            window.setScene(new Scene(profile, 600, 600));
        });

        MenuItem reset = new MenuItem("Reset Password");
        reset.setOnAction(e -> {
            resetPane = new Reset(window, user, true);
            resetPane.getStylesheets().add("Gui.css");
            window.setScene(new Scene(resetPane, 600, 600));
        });

        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> {
            try {
                loginpage = new Loginpage(window);
                loginpage.getStylesheets().add("Gui.css");
                window.setScene(new Scene(loginpage, 400, 400));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            boolean exor = AlertBoxError.confirm("Exit", "Are you sure?");
            if (exor)
                window.close();
        });

        setting.getItems().addAll(edit, reset, logout, exit);
        MenuBar rightBar = new MenuBar();
        rightBar.getMenus().addAll(setting);
        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        HBox menubars = new HBox(spacer, rightBar);

        VBox info = new VBox(20);
        info.setAlignment(Pos.TOP_CENTER);
        info.setPadding(new Insets(25, 25, 25, 25));
        ImageView pic;
        try {
            pic = new ImageView(new Image(user.getPicture()));
        } catch (IllegalArgumentException e) {
            AlertBoxError.display("Error", "Cannot find you directory file.\nI set other picture instead.");
            user.setPicture("Image/error404.jpg");
            AboutUser.updateUser(user);
            pic = new ImageView(new Image(user.getPicture()));
        }
        pic.setFitHeight(250);
        pic.setFitWidth(250);
        HBox forpic = new HBox(pic);
        forpic.setMaxSize(250, 250);
        Text id = new Text("ID : " + user.getUsername());
        id.setStyle("-fx-font-size: 18px ;-fx-text-fill: #111231 ;-fx-font-style: oblique;");
        Button change = new Button("Change Image");
        change.setOnAction(e -> {
            addImage = new AddImage(window, user);
            addImage.getStylesheets().add("Gui.css");
            window.setScene(new Scene(addImage, 400, 400));
        });
        Text name = new Text("Name : " + user.getName());
        name.setStyle("-fx-font-size: 18px ;-fx-text-fill: #3f4099 ;-fx-font-style: italic;");
        Text surname = new Text("Surname : " + user.getSurname());
        surname.setStyle("-fx-font-size: 18px ;-fx-text-fill: #3f4099 ;-fx-font-style: italic;");
        Text address = new Text("Address : " + user.getAddress());
        address.setStyle("-fx-font-size: 18px ;-fx-text-fill: #3f4099 ;-fx-font-style: italic;");
        Text email = new Text("Email : " + user.getEmail());
        email.setStyle("-fx-font-size: 18px ;-fx-text-fill: #3f4099 ;-fx-font-style: italic;");
        Text tel = new Text("Tel-Number : " + user.getTel());
        tel.setStyle("-fx-font-size: 18px ;-fx-text-fill: #3f4099 ;-fx-font-style: italic;");
        info.getChildren().addAll(id, forpic, change, name, surname, address, email, tel);

        Button parti = new Button("Participant");
        parti.setOnAction(e -> {
            try {
                participant = new Participant(window, user);
                participant.getStylesheets().add("Gui.css");

                window.setScene(new Scene(participant, 1280, 720));
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        HBox btn = new HBox(10);
        btn.getChildren().addAll(parti);
        btn.setAlignment(Pos.CENTER);
        btn.setPadding(new Insets(25, 25, 25, 25));
        VBox dash = new VBox(10);
        dash.getChildren().addAll(btn);
        dash.setAlignment(Pos.TOP_CENTER);

        // Topic Column
        TableColumn<Subject, String> topiccolumn = new TableColumn<>("Topic");
        topiccolumn.setMinWidth(300);
        topiccolumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        // Topic Column
        TableColumn<Subject, String> subjectcolumn = new TableColumn<>("Subject");
        subjectcolumn.setMinWidth(300);
        subjectcolumn.setCellValueFactory(new PropertyValueFactory<>("subjectString"));

        tablecourse = new TableView<>();
        tablecourse.setItems(Tabledash.getSubject(user));
        tablecourse.getColumns().addAll(topiccolumn, subjectcolumn);

        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            
            try {
              
                ObservableList<Subject> subselect;
                subselect = tablecourse.getSelectionModel().getSelectedItems();
                tablecourse.getItems().remove(subselect);
                for (int i = 0; i < user.getUserSubject().size(); i++) {
                    if (tablecourse.getSelectionModel().getSelectedItem().getSubjectString()
                            .equals(user.getUserSubject().get(i).getSubjectString())) {
                        user.getUserSubject().remove(i);
                    }
                }
                try {
                    AboutUser.updateUser(user);
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    dashboard = new Dashboard(window, user);
                    dashboard.getStylesheets().add("Gui.css");
                    window.setScene(new Scene(dashboard, 1280, 720));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
           
            } catch (NullPointerException c) {
                AlertBoxError.display("Error", "Please Select Subject");
            }
            
           
        });

        Button guidetable = new Button("Guide");
        guidetable.setOnAction(e -> {
            try {
              
                if (!tablecourse.getSelectionModel().getSelectedItem().getSubjectString().equals(null)) {
                    try {
    
                        window.setScene(
                                new Scene(new Guide(tablecourse.getSelectionModel().getSelectedItem().getSubjectString(),
                                        user, window), 1280, 720));
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
           
            } catch (NullPointerException c) {
                AlertBoxError.display("Error", "Please Select Subject");
            }
            
         });
        createExam = new ExamCreationModule(window);
        Button testtable = new Button("Test");
        testtable.setOnAction(e -> {
            try {
                oldExam = new ArrayList<>();
            try {
                oldExam = Course.readExam();
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            if (!tablecourse.getSelectionModel().getSelectedItem().getSubjectString().equals(null)) {
                boolean check = false;
                ExamCreationModule createExam = new ExamCreationModule(window);
                for (int i = 0; i < oldExam.size(); i++) {
                    if (oldExam.get(i).getSubjectname()
                            .equals(tablecourse.getSelectionModel().getSelectedItem().getSubjectString())) {
                        createExam.setExam(oldExam.get(i));
                        check = true;
                        break;
                    }
                }
                if (user.getTeacher() == true) {
                    if (check == false) {
                        createExam.setExam(
                                new Exam(tablecourse.getSelectionModel().getSelectedItem().getSubjectString()));
                    }
                    window.setScene(new Scene(createExam, 1280, 720));
                    createExam.setSaveButtonEvent(a -> {
                        try {
                            Course.writeFile(createExam.getExam());
                            window.setScene(new Scene(new Dashboard(window, user)));
                        } catch (ClassNotFoundException | IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                } else {
                    if (check == false) {
                        AlertBoxError.display("Cannot Do it !", "Teacher has not create Exam yet.");
                    } else {
                        ExaminationModule student = new ExaminationModule(window);
                        student.setExam(createExam.getExam());
                        window.setScene(new Scene(student));
                        student.setSubmitButtonEvent(b -> {
                            try {
                                window.setScene(new Scene(new Dashboard(window, user)));
                            } catch (ClassNotFoundException | IOException e1) {
                                e1.printStackTrace();
                            }
                        });    
                    }
            }
            }
            } catch (NullPointerException c) {
                AlertBoxError.display("Error", "Please Select Subject");
            }
            
        });
        HBox tablebtn = new HBox();
        tablebtn.setPadding(new Insets(10, 10, 10, 10));
        tablebtn.getChildren().addAll(delete,guidetable,testtable);
        tablebtn.setAlignment(Pos.CENTER);
        centerTable = new HBox();

        centerTable.setAlignment(Pos.CENTER);
        centerTable.getChildren().addAll(tablecourse);
        dash.getChildren().addAll(centerTable,tablebtn);
        dash.setMinSize(900,500);
        dash.setAlignment(Pos.TOP_CENTER);
        setCenter(dash);
        setTop(menubars);
        info.setMaxWidth(300);
        ScrollPane infoscroll = new ScrollPane(info);
        setLeft(infoscroll);
        infoscroll.setMaxWidth(300);
    }

}

