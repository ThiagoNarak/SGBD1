package Beans;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class RowId {
    private int containerId;
    private int blocoId;
    private int linhaId;
    private String parametro;

    public RowId(int containerId, int blocoId, int linhaId, String parametro) {
        this.containerId = containerId;
        this.blocoId = blocoId;
        this.linhaId = linhaId;
        this.parametro = parametro;
    }




}
