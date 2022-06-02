import java.util.ArrayList;
import java.util.Scanner;

public class Community {
   
    public String name;
    public String description;

    public User admin;

    public ArrayList<User> members = new ArrayList<User>();
    private ArrayList<User> members_request = new ArrayList<User>();

    public ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Inbox> inbox = new ArrayList<Inbox>();

    Community(String name, String description, User admin){

        this.name = name;
        this.description = description;
        this.admin = admin;
        this.members.add(admin);

    }

    public void showFeed(User user){

        for(Post post : this.posts){

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

                post.likePost(user);

            }

            System.out.println("Next post? (1: YES; 0/x: NO");
            option = scanner.nextInt();

            if(option != 1){

                break;

            }

            System.out.println("--------------------");

        }
    }

    public void sendRequest(User member){

        this.members_request.add(member);

    }

    public void seeRequest(User admin){

        if(this.admin.equals(admin)){

            for(int i = 0; i < this.members_request.size(); i++){

                User member = this.members_request.get(i);

                System.out.println(member.nickname + " wants to join. Accept new member? (1: YES; 0: NO)");

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();

                if(option == 1){

                    System.out.println("Member added.");

                    this.members.add(member);
                    this.members_request.remove(member);
                    member.groups.add(this);

                } else if(option == 0){

                    System.out.println("Member not added.");
                    this.members_request.remove(member);

                }

            }

        } else {

            System.out.println("Only admin can see.");

        }

    }

    public void seeCommunityInbox(User admin) throws Exception {

        if(this.admin.equals(admin)){

            for(Inbox inbox : this.inbox){

                System.out.println("-----------------------");
                System.out.println("Author: " + inbox.author.nickname +
                "\nMessage: " + inbox.text +
                "\nRead: " + inbox.read);
                System.out.println();
                System.out.println("Read: (1: YES; 0: NO)");
                System.out.println("-----------------------");

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();

                if(option == 1){

                    inbox.read = true;

                }

            }
        
        } else {

            System.out.println("Admin only!");

        }

    }

    public void sendMessage(Inbox message){

        this.inbox.add(message);

    }

}
