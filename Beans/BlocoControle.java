package Beans;

import Utilitarios.ByteArrayUtils;
import Utilitarios.VarStatics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;


public class BlocoControle {
    private int idConteiner;
    private int tamanhoBloco;
    private int statusConteiner;
    private int proximoBlocoLivre;
    private int tamanhoDescritor;
    private String descritor;
    private char tipos[];

    public BlocoControle() {

        this.idConteiner = 0;
        this.tamanhoBloco = 4096;
        this.statusConteiner = 0;
        this.proximoBlocoLivre = 1;
        this.tamanhoDescritor = 0;
        this.descritor = "";

    }

    public int getIdConteiner() {
        return idConteiner;
    }

    public void setIdConteiner(int idConteiner) {
        this.idConteiner = idConteiner;
    }

    public int getTamanhoBloco() {
        return tamanhoBloco;
    }

    public void setTamanhoBloco(int tamanhoBloco) {
        this.tamanhoBloco = tamanhoBloco;
    }

    public int getStatusConteiner() {
        return statusConteiner;
    }

    public void setStatusConteiner(int statusConteiner) {
        this.statusConteiner = statusConteiner;
    }

    public int getProximoBlocoLivre() {
        return proximoBlocoLivre;
    }

    public void setProximoBlocoLivre(int proximoBlocoLivre) {
        this.proximoBlocoLivre = proximoBlocoLivre;
    }

    public int getTamanhoDescritor() {
        return tamanhoDescritor;
    }

    public void setTamanhoDescritor(int tamanhoDescritor) {
        this.tamanhoDescritor = tamanhoDescritor;
    }

    public String getDescritor() {
        return descritor;
    }

    public void setDescritor(String descritor) {
        this.descritor = descritor;
        this.tamanhoDescritor = descritor.length();
    }

    @Override
    public String toString() {
        System.out.println("BlocoControle{" +
                "\nidConteiner=" + idConteiner +
                ",\n tamanhoBloco=" + tamanhoBloco +
                ",\n statusConteiner=" + statusConteiner +
                ",\n proximoBlocoLivre=" + proximoBlocoLivre +
                ",\n tamanhoDescritor=" + tamanhoDescritor +
                ",\n descritor='" + descritor + '\'' +
                "\n" + '}');

        return "";
    }

    public void criarTiposColuna() {
        String descritores[] = descritor.split("\\[");
        this.tipos = new char[descritores.length - 1];
        for (int i = 1; i < descritores.length; i++) {
            this.tipos[i - 1] = (descritores[i].charAt(0));
        }
    }

    public void criarBlocoControle(String path) {
        try {
            RandomAccessFile tabela = new RandomAccessFile(new File(path), "rw");
            tabela.seek(0);
            tabela.write(new byte[4096]);
            tabela.seek(0);
            tabela.write(Utilitarios.ByteArrayUtils.intTo1Bytes(this.idConteiner)); // escrevendo id em bytes
            tabela.seek(1);
            tabela.write(Utilitarios.ByteArrayUtils.intTo3Bytes(this.tamanhoBloco));
            tabela.seek(4);
            tabela.write(Utilitarios.ByteArrayUtils.intTo1Bytes(this.statusConteiner));
            tabela.seek(5);
            tabela.write(Utilitarios.ByteArrayUtils.intTo4Bytes(this.proximoBlocoLivre));
            tabela.seek(9);
            tabela.write(Utilitarios.ByteArrayUtils.intTo2Bytes(this.tamanhoDescritor));
            tabela.seek(11);
            tabela.write(Utilitarios.ByteArrayUtils.stringToByteArray(this.descritor));
            criarTiposColuna();

        } catch (FileNotFoundException e) {
            System.out.println("deu pau ao gravar o bloco Controle");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public char[] getTipos() {
        return tipos;
    }

    public void setTipos(char[] tipos) {
        this.tipos = tipos;
    }

    public void lerBlocoParaMemoria(String path) {
        byte []  bytes = new byte[4096];
        try {
            RandomAccessFile raf = new RandomAccessFile(path,"r");
            raf.seek(0);
            raf.readFully(bytes);
            this.idConteiner = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes,0,1));
            this.tamanhoBloco = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes,1,3));
            this.statusConteiner =  ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes,4,1));
            this.proximoBlocoLivre =  ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes,5,4));
            this.tamanhoDescritor =  ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes,9,2));
            this.descritor = ByteArrayUtils.byteArrayToString(ByteArrayUtils.subArray(bytes,11,tamanhoDescritor));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
