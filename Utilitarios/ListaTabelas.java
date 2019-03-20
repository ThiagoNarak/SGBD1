package Utilitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ListaTabelas {


    public static ArrayList<String> pegarTabelas(){
        ArrayList<String> listaTabelas = new ArrayList<>();
        try {

            RandomAccessFile    raf = new RandomAccessFile(new File("tabelaLista"), "r");
            String linha = " ";
            while ((linha= raf.readLine())!=null){

                listaTabelas.add(linha);

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaTabelas;
    }
}
