import java.text.NumberFormat.Style;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModuleController extends BorderPane {

    Stage window;

    BorderPane bottomBar = new BorderPane();
    VBox buttonList = new VBox();
    Button createExamButton = new Button("Create an exam");
    Button doAnExamButton = new Button("Do an exam");
    HBox box1 = new HBox();
    HBox box2 = new HBox();
    HBox box3 = new HBox();
    
    ExamCreationModule examCreationModule;
    ExaminationModule examinationModule;

    Exam exam = new Exam();

    public ModuleController(Stage stage) {
        
        super();
        super.setBottom(bottomBar);
        super.setStyle("-fx-background-color: rgb(54, 57, 63, 1.0);");
        super.setTop(buttonList);

        window = stage;
        
        examCreationModule = new ExamCreationModule(stage);
        examinationModule = new ExaminationModule(stage);

        box1.setPadding(new Insets(24, 24, 24, 24));
        box1.getChildren().add(createExamButton);

        box2.setPadding(new Insets(24, 24, 24, 24));
        box2.getChildren().add(doAnExamButton);

        createExamButton.getStylesheets().add("YellowButton.css");
        doAnExamButton.getStylesheets().add("YellowButton.css");
        
        buttonList.getChildren().addAll(box1, box2);
        
        createExamButton.setOnAction(e -> {
            window.getScene().setRoot(examCreationModule);
        });

        examCreationModule.setSaveButtonEvent(e -> {
            exam = examCreationModule.getExam();
            window.getScene().setRoot(this);
            System.out.println(exam.toString());
        });

        doAnExamButton.setOnAction(e -> {
            examinationModule.setExam(exam);
            window.getScene().setRoot(examinationModule);            
        });

        examinationModule.setSubmitButtonEvent(e -> {
            System.out.println("Your score is " + examinationModule.getPercentage() + "%");
            window.getScene().setRoot(this);
        });

        Question q1 = new Question("Who is Tyrant?");
        Question q2 = new Question("What is this year?");
        
        q1.addChoice("Prayuth");
        q1.addChoice("Tu");
        q1.setChoice(1, "Tou");

        q2.addChoice("2020");
        q2.addChoice("3020");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        exam.setTitle("555");
        examCreationModule.setExam(exam);
        
    }
    
    
    

}














