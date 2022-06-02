import java.time.LocalDate;

public class Inbox extends Message {
    
    public boolean read;
    public LocalDate date;

    Inbox(String text, User author){

        super(text, author);

        this.read = false;
        this.date = LocalDate.now();

    }

}
