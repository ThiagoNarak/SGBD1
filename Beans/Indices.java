package Beans;

import Utilitarios.ByteArrayUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Indices {
    private ArrayList<RowId> rowIdArrayList;

    public Indices() {
        this.rowIdArrayList = new ArrayList<>();
    }

    public void recuperarRowIdsFrom(String parametro) {
        try {
            RandomAccessFile raf = new RandomAccessFile(parametro, "rw");

            raf.seek(0);
            byte tamanhoAlias[] = new byte[2];
            byte alias[] = new byte[ByteArrayUtils.byteArrayToInt(tamanhoAlias)];
            byte tamanhoGeral[] = new byte[3];
            byte numeroIndices[] = new byte[4];

            raf.readFully(tamanhoAlias, 0, 2);
            raf.readFully(alias, 2, ByteArrayUtils.byteArrayToInt(tamanhoAlias));
            raf.readFully(tamanhoGeral, 20, 3);
            raf.readFully(numeroIndices, 23, 3);
            int somatorioBytes = 26;
            for (int i = 0; i < ByteArrayUtils.byteArrayToInt(numeroIndices); i++) {
                byte tamanhoRowId[] = new byte[4];
                byte colunaTamanho[] = new byte[2];
                byte coluna[];
                byte idConteinerTamanho[];
                byte idConteiner[];
                byte idBLocoTamanho[];
                byte idBloco[];
                byte idTuplaTamanho[];
                byte idTupla[];

                raf.readFully(tamanhoRowId, somatorioBytes, 4);
                raf.readFully(colunaTamanho,somatorioBytes+(4*i)+4,2);
                raf.readFully(coluna = new byte[ByteArrayUtils.byteArrayToInt(colunaTamanho)] ,somatorioBytes+(4*i)+4+(i*2)+2,ByteArrayUtils.byteArrayToInt(colunaTamanho));
//                raf.readFully();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
