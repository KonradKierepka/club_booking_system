package club_booking_system;

import javax.swing.*;

import java.awt.event.*;  
import java.awt.*;

public class UI{
    public static void createUI() {
        JFrame main = new JFrame("Club Booking System - Choose role");
        main.getContentPane().setBackground(new Color(125, 152, 176));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;
        int windows_height = height/4;
        int windows_width = width/4;
        
        JButton client = new JButton("Client");
        JButton admin = new JButton("Admin");

        JLabel wellcome1 = new JLabel("Wellcome!", SwingConstants.CENTER);
        JLabel wellcome2 = new JLabel("Choose your role.", SwingConstants.CENTER);

        wellcome1.setFont(new Font("Courier", Font.PLAIN, 15));
        wellcome2.setFont(new Font("Courier", Font.PLAIN, 15));
        client.setFont(new Font("Courier", Font.PLAIN, 15));
        admin.setFont(new Font("Courier", Font.PLAIN, 15));

        System.out.println(windows_width);

        wellcome1.setBounds(0, windows_height/6, windows_width,20);
        wellcome2.setBounds(0, windows_height/6 + 20, windows_width, 20);

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

        main.add(wellcome1);
        main.add(wellcome2);

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

        JLabel instruction = new JLabel("Your ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(new Color(250, 216, 183));
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth(),20);
        try_again.setVisible(false);
        
        JPasswordField id = new JPasswordField();
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                System.out.println(imput);
                if(Client.findInBase(imput) != null) {
                    ClientLogin.setVisible(false);
                    UI.clientUI(imput, bounds, Client.findInBase(imput));
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
                    if(Client.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.clientUI(imput, bounds, Client.findInBase(imput));
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

    protected static void clientUI(String client_id, Rectangle bounds, Client c) {
        JFrame ClientUI = new JFrame("ClientUI");
        ClientUI.setBounds(bounds);
        ClientUI.setResizable(false);
        ClientUI.setLayout(null);
        ClientUI.setVisible(true);
        ClientUI.getContentPane().setBackground(new Color(125, 152, 176));

        JButton BookedParties = new JButton("Booked Parties");
        JButton BookNewParty = new JButton("Book New Party");

        BookedParties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientUI.setVisible(false);
                UI.BookedPartiesUI(bounds, c);
                System.out.println(1);
            }
        });

        BookNewParty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO! new UI
                System.out.println(2);
            }
        });

        JLabel Description = new JLabel("Welcome " + c.getName(), SwingConstants.CENTER);

        Description.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);
        Description.setFont(new Font("Courier", Font.PLAIN, 15));

        BookedParties.setFont(new Font("Courier", Font.PLAIN, 15));
        BookNewParty.setFont(new Font("Courier", Font.PLAIN, 15));

        BookedParties.setBounds((int)bounds.getWidth()/2 - 160, (int)bounds.getHeight()/3 + 20, 150, 40);
        BookNewParty.setBounds((int)bounds.getWidth()/2 + 10, (int)bounds.getHeight()/3 + 20, 150, 40);

        ClientUI.add(Description);
        ClientUI.add(BookNewParty);
        ClientUI.add(BookedParties);
    }

    protected static void adminLoginUI(){  
        System.out.println("admin");
    }

    protected static void BookedPartiesUI(Rectangle bounds, Client c) {
        JFrame BookedPartiesUI = new JFrame("" + c.getName() + ": Your booked parties");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;

        BookedPartiesUI.setBounds(width/2 - (int)bounds.getWidth(), height/2 - (int)bounds.getHeight(), (int)bounds.getWidth()*2, (int)bounds.getHeight()*2);
        BookedPartiesUI.setResizable(false);
        BookedPartiesUI.setLayout(null);
        BookedPartiesUI.setVisible(true);
        BookedPartiesUI.getContentPane().setBackground(new Color(125, 152, 176));

        String temp_parties = Party.findParty(c.getId_number());
        String[] temp1 = temp_parties.split(" @");
        String[] temp2;
        String[] names = new String[temp1.length - 1];

        for(int i = 0; i < temp1.length - 1; i++){
            temp2 = temp1[i].split(" ");
            names[i] = i+1 + ") " + temp2[2] + " " + temp2[5].replace("_", " ").toUpperCase() + " cost: " + temp2[8] + " PLN";
        }
        JList<String> Parties = new JList<String>(names);

        Parties.setFont(new Font("Courier", Font.PLAIN, 15));
        Parties.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4, (int)bounds.getHeight()/2 - (int)bounds.getHeight()/3, (int)bounds.getWidth(), (int)bounds.getHeight());

        BookedPartiesUI.add(Parties);
    }
}
