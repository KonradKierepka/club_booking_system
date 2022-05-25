package Projekt;

public class Pracownik implements Osoba{

    private String imie;
    private String nazwisko;
    private String adres;
    private String nr_dowodu;
    private Integer nr_telefonu;


    public Pracownik(String imie, String nazwisko, String adres, String nr_dowodu, Integer nr_telefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.nr_dowodu = nr_dowodu;
        this.nr_telefonu = nr_telefonu;
    }
}
 