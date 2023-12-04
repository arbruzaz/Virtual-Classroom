
import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3667146219519736893L;
    private ArrayList<Subject> subjectArrayList;
    private String topicName;

    @Override
    public String toString() {
        return "Topic{" +
                "subjectArrayList=" + subjectArrayList +
                ", topicName='" + topicName + '\'' +
                '}';
    }

    public  Topic(){}
    public Topic(String topicName) {
        this.topicName = topicName;
        this.subjectArrayList = new ArrayList<>();
    }

    public ArrayList<Subject> getSubjectArrayList() {
        return subjectArrayList;
    }

    public void setSubjectArrayList(Subject subject) {
        this.subjectArrayList.add(subject);
    }

    public String getTopicName() {
        return topicName;
    }


}
