public class Teen_User extends User {
    
    public boolean canBeAdmin;

    Teen_User(String name, String sex, int age, String nickname, String password){

        super(name, sex, age, nickname, password);
        
        this.canBeAdmin = false;

    }

    @Override
    public void aboutMe(){

        System.out.println("Hi! I am " + this.name +
        "\nI am a teenager and I am " + this.age + " years old" +
        "\nMore about me: " +
        "\n" + this.profile.toString() +
        "\nGroups: " + this.groups.size() +
        "\nFriends: " + this.friends.size());

    }

}
