import java.io.Serializable;

public class Subject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String topicName;
    private String teacherName;
    private String teacherPicture;
    private String subjectString;
    private String subjectID;


    @Override
    public String toString() {
        return "Subject{" +
                "topicName='" + topicName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    private String subjectPicture;
    private String detailSubjectString;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectPicture() {
        return subjectPicture;
    }

    public void setSubjectPicture(String subjectPicture) {
        this.subjectPicture = subjectPicture;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }

    public void setTeacherPicture(String teacherPicture) {
        this.teacherPicture = teacherPicture;
    }

    public String getSubjectString() {
        return subjectString;
    }

    public void setSubjectString(String subjectString) {
        this.subjectString = subjectString;
    }

    public String getDetailSubjectString() {
        return detailSubjectString;
    }

    public void setDetailSubjectString(String detailSubjectString) {
        this.detailSubjectString = detailSubjectString;
    }

    public Subject(String topicName, String teacherName, String teacherPicture, String subjectString, String subjectID, String subjectPicture, String detailSubjectString) {
        this.topicName = topicName;
        this.teacherName = teacherName;
        this.teacherPicture = teacherPicture;
        this.subjectString = subjectString;
        this.subjectID = subjectID;
        this.subjectPicture = subjectPicture;
        this.detailSubjectString = detailSubjectString;
    }

    public Subject(String subjectPicture, String subjectString) {
        this.subjectPicture = subjectPicture;
        this.subjectString = subjectString;
    }
}
