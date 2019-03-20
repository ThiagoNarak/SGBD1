import Beans.BlocoControle;
import Utilitarios.ByteArrayUtils;
import Utilitarios.VarStatics;

import java.io.*;
import java.util.Arrays;


public class Teste {
    public static void main(String[] args) throws IOException {
//gerarBytesToTxt("tabela2");
        imprimirHeaderDados();


//        regexTeste();
//        imprimirLinhas();
//        imprimirLinhasBlocoDados(testeEscritaArquivoDados());
//        representacaoGraficaBloco();
//        testeEscritaArquivocontrole();
    }

    public static void regexTeste() {
        String descritor = "COD_FORN[I(6)]|NOME_FORN[A(25)]|END_FORN[A(40)]|NASC_FORN[I(4)]|FON_FORN[A(15)]|COM_CLI[A(101)]|";
        char tipos[] = new char[10];
        String descritores[] = descritor.split("\\[");
        for (int i = 1; i < descritores.length; i++) {
            tipos[i - 1] = descritores[i].charAt(0);
        }
        System.out.println(Arrays.toString(tipos));

    }

    public static void imprimirLinhas() {

        try {
            RandomAccessFile raf = new RandomAccessFile(new File("forn-tpch.txt"), "r");
            String linha;
            while ((linha = raf.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gerarBytesToTxt(String path) {
        try {
            RandomAccessFile raf = new RandomAccessFile("tabela2", "r");
            BlocoControle blocoControle = new BlocoControle();
            blocoControle.lerBlocoParaMemoria("tabela2");
            for (int i = 0; i < blocoControle.getProximoBlocoLivre(); i++) {
                byte[] bytes = testeEscritaArquivoDados(path, i + 1);
                int numeroDeLinhas = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes, 5, 2)) / 2;
                System.out.println("blocoAtual " + (i + 1));
                for (int j = 1; j <= numeroDeLinhas; j++) {
                    imprimir1LinhaEspecificaDoBloco(bytes, j);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void imprimir1LinhaEspecificaDoBloco(byte[] bytes, int linha) {
        int tamanhoLinha, tamanhoColuna1, tamanhoColuna2, tamanhoColuna3, tamanhoColuna4, tamanhoColuna5, tamanhoColuna6, indiceLinha;
        byte auxBytes[];
        int coluna1, coluna4;
        String coluna2, coluna3, coluna5, coluna6;
        indiceLinha = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes, 9 + (2 * (linha - 1)), 2));
        auxBytes = ByteArrayUtils.subArray(bytes, indiceLinha, ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes, indiceLinha, 4)));
        tamanhoColuna1 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 4, 2));
        coluna1 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 6, tamanhoColuna1));
        tamanhoColuna2 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 6 + tamanhoColuna1, 2));
        coluna2 = ByteArrayUtils.byteArrayToString(ByteArrayUtils.subArray(auxBytes, 8 + tamanhoColuna1, tamanhoColuna2));
        tamanhoColuna3 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 8 + tamanhoColuna1 + tamanhoColuna2, 2));
        coluna3 = ByteArrayUtils.byteArrayToString(ByteArrayUtils.subArray(auxBytes, 10 + tamanhoColuna1 + tamanhoColuna2, tamanhoColuna3));
        tamanhoColuna4 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 10 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3, 2));
        coluna4 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 12 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3, tamanhoColuna4));
        tamanhoColuna5 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 12 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3 + tamanhoColuna4, 2));
        coluna5 = ByteArrayUtils.byteArrayToString(ByteArrayUtils.subArray(auxBytes, 14 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3 + tamanhoColuna4, tamanhoColuna5));
        tamanhoColuna6 = ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(auxBytes, 14 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3 + tamanhoColuna4 + tamanhoColuna5, 2));
        coluna6 = ByteArrayUtils.byteArrayToString(ByteArrayUtils.subArray(auxBytes, 16 + tamanhoColuna1 + tamanhoColuna2 + tamanhoColuna3 + tamanhoColuna4 + tamanhoColuna5, tamanhoColuna6));
