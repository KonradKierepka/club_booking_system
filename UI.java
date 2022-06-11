package club_booking_system;
import javax.swing.*;

public class UI {
    public static void createUI() {
        JFrame main = new JFrame();
        
        JButton client = new JButton("Client");
        JButton admin = new JButton("Admin");

        JLabel wellcome1 = new JLabel("Wellcome!\n");
        JLabel wellcome2 = new JLabel("Choose your role.");

        Box title1 = Box.createHorizontalBox();
        Box title2 = Box.createHorizontalBox();
        title1.setBounds(100, 30, 100, 20);
        title1.add(wellcome1);
        title2.setBounds(85, 50, 100, 20);
        title2.add(wellcome2);

        client.setBounds(20, 80, 100, 40);
        admin.setBounds(140,80, 100, 40);

        main.add(title1);
        main.add(title2);

        main.add(client);
        main.add(admin);

        main.setSize(300, 300);
        main.setLayout(null);
