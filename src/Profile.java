public class Profile {
    
    public String address;
    public String occupation;

    Profile(String address, String occupation){

        this.address = address;
        this.occupation = occupation;

    }

    Profile(Character nothing, String occupation){

        this.occupation = occupation;

    }

    Profile(String address, Character nothing){

        this.address = address;

    }

    public String toString(){

        return "Address: " + this.address + 
        "\nOccupation: " + this.occupation;

    }

}
