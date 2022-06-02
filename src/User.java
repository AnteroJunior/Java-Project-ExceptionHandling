import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person {
    
    public String nickname;
    private String password;

    private ArrayList<Inbox> inbox = new ArrayList<Inbox>();

    private ArrayList<User> friend_request = new ArrayList<User>();
    public ArrayList<User> friends = new ArrayList<User>();

    public ArrayList<Community> groups = new ArrayList<Community>();

    public Profile profile = null;

    public boolean publicFeed = true;
    public ArrayList<Post> myFeed = new ArrayList<Post>();

    public User(String name, String sex, int age, String nickname, String password) throws Exception {

        super(name, sex, age);

        if(name.equals("") || sex.equals("") || nickname.equals("") || password.equals("")){

            throw new Exception("Blank fields. Try again!");

        }

        this.nickname = nickname;
        this.password = password;

    }

    /* User methods */
    public String getPassword(){

        return password;

    }

    public void changeFeedVisibility(int answer){

        if(this.publicFeed){

            System.out.println("Your feed is public.");

        } else {

            System.out.println("Your feed is private.");

        }

        if(answer == 1){

            this.publicFeed = !this.publicFeed;
            
            if(this.publicFeed){

                System.out.println("Now your feed is public.");

            } else {

                System.out.println("Now your feed is private.");

            }

        } else {

            System.out.println("Feed visibility not changed.");

        }

    }

    public void seeMyMessages(){

    }

    public void aboutMe(){

        System.out.println("Hi! I am a user");

    }

    public void checkMessages(){

        for(Inbox inbox : this.inbox){

            System.out.println("Author: " + inbox.author.nickname +
            "\nMessage: " + inbox.text +
            "\nRead: " + inbox.read);
            System.out.println();
            System.out.println("Read: (1: YES; 0: NO)");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            if(option == 1){

                inbox.read = true;

            }

        }

    }

    public void sendFriendRequest(User friend){

        friend.friend_request.add(this);

    }

    public void seeFriendRequest(){

        for(User friend : this.friend_request){

            System.out.println(friend.nickname + " wants to be your friend. Accept? (1: YES; 0/X: NO)");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            if(option == 1){

                System.out.println("Friend added.");

                this.friends.add(friend);
                friend.friends.add(this);

                this.friend_request.remove(friend);

            } else {
                
                this.friend_request.remove(friend);

            }

        }

    }

    public void showMyFeed(User friend){

        if(this.publicFeed){

            for(Post post : this.myFeed){

                System.out.println("--------------------");

                System.out.println("Author: " + post.author.nickname +
                "\nPost: " + post.text + 
                "\nDate: " + post.date +
                "\n" +
                "\nLikes: " + post.likes);

                System.out.println("Like button: (1: YES; 0: NO)");
                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();

                if(option == 1){

                    post.likePost(friend);

                }

                System.out.println("Next post? (1: YES; 0/x: NO)");
                option = scanner.nextInt();

                if(option != 1){

                    break;

                }

                System.out.println("--------------------");

            }

        } else { //Private

            if(this.friends.contains(friend)){

                for(Post post : friend.myFeed){

                    System.out.println("--------------------");
    
                    System.out.println("Author: " + post.author.nickname +
                    "\nPost: " + post.text + 
                    "\nDate: " + post.date +
                    "\n" +
                    "\nLikes: " + post.likes);
    
                    System.out.println("Like button: (1: YES; 0: NO)");
                    Scanner scanner = new Scanner(System.in);
                    int option = scanner.nextInt();
    
                    if(option == 1){
    
                        post.likePost(this);
    
                    }
    
                    System.out.println("Next post? (1: YES; 0/x: NO");
                    option = scanner.nextInt();
    
                    if(option != 1){
    
                        break;
    
                    }

                    System.out.println("--------------------");
    
                }

            } else {

                System.out.println("Only friends can see his/her feed.");

            }

        }

    }

    public void sendMessage(Inbox message){

        this.inbox.add(message);

    }

    public void deleteAccount(ArrayList<Community> groups){

        //Delete friends
        for(User friend : this.friends){

            friend.friends.remove(this);
            this.friends.remove(friend);

        }

        //Delete groups
        for(Community group : this.groups){

            //User is admin
            if(group.admin.equals(this)){

                for(int i = 0; i < group.members.size(); i++){

                    group.members.remove(i);

                }

                groups.remove(group);

            } else {

                group.members.remove(this);

            }

        }

    }

}
