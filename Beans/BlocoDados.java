package Beans;

import Utilitarios.ByteArrayUtils;
import Utilitarios.VarStatics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BlocoDados {
    private int idConteiner;
    private int idBloco;
    private int tipoBloco;
    private int tamanhoDiretorioTupla;
    private int ultimoByteUsado;
    private ArrayList<Tupla> tuplaArrayList;

    public BlocoDados(int idConteiner, int idBloco) {
        this.idConteiner = idConteiner;
        this.idBloco = idBloco;
        this.tipoBloco = 1;
        this.ultimoByteUsado = 4095;
        tamanhoDiretorioTupla = 0;

        tuplaArrayList = new ArrayList<>();

    }

    public void gravarBlocoDadosMemoria(Tupla tupla) {

        // add tupla a ao arraylist
        tuplaArrayList.add(tupla);
        //atualiza o indice da tupla onde ela começara.
        tupla.setIndiceLinha(ultimoByteUsado - tupla.getTamanhoEmBytes());
        //atualiza ultimo byte ocupado
        this.ultimoByteUsado -= tupla.getTamanhoEmBytes();
        //atualiza total de bytes usados pelas tuplas
        tamanhoDiretorioTupla += 2;


    }


    public boolean possuiEspacoNoBloco(Tupla tupla) {
        if (ultimoByteUsado - (4 + tupla.getTamanhoCaracteres() + (tupla.getColuna().length * 2)) > (10 + (this.tuplaArrayList.size() * 2))) {

            return true;
        } else {
            return false;
        }


    }

    public int getUltimoByteUsado() {
        return ultimoByteUsado;
    }

    public void setUltimoByteUsado(int ultimoByteUsado) {
        this.ultimoByteUsado = ultimoByteUsado;
    }

    public int getIdConteiner() {
        return idConteiner;
    }

    public void setIdConteiner(int idConteiner) {
        this.idConteiner = idConteiner;
    }

    public int getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(int idBloco) {
        this.idBloco = idBloco;
    }

    public int getTipoBloco() {
        return tipoBloco;
    }

    public void setTipoBloco(int tipoBloco) {
        this.tipoBloco = tipoBloco;
    }

    public int getTamanhoDiretorioTupla() {
        return tamanhoDiretorioTupla;
    }

    public void setTamanhoDiretorioTupla(int tamanhoDiretorioTupla) {
        this.tamanhoDiretorioTupla = tamanhoDiretorioTupla;
    }


    public void gravarBlocoArquivo() {
        try {
            int aux = 0;
            RandomAccessFile raf = new RandomAccessFile(new File("arquivo"), "rw");
            byte[] bloco = new byte[4096];
            BlocoControle blocoControle = new BlocoControle();
            blocoControle.lerBlocoParaMemoria();
            blocoControle.criarTiposColuna();
            ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo1Bytes(this.idConteiner), 0);
            ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo3Bytes(this.idBloco), 1);
            ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo1Bytes(this.tipoBloco), 4);
            ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo2Bytes(this.tamanhoDiretorioTupla), 5);

            ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo2Bytes(this.ultimoByteUsado), 7);
            for (int i = 0; i < this.tuplaArrayList.size(); i++) {
                ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo2Bytes(this.tuplaArrayList.get(i).getIndiceLinha()), 9 + (i * 2));
                ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo4Bytes(this.tuplaArrayList.get(i).getTamanhoEmBytes()), tuplaArrayList.get(i).getIndiceLinha());

                aux = tuplaArrayList.get(i).getIndiceLinha() + 4;

                for (int j = 0; j < this.tuplaArrayList.get(i).getColuna().length; j++) {

                    if (blocoControle.getTipos()[j]=='I'){
                        ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo2Bytes(4), aux);
                        aux += 2;

                        ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo4Bytes(Integer.parseInt(this.tuplaArrayList.get(i).getColuna()[j])), aux);
                        aux += 4;

                        //TODO: VERIFICAR CORRESPONDENTE

                    }else {
                        ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.intTo2Bytes(this.tuplaArrayList.get(i).getColuna()[j].length()), aux);
                        aux += 2;

                        ByteArrayUtils.insertBytesInArray(bloco, ByteArrayUtils.stringToByteArray(this.tuplaArrayList.get(i).getColuna()[j]), aux);
                        aux += tuplaArrayList.get(i).getColuna()[j].length();
                    }

                }
            }
            raf.seek(this.idBloco * 4096);
            raf.write(bloco);
            blocoControle.setProximoBlocoLivre(blocoControle.getProximoBlocoLivre()+1);
            blocoControle.criarBlocoControle();
            System.out.println("escrevendo o bloco: "+ this.idBloco);
            System.out.println("com "+ tuplaArrayList.size()+" linhas");
            VarStatics.totalLinhas += tuplaArrayList.size();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
