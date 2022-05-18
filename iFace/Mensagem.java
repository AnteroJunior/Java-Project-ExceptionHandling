import java.time.LocalDate;

public class Mensagem {
    
    public String texto;
    public String autor;
    public LocalDate data;

    public Mensagem(String texto, String autor, LocalDate data){

        this.texto = texto;
        this.autor = autor;
        this.data = data;

    }

}
