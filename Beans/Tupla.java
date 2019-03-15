package Beans;

public class Tupla {
    private String[] coluna;
    private int indiceLinha;
    private int tamanhoCaracteres;
    private int tamanhoEmBytes;

    public Tupla(String linha) {

        this.coluna = linha.split("\\|");
        this.indiceLinha = 0;
        this.tamanhoCaracteres = 0;
        for (int i = 0; i < coluna.length; i++) {

            if (checaNumero(coluna[i])){
                this.tamanhoCaracteres =  this.tamanhoCaracteres + 4;
            }else {

                this.tamanhoCaracteres = this.tamanhoCaracteres + coluna[i].length();
            }
        }
        this.tamanhoEmBytes = 4 + tamanhoCaracteres + (2 * coluna.length);
    }
    private boolean checaNumero(String x){

        try{

            Integer.parseInt(x);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }

    public String[] getColuna() {
        return coluna;
    }

    public void setColuna(String[] coluna) {
        this.coluna = coluna;
    }

    public int getIndiceLinha() {
        return indiceLinha;
    }

    public void setIndiceLinha(int indiceLinha) {
        this.indiceLinha = indiceLinha;
    }

    public int getTamanhoCaracteres() {
        return tamanhoCaracteres;
    }

    public void setTamanhoCaracteres(int tamanhoCaracteres) {
        this.tamanhoCaracteres = tamanhoCaracteres;
    }

    public int getTamanhoEmBytes() {
        return tamanhoEmBytes;
    }

    public void setTamanhoEmBytes(int tamanhoEmBytes) {
        this.tamanhoEmBytes = tamanhoEmBytes;
    }
}
