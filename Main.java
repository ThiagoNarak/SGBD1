import Beans.BlocoControle;
import Beans.BlocoDados;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int menu =1;
        int idConteiner;
        int idBloco;
        Gerenciador g =new Gerenciador();
        Scanner scanner = new Scanner(System.in);
        while (menu!=0){
            System.out.println("digite 1 para gerar os arquivos do banco");
            System.out.println("digite 2 para imprimir um bloco");
            System.out.println("imprimir uma tupla aleatoria de qualquer bloco e conteiner");
            menu = scanner.nextInt();
            switch (menu){
                case 1:
                    g.modificarOrigemDestino("forn-tpch.txt", "tabela1");
                    g.start();
                    g.modificarOrigemDestino("forn-tpch2.txt", "tabela2");
                    g.start();

                    break;
                case 2:
                    System.out.println("digite o id do conteiner");
                    idConteiner = scanner.nextInt();
                    System.out.println("digite id bloco dados");
                    idBloco = scanner.nextInt();
                    Teste.imprimir1LinhaEspecificaDoBloco(g.input(idConteiner,idBloco),1);

                    break;
                case 3:
                    Random random = new Random();
                    idConteiner = random.nextInt(2);

                System.out.println("conteiner: "+idConteiner);
                    idBloco = random.nextInt(1+g.lerControle(idConteiner).getProximoBlocoLivre());
                    System.out.println("idBloco: "+idBloco);

//                    Teste.imprimir1LinhaEspecificaDoBloco(g.input(idConteiner,idBloco),g.lerBlocoDado(idConteiner,idBloco));

                    break;

            }

        }





    }
}
