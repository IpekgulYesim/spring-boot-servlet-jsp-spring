package jrout.tutorial.springbootservletjsp.model;

import java.util.ArrayList;
import java.util.List;

public class Tarif {
    public int idTARIF;
    public String YEMEK_ADI;
    public String YEMEK_TURU;
    public String YAPILISI;

    List<Malzeme> malzemeler = new ArrayList<Malzeme>();

    public Tarif (String YEMEK_ADI, String YEMEK_TURU) {
        this.YEMEK_ADI =YEMEK_ADI;
        this.YEMEK_TURU = YEMEK_TURU;
    }

    public void MalzemeEkle(Malzeme yeniMalzeme)
    {
        this.malzemeler.add(yeniMalzeme);
    }
}
