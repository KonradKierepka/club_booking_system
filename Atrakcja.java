package Projekt;

public class Atrakcja {
    private String nazwa;
    private Double cena; 


    public Atrakcja(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public void edytujAtrakcje(String nazwa, Double cena) {
        if(nazwa != null) {
            this.nazwa = nazwa;
        }
        else if(cena != null) {
            this.cena = cena;
        }
        else {
            // TODO! throw new 
        }
    }

    public void usunAtrakcje() {
        this.nazwa = null;
        this.cena = null;
    }

    
}
