package Projekt;

public class main {
    public static void main(String[] args) {
        Admin a1 = new Admin("Konrad", "Kierepka", "xxx", "abdc231", 652432523, "test123");
        Pracownik a2 = new Pracownik("Konrad", "Tako", "yyy", "asadsa231", 652322323);
        Klient k1 = new Klient("Ewa", "Kierepka", "ysdssdf22", 543223653);
        System.out.println(a1.getPassword());
        //a2.deleteEmployee();
        //a1.deleteEmployee();
    }
}
