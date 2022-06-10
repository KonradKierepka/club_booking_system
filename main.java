package Projekt;

public class main {
    public static void main(String[] args) {
        Admin a1 = new Admin("Konrad", "Kierepka", "xxx", "abdc231", 652432523, "test123");
        Employee a2 = new Employee("Konrad", "Tako", "yyy", "asadsa231", 652322323);
        Client k1 = new Client("Ewa", "Kierepka", "ysdssdf22", 543223653);
        Attraction at1 = new Attraction("Pilkarzyki", 80.00);
        at1.editAttraction("Pilkarzyki", 50.00);
        System.out.println(a1.getPassword());
        //a2.deleteEmployee();
        //a1.deleteEmployee();
    }
}
