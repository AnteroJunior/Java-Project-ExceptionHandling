public class Pessoa {
    
    public String nome, sexo;
    public Integer idade;

    public Pessoa(String nome, String sexo, Integer idade){

        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;

    }

    public String toString(){

        return "Nome: " + this.nome + "\n" +
        "Sexo: " + this.sexo + "\n" +
        "Idade: " + this.idade;

    }

}