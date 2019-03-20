package Buffers;

import Beans.BlocoDados;

public class LRU {


    private No primeiroNo;
    private No ultimoNo;


    public LRU(No primeiroNo, No ultimoNo) {
        this.primeiroNo = null;
        this.ultimoNo = null;
    }


    public void addBloco (BlocoDados bloco){

        No no = new No(bloco);
        if (this.primeiroNo ==null){

        } else {
            no.setProximoNo(this.primeiroNo);
            this.primeiroNo = no;
        }

    }

    public void removeBlock(BlocoDados blocoDados){


    }
}
