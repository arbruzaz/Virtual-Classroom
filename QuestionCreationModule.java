import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuestionCreationModule extends VBox {
    
    Stage window;
    
    Label question = new Label();
    HBox questionBox = new HBox();
    HBox numberBox = new HBox();
    Label number = new Label("1. ");
    TextField questionField = new TextField();

    ArrayList<ChoiceBoxModule> choiceList = new ArrayList<ChoiceBoxModule>();
    HBox buttonBox = new HBox();
    Button addChoiceButton = new Button(" + Add Choice ");
    Button deleteButton = new Button(" Delete Question ");

    ModuleDisplayMode Mode = ModuleDisplayMode.Showing;
    ToggleGroup group = new ToggleGroup();

    public QuestionCreationModule(Stage stage, int inputNumber, String questionStatement) {
        super();
        super.setPadding(new Insets(16, 64, 16, 64));
        super.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");

        window = stage;
        number.setText((int)inputNumber + ". ");

        questionBox.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        questionBox.setPadding(new Insets(8, 8, 8, 8));
        questionBox.setSpacing(16);
        questionBox.getChildren().addAll(numberBox, questionField);

        numberBox.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        numberBox.setAlignment(Pos.CENTER);
        numberBox.getChildren().add(number);
        numberBox.setMinWidth(18);

        number.getStylesheets().add("WhiteLabel.css");
        number.setFont(Font.font("", FontWeight.BOLD, 16));

        question.getStylesheets().add("WhiteLabel.css");
        question.setFont(Font.font("", FontWeight.BOLD, 16));
        question.setText(number.getText() + questionStatement);

        questionField.getStylesheets().add("WhiteTextField.css");
        questionField.prefWidthProperty().bind(super.widthProperty());
        questionField.setText(questionStatement);

        buttonBox.setPadding(new Insets(12, 80, 16, 64));
        buttonBox.setSpacing(16);
        buttonBox.getChildren().addAll(addChoiceButton, deleteButton);
        addChoiceButton.getStylesheets().add("YellowButton.css");
        deleteButton.getStylesheets().add("YellowButton.css");

        super.setOnMouseEntered(e -> {
            if (Mode == ModuleDisplayMode.Showing) {
                super.setStyle("-fx-background-color: rgb(46, 50, 56, 1.0);");
                window.getScene().setCursor(Cursor.HAND);
            }
        });
        super.setOnMouseExited(e -> {
            if (Mode == ModuleDisplayMode.Showing) {
                super.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
                window.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        super.setOnMouseClicked(e -> {
            if (Mode == ModuleDisplayMode.Showing) {
                Mode = ModuleDisplayMode.Editting;
                super.getChildren().clear();
                super.getChildren().add(questionBox);
                super.setStyle("-fx-background-color: rgb(46, 50, 56, 1.0);");
                window.getScene().setCursor(Cursor.DEFAULT);

                for (int i = 0 ; i < choiceList.size() ; i++) {
                    choiceList.get(i).enterModule();
                    super.getChildren().add(choiceList.get(i));
                }

                super.getChildren().add(buttonBox);
            }
        });

        addChoiceButton.setOnAction(e -> {
            if (choiceList.size() < 10) {
                super.getChildren().clear();
                super.getChildren().add(questionBox);
                choiceList.add(new ChoiceBoxModule(window, "Choice " + (int)(choiceList.size() + 1)));
                choiceList.get(choiceList.size() - 1).setToggleGroup(group);
                for (int i = 0 ; i < choiceList.size() ; i++) {
                    choiceList.get(i).exitModule();
                    choiceList.get(i).enterModule();
                    super.getChildren().add(choiceList.get(i));
                }
                super.getChildren().add(buttonBox);
            }
        });

        
        choiceList.add(new ChoiceBoxModule(window, "Choice 1"));
        choiceList.get(0).getRadioButton().setDisable(true);
        choiceList.get(0).setToggleGroup(group);
        choiceList.get(0).setSelected(true);
        super.getChildren().addAll(question, choiceList.get(0));
    }

    public QuestionCreationModule(Stage stage, int inputNumber) {
        this(stage, inputNumber, "Question");
    }

    public void exitModule() {
        Mode = ModuleDisplayMode.Showing;
        super.getChildren().clear();
        question.setText(number.getText() + questionField.getText());
        super.getChildren().add(question);
        super.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        window.getScene().setCursor(Cursor.DEFAULT);
        
        for (int i = 0 ; i < choiceList.size() ; i++) {
            choiceList.get(i).exitModule();
            super.getChildren().add(choiceList.get(i));
        }
    }

    public boolean isShowing() {
        if (Mode == ModuleDisplayMode.Showing) {
            return true;
        }
        else return false;
    }

    public void setNumber(int inputNumber) {
        number.setText((int)inputNumber + ". ");
        question.setText(number.getText() + questionField.getText());
    }

    public int getNumber() {
        return (int)(this.number.getText().charAt(0));
    }

    public void setDeleteButtonEvent(EventHandler<ActionEvent> value) {
        deleteButton.setOnAction(value);
    }

    public Question getQuestion() {
        Question objQuestion = new Question(questionField.getText());
        for (int i = 0 ; i < choiceList.size() ; i++) {
            objQuestion.addChoice(choiceList.get(i).getText());
            if (choiceList.get(i).isSelected() == true) {
                objQuestion.setCorrectChoice(i);
            }
        }
        
        return objQuestion;
    }

    public void addChoice(String text) {
        super.getChildren().clear();
        super.getChildren().add(questionBox);
        choiceList.add(new ChoiceBoxModule(window, text));
        choiceList.get(choiceList.size() - 1).setToggleGroup(group);
        for (int i = 0 ; i < choiceList.size() ; i++) {
            choiceList.get(i).exitModule();
            choiceList.get(i).enterModule();
            super.getChildren().add(choiceList.get(i));
        }
        super.getChildren().add(buttonBox);
    }
    
    public void setCorrectChoice(int i) {
        choiceList.get(i).setSelected(true);
    }

    public void setQuestion(Question question) {
        this.question.setText(number.getText() + question.getQuestionStatement());
        questionField.setText(question.getQuestionStatement());
        
        choiceList.clear();
        for (int i = 0 ; i < question.size() ; i++) {
            choiceList.add(new ChoiceBoxModule(window, question.getChoice(i)));
            if (i == question.getCorrectChoice()) {
                choiceList.get(i).setSelected(true);
            }
        }
        
        super.getChildren().clear();
        this.question.setText(number.getText() + questionField.getText());
        super.getChildren().add(this.question);
        super.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        
        for (int i = 0 ; i < choiceList.size() ; i++) {
            choiceList.get(i).exitModule();
            super.getChildren().add(choiceList.get(i));
        }
    }
}