package jrout.tutorial.springbootservletjsp.model;

public class Kullanici {
    public int idKULLANICI;
    public String ADI;
    public String SOYADI;
    public String EMAIL;
    public String KULLANICI_ADI;
    public String Password;

    public Kullanici()
    {}
    
    public String getKULLANICI_ADI() {
        return KULLANICI_ADI;
    }

    public Kullanici(int idKULLANICI , String ADI){
        this.idKULLANICI = idKULLANICI;
        this.ADI = ADI;
    }
    public Kullanici(int idKULLANICI , String ADI, String SOYADI){
        this.idKULLANICI = idKULLANICI;
        this.ADI = ADI;
        this.SOYADI = SOYADI;
    }

    public String getADI() {
        return ADI;
    }

    public void setADI(String ADI) {
        this.ADI = ADI;
    }

    public String getSOYADI() {
        return SOYADI;
    }

    public void setSOYADI(String SOYADI) {
        this.SOYADI = SOYADI;
    }

    public int getidKULLANICI() {
        return idKULLANICI;
    }

    public String getPassword() {
        return Password;
    }

    public void setidKULLANICI(int idKULLANICI) {
        this.idKULLANICI = idKULLANICI;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setKULLANICI_ADI(String KULLANICI_ADI) {
        this.KULLANICI_ADI = KULLANICI_ADI;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}

