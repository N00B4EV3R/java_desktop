package model.Entity;

public class Jucarie extends Entity{
    private String denumire, tara;
    private int pret, varsta, cantitate, pr_vandute;
    private Long ID;

    public Jucarie(){}

    public Jucarie(Long ID, String denumire, String tara, int pret, int varsta, int cantitate, int pr_vandute) {
        this.ID = ID;
        this.denumire = denumire;
        this.tara = tara;
        this.pret = pret;
        this.varsta = varsta;
        this.cantitate = cantitate;
        this.pr_vandute = pr_vandute;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPr_vandute() {
        return pr_vandute;
    }

    public void setPr_vandute(int pr_vandute) {
        this.pr_vandute = pr_vandute;
    }

    @Override
    public String toString() {
        return getDenumire()+": "+getPret()+"lei, varsta minima e de "+getVarsta()+" ani, pe stock sunt "+getCantitate()+" produse, "+getPr_vandute()+" produse au fost vandute, sunt fabricate in "+getTara();
    }
}
