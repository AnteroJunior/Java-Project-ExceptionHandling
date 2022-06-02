import java.time.LocalDate;
import java.util.ArrayList;

public class Post extends Message {
    
    public int likes;
    public ArrayList<User> liked = new ArrayList<User>();
    public LocalDate date;

    Post(String text, User author){

        super(text, author);

        this.date = LocalDate.now();
        this.likes = 0;

    }

    public void likePost(User user){

        System.out.println(this.liked.contains(user));

        if(this.liked.contains(user) == true){

            System.out.println("You have liked already.");

        } else {

            this.likes += 1;

        }

    }

}
