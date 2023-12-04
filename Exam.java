import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable {
        ArrayList<Question> questionLists = new ArrayList<Question>();
        String title = "Title";
        String description = "Description";
        String subjectname;

        public Exam() {
            
        }

        public Exam(String subjectname) {
            this.subjectname = subjectname;
        }

        public String getSubjectname(){
            return this.subjectname;
        }
        public void loadFromFile(String location) {

        }

        public void setTitle(String text) {
            this.title = text;
        }

        public String getTitle() {
            return title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setQuestion(int index, Question element) {
            questionLists.set(index, element);
        }

        public Question getQuestion(int index) {
            return questionLists.get(index);
        }

        public void addQuestion(Question question) {
            questionLists.add(question);
        }

        public int size() {
            return questionLists.size();
        }

        public void removeQuestion(int index) {
            questionLists.remove(index);
        }

        public String toString() {
            String text = title;
            for (int i = 0 ; i < questionLists.size() ; i++) {
                text += "\n" + (1 + i) + ". " + questionLists.get(i).toString();
            }
            return text;
        }

        public void setSubjectname(String subjectname) {
            this.subjectname = subjectname;
        }

    
}