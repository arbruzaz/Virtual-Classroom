import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExaminationModule extends ScrollPane {

    Stage window;

    VBox layout = new VBox();

    VBox header = new VBox();
    Label title = new Label("Title");
    Label description = new Label("Description");

    VBox itemList = new VBox();
    ArrayList<QuestionModule> questionList = new ArrayList<QuestionModule>();

    HBox buttonBox = new HBox();
    Button submitButton = new Button(" Submit ");

    public ExaminationModule(Stage stage) {
        this.window = stage;

        super.setContent(layout);
        super.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        super.setHbarPolicy(ScrollBarPolicy.NEVER);
        super.getStylesheets().add("DarkTheme.css");

        layout.setStyle("-fx-background-color: rgb(54, 57, 63, 1.0);");
        layout.setSpacing(8);
        layout.prefWidthProperty().bind(super.widthProperty());
        layout.prefHeightProperty().bind(super.heightProperty());

        header.setStyle("-fx-background-color: rgb(46, 50, 56, 1.0);");
        header.setPadding(new Insets(24, 64, 24, 32));
        header.setSpacing(12);
        header.getChildren().addAll(title, description);

        title.getStylesheets().add("DarkTheme.css");
        title.setFont(Font.font("", FontWeight.BOLD, 28));
        description.getStylesheets().add("DarkTheme.css");
        description.setFont(Font.font(16));

        itemList.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        itemList.setSpacing(12);

        buttonBox.setPadding(new Insets(16, 32, 16, 32));
        buttonBox.setSpacing(16);
        buttonBox.getChildren().add(submitButton);

        layout.getChildren().addAll(header, itemList, buttonBox);
    }

    public void setExam(Exam exam) {
        
        title.setText(exam.getTitle());
        description.setText(exam.getDescription());

        questionList.clear();
        System.out.println("There are " + (int)exam.size() + " questions.");
        
        for (int i = 0 ; i < exam.size() ; i++) {
            questionList.add(new QuestionModule(i + 1, exam.getQuestion(i)));
        }

        itemList.getChildren().clear();
        for (int i = 0 ; i < questionList.size() ; i++) {
            itemList.getChildren().add(questionList.get(i));
        }

    }

    public double getPercentage() {
        double sum = 0;
        for (int i = 0 ; i < questionList.size() ; i++) {
            if (questionList.get(i).isCorrect()) {
                sum += 1;
            }
        }

        return (sum/questionList.size()) * 100;
    }

    public void setSubmitButtonEvent(EventHandler<ActionEvent> value) {
        submitButton.setOnAction(value);
    }

}