package Beans;

public class Linha {

    private String linha;
    private int caracteres;
    private String[] linhas;

    public Linha(String linha) {
        this.linha = linha;

        this.caracteres = linha.length();

        this.linhas = linha.split("\\|");
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public int getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(int caracteres) {
        this.caracteres = caracteres;
    }

    public String[] getLinhas() {
        return linhas;
    }

    public void setLinhas(String[] linhas) {
        this.linhas = linhas;
    }
}
