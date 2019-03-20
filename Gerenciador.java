import Beans.BlocoControle;
import Beans.BlocoDados;
import Beans.Linha;
import Beans.Tupla;
import Utilitarios.ListaTabelas;
import Utilitarios.VarStatics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Gerenciador {


    private String origem,destino;
    int idConteiner;

    public Gerenciador(){};
    public Gerenciador(String origem, String destino,int idConteiner) {
        this.origem = origem;
        this.destino = destino;
        this.idConteiner = idConteiner;

    }

    public void start() throws IOException {
        RandomAccessFile raf = null;
        BlocoControle bc = new BlocoControle();
        bc.setIdConteiner(idConteiner);




        try {
            raf = new RandomAccessFile(new File(origem), "r");
            bc.setDescritor(raf.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //cria bloco da memoria para arquivo

        bc.criarBlocoControle(destino);

        BlocoDados bd = new BlocoDados(bc.getIdConteiner(), bc.getProximoBlocoLivre() );
        String linha = " ";
        int cont =0;
            while ((linha= raf.readLine())!=null){

            cont++;
            Tupla tupla = new Tupla(linha);

            if (bd.possuiEspacoNoBloco(tupla)){
                bd.gravarBlocoDadosMemoria(tupla);

            }else {
                bd.gravarBlocoArquivo(destino);
                bc.lerBlocoParaMemoria(destino);
                bd = new BlocoDados(bc.getIdConteiner(),bc.getProximoBlocoLivre());
                bd.gravarBlocoDadosMemoria(tupla);

            }


        }
        bd.gravarBlocoArquivo(destino);



        raf.close();
        System.out.println("cont "+ cont);
        System.out.println("total de linhas escritas "+ VarStatics.totalLinhas);
    }

    public byte[] input(int conteinerID,int blockId){

        byte[] bytes = new byte[4096];
        try {
            ListaTabelas listaTabelas = new ListaTabelas();

            RandomAccessFile raf = new RandomAccessFile(new File(ListaTabelas.pegarTabelas().get(conteinerID)), "rw");
            raf.seek(4096*blockId);
            raf.readFully(bytes);


        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return bytes;


    }
    public void modificarOrigemDestino(String origem, String destino){
        this.origem = origem;
        this.destino = destino;
    }

    public BlocoControle lerControle(int idConteiner) {
        BlocoControle bc = new BlocoControle();

//         bc.lerBlocoParaMemoria(VarStatics.arquivos.get(idConteiner));
        bc.lerBlocoParaMemoria(ListaTabelas.pegarTabelas().get(idConteiner));

        return bc;
    }

    public byte[] lerBlocoDado(int idConteiner, int idBloco) {
        BlocoDados bc = new BlocoDados(idConteiner,idBloco);
        bc.lerBlocoParaMemoria();
        return null;
    }
}