//        System.out.println("tananho da linha em bytes: "+auxBytes.length);
//        System.out.println("tamanhoColuna1: "+tamanhoColuna1);
//        System.out.println("Valor da coluna 1: "+coluna1);
//        System.out.println("tamanhoColuna2: "+tamanhoColuna2);
//        System.out.println("Valor da coluna 2: "+ coluna2);
//        System.out.println("tamanhoColuna3: "+tamanhoColuna3);
//        System.out.println("Valor da coluna 3: "+ coluna3);
//        System.out.println("tamanhoColuna4: "+tamanhoColuna4);
//        System.out.println("Valor da coluna 4: "+ coluna4);
//        System.out.println("tamanhoColuna5: "+tamanhoColuna5);
//        System.out.println("Valor da coluna 5: "+ coluna5);
//        System.out.println("tamanhoColuna6: "+tamanhoColuna6);
//        System.out.println("Valor da coluna 6: "+ coluna6);
//        System.out.println(coluna1 + " | " + coluna2 + " | " + coluna3 + " | " + coluna4 + " | " + coluna5 + " | " + coluna6);

//        try {
//            FileWriter fw = new FileWriter("tabela2teste",true);
//            fw.write(coluna1 + " | " + coluna2 + " | " + coluna3 + " | " + coluna4 + " | " + coluna5 + " | " + coluna6+"\n");
//        fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(coluna1 + " " + coluna2 + " " + coluna3 + " " + coluna4 + " " + coluna5 + " " + coluna6);

    }


    public static void inverter(int vetor[]) {
        int cont = vetor.length - 1;
        for (int i = 0; i < vetor.length / 2; i++) {
            int aux = vetor[i];
            vetor[i] = vetor[cont];
            vetor[cont] = aux;
        }
    }

    public static byte[] testeEscritaArquivoDados(String path, int i) {
        byte[] bytes = new byte[4096];
        try {
            RandomAccessFile raf = new RandomAccessFile(new File(path), "rw");
            raf.seek(4096 * i);
            raf.readFully(bytes);

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return bytes;
    }

    public static void representacaoGraficaBloco() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(new File("arquivo"), "rw");
        byte[] bytes = new byte[4096];
        try {

            raf.seek(0);
            raf.readFully(bytes);
            for (int i = 0; i < bytes.length; i++) {
                if (i % 120 == 0) {
                    System.out.println("");
                } else {
                    if (bytes[i] != 0) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void testeEscritaArquivocontrole() throws IOException {
        RandomAccessFile tabela = new RandomAccessFile(new File("arquivo"), "rw");
        byte[] id = new byte[1];
        byte[] tamanhoBloco = new byte[3];
        byte[] status = new byte[1];
        byte[] proximoBlocoLivre = new byte[4];
        byte[] tamanhoDescritor = new byte[2];
        byte[] descritor = new byte[96];

        tabela.seek(0);
        tabela.readFully(id);
        tabela.seek(1);
        tabela.readFully(tamanhoBloco);
        tabela.seek(4);
        tabela.readFully(status);
        tabela.seek(5);
        tabela.readFully(proximoBlocoLivre);
        tabela.seek(9);
        tabela.readFully(tamanhoDescritor);
        tabela.seek(11);
        tabela.readFully(descritor);
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToInt(id));
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToInt(tamanhoBloco));
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToInt(status));
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToInt(proximoBlocoLivre));
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToInt(tamanhoDescritor));
        System.out.println(Utilitarios.ByteArrayUtils.byteArrayToString(descritor));
    }

    public static void imprimirHeaderDados() {

        try {
            RandomAccessFile raf = new RandomAccessFile("tabela1", "rw");
            byte[] bytes = new byte[4096];
            raf.seek(4096);
            raf.readFully(bytes);

            System.out.println(ByteArrayUtils.byteArrayToInt(ByteArrayUtils.subArray(bytes, 5, 2)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
