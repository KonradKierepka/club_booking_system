package club_booking_system;

import javax.swing.*;

import java.awt.event.*;  
import java.awt.*;

public class UI{
    public static void createUI() {
        JFrame main = new JFrame("Club Booking System - Choose role");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;
        int windows_height = height/4;
        int windows_width = width/4;
        
        JButton client = new JButton("Client");
        JButton admin = new JButton("Admin");

        JLabel wellcome1 = new JLabel("Wellcome!\n");
        JLabel wellcome2 = new JLabel("Choose your role.");

        wellcome1.setFont(new Font("Courier", Font.PLAIN, 15));
        wellcome2.setFont(new Font("Courier", Font.PLAIN, 15));
        client.setFont(new Font("Courier", Font.PLAIN, 15));
        admin.setFont(new Font("Courier", Font.PLAIN, 15));

        Box title1 = Box.createHorizontalBox();
        Box title2 = Box.createHorizontalBox();
        //title1.setBounds(width/3 - width/3 + 20, 30, 100, 20);
        title1.add(wellcome1);
        title1.setBounds(windows_width/2 - 45, windows_height/6, 71,20);
        title2.add(wellcome2);
        title2.setBounds(windows_width/2 - 67, windows_height/6 + 20, 115, 20);

        client.setBounds(windows_width/2 - 120, windows_height/3 + 20, 100, 40);
        admin.setBounds(windows_width/2 + 10, windows_height/3 + 20, 100, 40);

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.adminLoginUI();
            }
        });

        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.clientLoginUI(main.getBounds());
            }
        });

        main.add(title1);
        main.add(title2);

        main.add(client);
        main.add(admin);

        main.setBounds(width/2 - windows_width/2, height/2 - windows_height/2, width/4, height/4);
        main.setResizable(false);
        main.setLayout(null);
        main.setVisible(true);
    }

    protected static void clientLoginUI(Rectangle bounds) {
        JFrame ClientLogin = new JFrame("Client ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(new Color(125, 152, 176));

        JLabel instruction = new JLabel("Your ID:");
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setBounds((int)bounds.getWidth()/2 - 26, (int)bounds.getHeight()/6, 52,20);

        JLabel try_again = new JLabel("Bad ID - Try Again.");
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(new Color(250, 216, 183));
        try_again.setBounds((int)bounds.getWidth()/2 - 61, (int)bounds.getHeight()/3 + 60, 122,20);
        try_again.setVisible(false);
        
        JTextField id = new JTextField();
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                System.out.println(imput);
                if(Client.findInBase(imput) == true) {
                    System.out.println("true");
                }
                else {
                    id.setText("");
                    try_again.setVisible(true);
                }
            }
        });
        id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String imput = id.getText();
                    System.out.println(imput);
                    if(Client.findInBase(imput) == true) {
                        System.out.println("true");
                    }
                    else {
                        id.setText("");
                        try_again.setVisible(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // do nothing
            }
        });
        submit.setBounds((int)bounds.getWidth()/2 + (int)bounds.getWidth()/4 - 50, (int)bounds.getHeight()/3 + 20, 100, 30);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
    }

    protected static void adminLoginUI(){  
        System.out.println("admin");
    }
}
