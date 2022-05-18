import java.io.IOException;
import java.util.ArrayList;

class FuncoesMain {

    public static void menuDeslogado(){

        System.out.println("--------------------------------------");
        System.out.println("1. Criar conta" + 
        "\n2. Fazer login" +
        "\n3. Sair do sistema");
        System.out.println("--------------------------------------");

    }

    public static void menuLogado(){
        System.out.println("--------------------------------------");
        System.out.println("1. Usuario" +
        "\n2. Amigos" +
        "\n3. Comunidade" +
        "\n4. Mensagem" +
        "\n5. Recuperar informacoes de um usuario" +
        "\n6. Deslogar");
        System.out.println("--------------------------------------");

    }

    public static void menuOpcaoUsuario(){
        
        System.out.println("--------------------------------------");
        System.out.println("1. Criar/Editar perfil" +
        "\n2. Apagar conta" +
        "\n3. Controle do feed (publico/privado)" +
        "\n4. Voltar");
        System.out.println("--------------------------------------");

    }

    public static void menuOpcaoAmigos(){
        
        System.out.println("--------------------------------------");
        System.out.println("1. Adicao de amigos" +
        "\n2. Ver pedidos de amizade");
        System.out.println("--------------------------------------");

    }

    public static void menuOpcaoComunidade(){

        System.out.println("--------------------------------------");
        System.out.println("1. Criar comunidade" +
        "\n2. Entrar em uma comunidade" +
        "\n3. Verificar solicitacao para entrar" +
        "\n4. Verificar mensagens" +
        "\n5. Verificar feed de uma comunidade");
        System.out.println("--------------------------------------");

    }

    public static void menuOpcaoMensagem(){

        System.out.println("--------------------------------------");
        System.out.println("1. Enviar mensagem para usuario" +
        "\n2. Enviar mensagem para comunidade" +
        "\n3. Publicar no feed de usuario" +
        "\n4. Publicar no feed de comunidade" +
        "\n5. Verificar minhas mensagens");
        System.out.println("--------------------------------------");

    }

    public static void checaLista(String nickname, ArrayList<Usuario> listaUsuarios) throws IOException {

        for(Usuario usuarioAtual : listaUsuarios){

            if(nickname.equals(usuarioAtual.nickname)){
             
                throw new IOException("Usuario ja existe");
            
            }
        }

    }

    public static boolean buscaUsuario(String nickname, ArrayList<Usuario> listaUsuarios) throws IOException {

        for(Usuario usuarioAtual : listaUsuarios){

            if(nickname.equals(usuarioAtual.nickname)){
             
                return false;
            
            }
        }

        throw new IOException("Usuario nao existe!");

    }

    public static boolean buscaComunidade(String comunidade, ArrayList<Comunidade> listaComunidades){

        for(Comunidade comunidadeAtual : listaComunidades){

            if(comunidadeAtual.nome.equals(comunidade)){

                return true;

            }

        }

        return false;

    }

    public static Usuario fazLogin(String nickname, String password, ArrayList<Usuario> listaUsuarios){

        for(Usuario usuarioAtual : listaUsuarios){

            if(nickname.equals(usuarioAtual.nickname)){

                if(password.equals(usuarioAtual.getPassword())){

                    System.out.println("Ola, " + nickname + "! Voce sera redirecionado em alguns instantes.");
                    return usuarioAtual;

                } else {

                    System.out.println("Senha incorreta. Tente novamente.");
                    return null;

                }

            }

        }

        System.out.println("Usuario nao existe.");
        return null;

    }

    public static void deletarContaUsuario(ArrayList<Usuario> lista_usuarios, ArrayList<Comunidade> lista_comunidades, Usuario usuarioAtual){

        System.out.println("Esta acao nao pode ser desfeita.");

        //Remover todas as comunidades
        //Se administrador, remover todos os usuários
        for(int i = 0; i < usuarioAtual.comunidades.size(); i++){

            Comunidade comunidade = usuarioAtual.comunidades.get(i);

            if(comunidade.administrador.equals(usuarioAtual)){

                lista_comunidades.remove(comunidade);

            }

        }

        //Operação para remover usuário
        for(int i = 0; i < lista_usuarios.size(); i++){

            if(lista_usuarios.get(i).nickname.equals(usuarioAtual.nickname)){

                lista_usuarios.remove(i);
                usuarioAtual = null;

            }

        }

    }

    public static void verificarDados(String nome, String sexo, Integer idade, String nickname, String password)
            throws IOException {

        //Fazendo verificações
                //Nome
        for(int i = 0; i < nome.length(); i++){

            if(Character.isDigit(nome.charAt(i))){

                throw new IOException("Seu nome nao deve conter digitos");

            }

        }

        //Sexo 
        for(int i = 0; i < sexo.length(); i++){

            if(Character.isDigit(nome.charAt(i)) || sexo.length() > 1){

                throw new IOException("Sexo nao pode conter mais de um caractere nem ser digito");

            }

        }

        //Idade
        if(idade < 15 || idade > 110){

            throw new IOException("Idade nao pode ser menor que 15 nem maior que 100.");

        }
    }

}