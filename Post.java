import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = -6070891398251750759L;
	private String post;
    private String subject;
    private ArrayList<String> comment;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Post(String subject, String post) {
        this.subject = subject;
        this.post = post;
        comment = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void addComment(String comment){
        this.comment.add(comment);
    }
}