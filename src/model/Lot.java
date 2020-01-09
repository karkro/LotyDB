package model;

import java.util.Date;

public class Lot {

    private int id;
    private String skad;
    private String dokad;
    private Date dataWylotu;
    private int cena;
    private int iloscMiejscBiznesowych;
    private int iloscMiejscEkonomicznych;

    public Lot(int id, String skad, String dokad, Date dataWylotu, int cena,
               int iloscMiejscBiznesowych, int iloscMiejscEkonomicznych) {
        super();
        this.id = id;
        this.skad = skad;
        this.dokad = dokad;
        this.dataWylotu = dataWylotu;
        this.cena = cena;
        this.iloscMiejscBiznesowych = iloscMiejscBiznesowych;
        this.iloscMiejscEkonomicznych = iloscMiejscEkonomicznych;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", skad='" + skad + '\'' +
                ", dokad='" + dokad + '\'' +
                ", dataWylotu=" + dataWylotu +
                ", cena=" + cena +
                ", iloscMiejscBiznesowych=" + iloscMiejscBiznesowych +
                ", iloscMiejscEkonomicznych=" + iloscMiejscEkonomicznych +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkad() {
        return skad;
    }

    public void setSkad(String skad) {
        this.skad = skad;
    }

    public String getDokad() {
        return dokad;
    }

    public void setDokad(String dokad) {
        this.dokad = dokad;
    }

    public Date getDataWylotu() {
        return dataWylotu;
    }

    public void setDataWylotu(Date dataWylotu) {
        this.dataWylotu = dataWylotu;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getIloscMiejscBiznesowych() {
        return iloscMiejscBiznesowych;
    }

    public void setIloscMiejscBiznesowych(int iloscMiejscBiznesowych) {
        this.iloscMiejscBiznesowych = iloscMiejscBiznesowych;
    }

    public int getIloscMiejscEkonomicznych() {
        return iloscMiejscEkonomicznych;
    }

    public void setIloscMiejscEkonomicznych(int iloscMiejscEkonomicznych) {
        this.iloscMiejscEkonomicznych = iloscMiejscEkonomicznych;
    }

}
