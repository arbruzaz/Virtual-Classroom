import java.util.ArrayList;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class ExamCreationModule extends ScrollPane {

    Stage window;
    String subjectname;
    ScrollPane pane = new ScrollPane();
    VBox layout = new VBox();
    VBox itemList = new VBox();

    VBox header = new VBox();
    Label title = new Label("Title");
    TextField titleField = new TextField("Title");
    Label description = new Label("Descruption");
    TextField descriptionField = new TextField("Description");
    
    ModuleDisplayMode headerMode = ModuleDisplayMode.Showing;

    ArrayList<QuestionCreationModule> questionList = new ArrayList<QuestionCreationModule>();
    Button addQuestionButton = new Button("+ Add Question");
    Button saveButton = new Button(" Save ");
    HBox buttonBox = new HBox();

    public ExamCreationModule(Exam exam, Stage stage) {
        super();
        window = stage;

        super.setContent(layout);
        super.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        super.setHbarPolicy(ScrollBarPolicy.NEVER);
        super.getStylesheets().add("BlackScrollPane.css");

        layout.getChildren().addAll(header, itemList);
        layout.setStyle("-fx-background-color: rgb(54, 57, 63, 1.0);");
        layout.setSpacing(8);
        layout.prefWidthProperty().bind(super.widthProperty());
        layout.prefHeightProperty().bind(super.heightProperty());

        title.getStylesheets().add("WhiteLabel.css");
        title.setFont(Font.font("", FontWeight.BOLD, 28));
        description.getStylesheets().add("WhiteLabel.css");
        description.setFont(Font.font(16));

        titleField.getStylesheets().add("WhiteLargeTextField.css");
        descriptionField.getStylesheets().add("WhiteTextField.css");

        header.setPadding(new Insets(24, 64, 24, 32));
        header.setSpacing(12);
        header.setStyle("-fx-background-color: rgb(54, 57, 63, 1.0);");
        header.getChildren().addAll(title, description);
        header.setOnMouseEntered(e -> {
            if (headerMode == ModuleDisplayMode.Showing) {
                header.setStyle("-fx-background-color: rgb(46, 50, 56, 1.0);");
                window.getScene().setCursor(Cursor.HAND);
            }            
        });
        header.setOnMouseExited(e -> {
            if (headerMode == ModuleDisplayMode.Showing) {
                header.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
                window.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        header.setOnMouseClicked(e -> {
            if (headerMode == ModuleDisplayMode.Showing) {
                headerMode = ModuleDisplayMode.Editting;
                header.getChildren().clear();
                header.getChildren().addAll(titleField, descriptionField);
                header.setStyle("-fx-background-color: rgb(46, 50, 56, 1.0);");
                window.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        
        layout.setOnMouseClicked(e -> {
            exitAllModule();
        });
        
        itemList.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        itemList.setSpacing(12);
        buttonBox.setPadding(new Insets(16, 32, 16, 32));
        buttonBox.setSpacing(16);
        buttonBox.getChildren().addAll(addQuestionButton, saveButton);
        addQuestionButton.getStylesheets().add("YellowButton.css");
        saveButton.getStylesheets().add("DarkTheme.css");

        questionList.add(new QuestionCreationModule(window, 1));
        manageDeleteEvent();
        manageItemList();  
        
        addQuestionButton.setOnAction(e -> {
            questionList.add(new QuestionCreationModule(window, questionList.size() + 1));
            manageItemList();
            
            manageDeleteEvent();
        });        
    }

    public ExamCreationModule(Stage stage) {
        this(new Exam(), stage);
    }

    public void createExam() {
        if (window != null) {
            Scene scene = new Scene(this, window.getScene().getWidth(), window.getScene().getHeight());
            window.setScene(scene);
        }
    }

    private void exitAllModule() {
        if (headerMode == ModuleDisplayMode.Editting && header.isHover() == false) {
            headerMode = ModuleDisplayMode.Showing;
            header.getChildren().clear();
            header.getChildren().addAll(title, description);
            header.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
            window.getScene().setCursor(Cursor.DEFAULT);

            title.setText(titleField.getText());
            description.setText(descriptionField.getText());
        }
        for (int i = 0 ; i < questionList.size() ; i++) {
            if (questionList.get(i).isShowing() == false && questionList.get(i).isHover() == false) {
                questionList.get(i).exitModule();
            }
        }
    }

    private void manageItemList() {
        itemList.getChildren().clear();            
        for (int i = 0 ; i < questionList.size() ; i++) {
            itemList.getChildren().add(questionList.get(i));                
        }
        itemList.getChildren().add(buttonBox);
    }

    public void manageDeleteEvent() {
        for(int i = 0 ; i < questionList.size() ; i++) {
            int index = i;
            questionList.get(index).setDeleteButtonEvent(Event -> {
                if (questionList.size() > 1) {                    
                    questionList.get(index).exitModule();
                    questionList.remove(index);
                    for (int j = 0 ; j < questionList.size() ; j++) {
                        questionList.get(j).setNumber(j + 1);
                    }
                    manageItemList();               
                }
            });
        }
    }

    public void setSaveButtonEvent(EventHandler<ActionEvent> value) {
        saveButton.setOnAction(value);
    }

    public void setExam(Exam exam) {
        questionList.clear();
        this.subjectname = exam.getSubjectname();
        title.setText(exam.getTitle());
        titleField.setText(exam.getTitle());
        description.setText(exam.getDescription());
        descriptionField.setText(exam.getDescription());

        for (int i = 0 ; i < exam.size() ; i++) {
            questionList.add(new QuestionCreationModule(window, i + 1, exam.getQuestion(i).getQuestionStatement()));
            questionList.get(i).setQuestion(exam.getQuestion(i));
        }

        manageDeleteEvent();
        manageItemList();
    }

    public Exam getExam() {
        exitAllModule();
        Exam exam = new Exam(this.title.getText());
        exam.setSubjectname(this.subjectname);
        exam.setDescription(this.description.getText());
        for (int i = 0 ; i < questionList.size() ; i++) {
            exam.addQuestion(questionList.get(i).getQuestion());
        }
        return exam;
    }
}