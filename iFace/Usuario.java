import java.util.ArrayList;
import java.util.Scanner;

public class Usuario extends Pessoa implements FuncoesUsuario {
    
    public String nickname;
    private String password;
    public boolean perfilPublico = true;
    public Perfil perfil = null;

    //Listas
    private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
    private ArrayList<Usuario> solicitacoesAmizade = new ArrayList<Usuario>();
    public ArrayList<Usuario> amigos = new ArrayList<Usuario>();
    public ArrayList<Comunidade> comunidades = new ArrayList<Comunidade>();
    public ArrayList<Post> feed = new ArrayList<Post>();

    public Usuario(String nome, String sexo, Integer idade, String nickname, String password){

        super(nome, sexo, idade);
        this.nickname = nickname;
        setPassword(password);

    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void insereSolicitacaoAmizade(Usuario usuario){

        this.solicitacoesAmizade.add(usuario);

    }

    public void verificaSolicitacoes(){

        Scanner scanner = new Scanner(System.in);

        for(Usuario usuario : this.solicitacoesAmizade){

            System.out.print(usuario.nickname + " quer ser seu amigo. Aceita? (Y = 1/N = 0) ");
            
            Integer opcaoUsuario = scanner.nextInt();

            if(opcaoUsuario == 1){

                this.amigos.add(usuario);

                usuario.amigos.add(this);

            } else if(opcaoUsuario == 0) {

                this.solicitacoesAmizade.remove(usuario);

            }

        }

    }

    @Override
    public void mudarVisibilidadeFeed(){

        if(this.perfilPublico){

            System.out.println("Seu perfil sera mudado para privado.");
            this.perfilPublico = false;

        } else {

            System.out.println("Seu perfil sera mudado para publico.");
            this.perfilPublico = true;

        }

    }

    @Override
    public String toString(){

        return "Nome: " + this.nome + "\n" +
        "Nickname: " + this.nickname + "\n" +
        "Sexo: " + this.sexo + "\n" +
        "Idade: " + this.idade +
        "\nAmigos: " + this.amigos.size() +
        "\nComunidades: " + this.comunidades.size();

    }

    public void mostraFeed(Usuario usuario){

        for(Post post : usuario.feed){

            System.out.println("Entrou");

            System.out.println("------------------------------");
            System.out.println("Mensagem: " + post.texto +
            "\nAutor: " + post.autor +
            "\nData: " + post.data);

            System.out.println("------------------------------");

        }

    }

    public void enviaMensagem(Mensagem mensagem){

        this.mensagens.add(mensagem);

    }

    public void mostraMensagens(){

        for(Mensagem mensagem : this.mensagens){

            System.out.println("Mensagem: " + mensagem.texto +
            "\nAutor: " + mensagem.autor +
            "\nData: " + mensagem.data);

            System.out.println();
            System.out.println("1. Proxima" +
            "\n2. Sair");

            Scanner scanner = new Scanner(System.in);
            Integer opcaoUsuario = scanner.nextInt();

            if(opcaoUsuario == 1){

                continue;

            } else {

                break;

            }

        }

    }

}
