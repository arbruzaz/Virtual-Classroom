import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChoiceBoxModule extends HBox {
    
    Stage window;

    RadioButton choice = new RadioButton();
    TextField choiceField = new TextField();
    
    ModuleDisplayMode Mode = ModuleDisplayMode.Showing;

    public ChoiceBoxModule(Stage stage, String text) {
        super();
        window = stage;
        choice.setText(text);
        choiceField.setText(text);

        super.setStyle("-fx-background-color: rgb(0, 0, 0, 0.0);");
        super.setPadding(new Insets(16, 80, 16, 64));

        choice.getStylesheets().add("WhiteRadioButton.css");

        choiceField.getStylesheets().add("WhiteTextField.css");
        choiceField.prefWidthProperty().bind(window.widthProperty());

        super.getChildren().add(choice);        
    }

    public ChoiceBoxModule(Stage stage) {
        this(stage, "Choice");
    }

    public void setMode(ModuleDisplayMode mode) {
        this.Mode = mode;
    }

    public void enterModule() {
        choiceField.setText(choice.getText());
        choice.setText("");
        choice.setDisable(false);

        super.getChildren().clear();
        super.getChildren().addAll(choice, choiceField);
    }

    public void exitModule() {
        choice.setText(choiceField.getText());
        choice.setDisable(true);

        super.getChildren().clear();
        super.getChildren().add(choice);
    }

    public RadioButton getRadioButton() {
        return this.choice;
    }

    public String getText() {
        return this.choiceField.getText();
    }

    public void setToggleGroup(ToggleGroup group) {
        this.choice.setToggleGroup(group);
    }

    public void setSelected(boolean value) {
        this.choice.setSelected(value);
    }

    public boolean isSelected() {
        return this.choice.isSelected();
    }

    public void setChoice(String text) {
        choice.setText(text);
        choiceField.setText(text);
    }

}