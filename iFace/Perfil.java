public class Perfil {
    
    public String endereco;
    public String ocupacao;
    
    public Perfil(String endereco, String ocupacao){

        this.endereco = endereco;
        this.ocupacao = ocupacao;

    }

    public void alteraPerfil(String endereco, String ocupacao){

        this.endereco = endereco;
        this.ocupacao = ocupacao;

    }

    public String toString(){

        return "Endereco: " + this.endereco + "\nOcupacao: " + this.ocupacao;

    }

}
