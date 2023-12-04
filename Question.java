import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8444550158617467279L;
    String questionStatement = "Question";
    ArrayList<String> choiceList = new ArrayList<String>();
    int correctChoice = 0;

    // Constructer the question with proposition
    public Question(String text) {
        questionStatement = text;
    }

    // Edit proposition
    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }

    // Get String of proposition
    public String getQuestionStatement() {
        return questionStatement;
    }
    
    // Add choice in the question
    public void addChoice(String text) {
        choiceList.add(text);
    }

    // Edit choice
    public void setChoice(int i, String text) {
        choiceList.set(i, text);
    }

    // Get String of each choice
    public String getChoice(int number) {
        return choiceList.get(number);
    }

    // Set the correct choice
    public void setCorrectChoice(int number) {
        correctChoice = number;
    }

    // Get the correct choice
    public int getCorrectChoice() {
        return correctChoice;
    }

    // How many choice
    public int size() {
        return choiceList.size();
    }

    public String toString() {
        String text = questionStatement;
        for (int i = 0 ; i < choiceList.size() ; i++) {
            text += "\n" + (char)(i + 65) + ". " + choiceList.get(i).toString();
        }
        text += "\n" + "Correct answer is (" + (char)(correctChoice + 65) + ")";
        return text;
    }
}