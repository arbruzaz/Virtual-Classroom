
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuestionModule extends VBox {

    int number;
    Label questionStatement = new Label();
    String statement;

    VBox choiceBox = new VBox();
    ToggleGroup group = new ToggleGroup();
    ArrayList<RadioButton> choiceList = new ArrayList<RadioButton>();

    int correctChoice;

    public QuestionModule(int inputNumber, Question question) {
        super();

        super.setStyle("-fx-background-color: rgb(54, 57, 63, 1.0);");
        super.setPadding(new Insets(16, 64, 16, 64));
        super.setSpacing(12);

        statement = question.getQuestionStatement();

        number = inputNumber;
        questionStatement.setText((int)number + ". " + statement);

        questionStatement.getStylesheets().add("DarkTheme.css");
        questionStatement.setFont(Font.font("", FontWeight.BOLD, 16));
        questionStatement.setPadding(new Insets(8, 8, 8, 8));

        choiceBox.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        choiceBox.setPadding(new Insets(16, 80, 16, 64));
        
        for (int i = 0 ; i < question.size() ; i++) {
            choiceList.add(new RadioButton(question.getChoice(i)));
            choiceList.get(i).setFont(Font.font("", FontWeight.BOLD, 16));
        }
        
        for (int i = 0 ; i < choiceList.size() ; i++) {
            choiceBox.getChildren().add(choiceList.get(i));
            choiceList.get(i).setToggleGroup(group);
        }

        correctChoice = question.getCorrectChoice();

        super.getChildren().addAll(questionStatement, choiceBox);
    }

    public boolean isCorrect() {
        for (int i = 0 ; i < choiceList.size() ; i++) {
            if (choiceList.get(i).isSelected() && i == correctChoice) {
                return true;
            }
        }
        return false;
    }

    public int getCorrectChoice() {
        return this.correctChoice;
    }
}