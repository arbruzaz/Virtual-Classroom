import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Tabledash
 */
public class Tabledash {

    public static ObservableList<Subject> getSubject(User user){
        ObservableList<Subject> allsubjects = FXCollections.observableArrayList();
        for(int i = 0;i < user.getUserSubject().size(); i++){
            allsubjects.add(user.getUserSubject().get(i));
        }
        return allsubjects;
    }
}
