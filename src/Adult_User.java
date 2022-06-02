public class Adult_User extends User {
    
    public boolean canBeAdmin;

    Adult_User(String name, String sex, int age, String nickname, String password) throws Exception {

        super(name, sex, age, nickname, password);
        this.canBeAdmin = true;

    }

    @Override
    public void aboutMe(){

        System.out.println("Hi! I am " + this.name +
        "\nI am an adult and I am " + this.age + " years old" +
        "\nMore about me: " +
        "\n" + this.profile.toString() +
        "\nGroups: " + this.groups.size() +
        "\nFriends: " + this.friends.size());

    }

}
