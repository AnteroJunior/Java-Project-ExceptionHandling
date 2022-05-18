import java.time.LocalDate;

public class Post {

    public String texto;
    public String autor;
    public LocalDate data;

    public Post(String texto, String autor, LocalDate data){

        this.texto = texto;
        this.autor = autor;
        this.data = data;

    }

}