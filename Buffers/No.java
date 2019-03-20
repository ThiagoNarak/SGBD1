package Buffers;

import Beans.BlocoDados;

public class No {


    private No proximoNo;
    private BlocoDados blocoDados;

    public No(BlocoDados blocoDados) {
        this.blocoDados = blocoDados;
        this.proximoNo = null;

    }

    public No getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(No proximoNo) {
        this.proximoNo = proximoNo;
    }

    public BlocoDados getBlocoDados() {
        return blocoDados;
    }

    public void setBlocoDados(BlocoDados blocoDados) {
        this.blocoDados = blocoDados;
    }
}
