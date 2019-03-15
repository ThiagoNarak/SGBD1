import Beans.BlocoControle;
import Beans.BlocoDados;
import Beans.Linha;
import Beans.Tupla;
import Utilitarios.VarStatics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Gerenciador {

    public void start() throws IOException {
        RandomAccessFile raf = null;
        BlocoControle bc = new BlocoControle();
        try {
            raf = new RandomAccessFile(new File("forn-tpch.txt"), "r");
            bc.setDescritor(raf.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //cria bloco da memoria para arquivo

        bc.criarBlocoControle();

        BlocoDados bd = new BlocoDados(bc.getIdConteiner(), bc.getProximoBlocoLivre() );
        String linha = " ";
        int cont =0;
            while ((linha= raf.readLine())!=null){

            cont++;
            Tupla tupla = new Tupla(linha);

            if (bd.possuiEspacoNoBloco(tupla)){
                bd.gravarBlocoDadosMemoria(tupla);

            }else {
                bd.gravarBlocoArquivo();
                bc.lerBlocoParaMemoria();
                bd = new BlocoDados(bc.getIdConteiner(),bc.getProximoBlocoLivre());
                bd.gravarBlocoDadosMemoria(tupla);

            }


        }
        bd.gravarBlocoArquivo();



        raf.close();
        System.out.println("cont "+ cont);
        System.out.println("total de linhas escritas "+ VarStatics.totalLinhas);
    }

}
