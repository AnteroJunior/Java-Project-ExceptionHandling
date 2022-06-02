import java.io.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IFace {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        
        // Global variables
        User loggedUser = null;
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Community> groups = new ArrayList<Community>();
        
        int option;
        boolean condition = true;

        while(condition){

            if(loggedUser == null){

                menuNotLogged();
                                
                try {

                    option = scanner.nextInt();
    
                    if(option == 1){ //Sign up

                        String userName, sex, userNickname, userPassword;
                        int age;
    
                        userName = console.readLine("Your name: ");
                        sex = console.readLine("Your sex: ");
                        System.out.print("Your age: ");
                        age = scanner.nextInt();

                        userNickname = console.readLine("Your nickname: ");
                        userPassword = console.readLine("Your password: ");

                        //Search for nickname use
                        if(searchUser(userNickname, users) != null){

                            throw new Exception("This nickname is already being used. Please, choose another nickname.");

                        } else if(age >= 13 && age <= 17){

                            System.out.println("User created. Please, sign up to see more options.");

                            users.add(new Teen_User(userName, sex, age, userNickname, userPassword));

                        } else if(age >= 18 && age <= 120){

                            users.add(new Adult_User(userName, sex, age, userNickname, userPassword));

                            System.out.println("User created. Please, sign up to see more options.");

                        } else {

                            throw new Exception("Too young or you should be dead by now.");

                        }

                    } else if(option == 2){ //Login
    
                        String nickname = console.readLine("Nickname: ");
                        String password = console.readLine("Password: ");
                        
                        try {
                            
                            User user = searchUser(nickname, users);

                            if(user != null){

                                if(user.getPassword().equals(password)){

                                    System.out.println("Welcome! Enjoy iFace.");

                                    loggedUser = user;

                                } else {

                                    throw new Exception("Wrong password! Try again.");

                                }

                            } else {

                                throw new Exception("User not found.");

                            }

                        } catch (Exception e) {

                            System.out.println(e);

                        }

    
                    } else if(option == 3){
    
                        System.out.println("Quit system");
                        break;
    
                    } else {

                        throw new Exception("Ooops! You have selected an invalid option.");

                    }

                } catch (InputMismatchException e){

                    break;

                } catch(Exception e){

                    System.out.println(e);

                }

            } else { //Logged

                menuLogged();

                try {

                    option = scanner.nextInt();

                    if(option == 1){ //My Account

                        menuAccount();
                        
                        option = scanner.nextInt();

                        try {
                            
                            if(option == 1){ //Create or edit profile

                                String address = console.readLine("Address: ");
                                String occupation = console.readLine("Occupation: ");

                                loggedUser.profile = new Profile(address, occupation);

                            } else if(option == 2){ // Change feed visibility

                                System.out.println("Are you sure you want to change your feed visibility? (1: YES; 0: NO)");
                                option = scanner.nextInt();

                                if(option == 1 || option == 0){

                                    loggedUser.changeFeedVisibility(option);

                                } else {

                                    throw new Exception();

                                }

                            } else if(option == 3){ //Delete my account

                                loggedUser.deleteAccount(groups);
                                users.remove(loggedUser);
                                loggedUser = null;

                            } else if(option == 4){ // About me

                                loggedUser.aboutMe();

                            } else if(option == 5){ // See my messages

                                loggedUser.checkMessages();

                            } else if(option == 6){ //See friend requests

                                loggedUser.seeFriendRequest();

                            } else {

                                throw new Exception();

                            }

                        } catch (Exception e) {

                            System.out.println("Ooops! You have inserted an invalid option. Try again.");

                        }

                    } else if(option == 2){ //Friends

                        menuFriends();

                        option = scanner.nextInt();

                        try {

                            if(option == 1){ //Add friends

                                String friend_name = console.readLine("Friend nickname: ");

                                User friend = searchUser(friend_name, users);

                                if(friend != null){

                                    loggedUser.sendFriendRequest(friend);

                                } else {

                                    throw new Exception("User not found.");

                                }

                            } else if(option == 2){ //See friend feed

                                String friend_name = console.readLine("Friend nickname: ");

                                User friend = searchUser(friend_name, users);

                                if(friend != null){

                                    friend.showMyFeed(loggedUser);

                                } else {

                                    throw new Exception("User not found.");

                                }

                            } else {

                                throw new Exception("Ooops! You have selected an invalid option.");

                            }
                            
                        } catch (Exception e) {
                            
                            System.out.println(e);

                        }


                    } else if(option == 3){ //Community

                        menuCommunity();

                        try {

                            option = scanner.nextInt();

                            if(option == 1){ //Create community

                                String group_name = console.readLine("Group name: ");
                                String group_description = console.readLine("Group description: ");

                                if(searchCommunity(group_name, groups) != null || loggedUser.age <= 17){

                                    throw new Exception("Name already in use or age under 18.");

                                } else {

                                    System.out.println("Group created.");

                                    Community new_group = new Community(group_name, group_description, loggedUser);

                                    loggedUser.groups.add(new_group);
                                    groups.add(new_group);

                                }

                            } else if(option == 2){ //Send request

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    group.sendRequest(loggedUser);

                                } else {
                                    
                                    throw new Exception("Group not found.");

                                }

                            } else if(option == 3){ //See feed

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    group.showFeed(loggedUser);

                                } else {
                                    
                                    throw new Exception("Group not found.");

                                }

                            } else if(option == 4){ //See requests (admin)

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    group.seeRequest(loggedUser);

                                } else {
                                    
                                    throw new Exception("Group not found.");

                                }

                            } else if(option == 5){ //See inbox

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    group.seeCommunityInbox(loggedUser);

                                } else {
                                    
                                    throw new Exception("Group not found.");

                                }

                            } else {

                                throw new Exception("Ooops! You have selected an invalid option.");

                            }

                        } catch (Exception e) {

                            System.out.println(e);

                        }

                    } else if(option == 4){ //Messages

                        menuMessages();

                        try {

                            option = scanner.nextInt();

                            if(option == 1){ //Post user feed

                                String user_nickname = console.readLine("User nickname: ");

                                User user = searchUser(user_nickname, users);

                                if(user != null){

                                    if(user.friends.contains(loggedUser)){

                                        String text = console.readLine("Insert your message: ");

                                        Post new_post = new Post(text, loggedUser);

                                        user.myFeed.add(new_post);

                                    } else {

                                        throw new Exception("Only friends can post at user feed.");

                                    }

                                } else {

                                    throw new Exception("User not found.");

                                }

                            } else if(option == 2){ //Post group feed

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    if(group.members.contains(loggedUser)){

                                        String text = console.readLine("Insert your message: ");

                                        Post new_post = new Post(text, loggedUser);

                                        group.posts.add(new_post);
 
                                    } else {

                                        throw new Exception("Only members can post.");

                                    }

                                } else {

                                    throw new Exception("Group not found.");

                                }

                            } else if(option == 3){ //Send user message

                                String user_nickname = console.readLine("User nickname: ");

                                User user = searchUser(user_nickname, users);

                                if(user != null){

                                    String text = console.readLine("Insert your message: ");

                                    Inbox new_message = new Inbox(text, loggedUser);

                                    user.sendMessage(new_message);

                                    System.out.println("Message sent successfully.");

                                } else {

                                    throw new Exception("User not found.");

                                }


                            } else if(option == 4){ //Send group message

                                String group_name = console.readLine("Group name: ");

                                Community group = searchCommunity(group_name, groups);

                                if(group != null){

                                    String text = console.readLine("Insert your message: ");

                                    Inbox new_message = new Inbox(text, loggedUser);

                                    group.sendMessage(new_message);

                                    System.out.println("Message sent successfully.");

                                } else {

                                    throw new Exception("Group not found.");

                                }

                            } else {

                                throw new Exception("Ooops! You have selected an invalid option");                                

                            }

                        } catch (Exception e){

                            System.out.println(e);

                        }

                    } else if(option == 5){ //Get user information

                        String user_nickname = console.readLine("Search user: ");

                        User user = searchUser(user_nickname, users);

                        if(user != null){

                            user.aboutMe();

                        } else {

                            throw new Exception("User not found.");

                        }

                    } else if(option == 6){ //Log out

                        System.out.println("Exit!");
                        loggedUser = null;

                    } else {

                        throw new Exception();

                    }

                } catch(InputMismatchException e){

                    break;

                } catch (Exception e) {
                    
                    System.out.println("Ooops! You have selected an invalid option.");

                }

            }


        }

    }

    /* Métodos - Menu (logado e deslogado) */
    public static void menuNotLogged(){

        System.out.println("--------------------------");
        System.out.println("1. Sign up" +
        "\n2. Sign in" +
        "\n3. Quit");
        System.out.println("--------------------------");


    }

    public static void menuLogged(){

        System.out.println("--------------------------");
        System.out.println("1. Account" +
        "\n2. Friends" +
        "\n3. Groups" +
        "\n4. Messages" +
        "\n5. Recover informations" +
        "\n6. Log out");
        System.out.println("--------------------------");

    }

    public static void menuAccount(){

        System.out.println("--------------------------");

        System.out.println("1. Create or edit profile" +
        "\n2. Change feed visibility" +
        "\n3. Delete account" +
        "\n4. About me" +
        "\n5. See my messages" +
        "\n6. See friend requests");

        System.out.println("--------------------------");

    }

    public static void menuFriends(){

        System.out.println("--------------------------");
        System.out.println("1. Add friends" +
        "\n2. See friend feed");
        System.out.println("--------------------------");

    }

    public static void menuCommunity(){

        System.out.println("--------------------------");
        System.out.println("1. Create community" +
        "\n2. Send request to a community" +
        "\n3. See community feed" +
        "\n4. See community requests (admin only!)" +
        "\n5. See community inbox (admin only!)");
        System.out.println("--------------------------");

    }

    public static void menuMessages(){

        System.out.println("--------------------------");
        System.out.println("1. Post on user's feed" +
        "\n2. Post on community feed" +
        "\n3. Send message to user" +
        "\n4. Send message to group");
        System.out.println("--------------------------");

    }

    public static User searchUser(String userNickname, ArrayList<User> users_list){
        
        for(int i = 0; i < users_list.size(); i++){

            if(users_list.get(i).nickname.equals(userNickname)){

                return users_list.get(i);

            }

        }

        return null;

    }

    public static Community searchCommunity(String group_name, ArrayList<Community> groups){

        for(Community community : groups){

            if(community.name.equals(group_name)){

                return community;

            }

        }

        return null;

    }

}
