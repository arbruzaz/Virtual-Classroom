import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AboutUser {
    public static void register(String username, String password)  throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("userdata.dat");
        boolean sameuser = true;
        ArrayList<User> oldUser = new ArrayList<User>();
        if(file.exists()){
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin); 
            while(fin.available() != 0){
                User a = (User)in.readObject();
                oldUser.add(a);
                int numberUser = User.getNumberUser();
                User.setNumberUser(++numberUser);
            }
            in.close();
        }
        ObjectOutputStream writeUser = new ObjectOutputStream(new FileOutputStream("userdata.dat"));
        for (int i = 0; i < oldUser.size(); i++) {
            writeUser.writeObject(oldUser.get(i));
            if(username.equals(oldUser.get(i).getUsername())){
                sameuser = false;
            }
        }
        if(sameuser == true){
            boolean teacher = AlertBoxError.confirm("Choose", "Are you a teacher?");
            writeUser.writeObject(new User(username,password,teacher));           
            writeUser.close();
            AlertBoxError.display("Register Done!", "Now, You can use this Username.");
        }
        else{
            AlertBoxError.display("Register not Done!", "Please use others Username.");
            writeUser.close();
           
        }
    }

    public static boolean login(String username, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("userdata.dat");
        boolean canlogin = false;

        if(file.exists()){
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin); 
            while(fin.available() != 0){
                User a = (User)in.readObject();
                if(username.equals(a.getUsername()) && password.equals(a.getPassword())){
                    canlogin = true;
                    break;
                }else{
                    canlogin = false;
                }
            }
            in.close();
        }
        if(canlogin) return true;  
        else return false;
    }

    public static void updateUser(User user) throws IOException, ClassNotFoundException {
   
        File file = new File("userdata.dat");
        ArrayList<User> oldUser = new ArrayList<User>();
        if(file.exists()){ 
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin); 
            while(fin.available() != 0){ 
                User a = (User)in.readObject();
                oldUser.add(a);
            }
            in.close();
        }
        ObjectOutputStream writeUser = new ObjectOutputStream(new FileOutputStream("userdata.dat"));
        for (int i = 0; i < oldUser.size(); i++) {
            if(user.getUsername().equals(oldUser.get(i).getUsername())){ 
                oldUser.remove(i); 
                writeUser.writeObject(user);
            }
            else{
                writeUser.writeObject(oldUser.get(i));
            }
        }
        writeUser.close();
    }
    
    public static User forgotPassword(String username) throws IOException, ClassNotFoundException {
        File file = new File("userdata.dat");
        User a = null;
        if (file.exists()) {
            FileInputStream fin = new FileInputStream("userdata.dat");
            ObjectInputStream in = new ObjectInputStream(fin);
            while (fin.available() != 0) {
                a = (User) in.readObject();
                if (username.equals(a.getUsername())) {
                    break;
                }
            }
            in.close();
        }
        return a;
    }
}