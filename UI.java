package club_booking_system;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.*;

import club_booking_system.Party.TypeOfMusic;
import club_booking_system.Party.TypeOfParty;
import club_booking_system.Party.Menu;

import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;

public class UI implements UIInterface{
    // public static final Color B1 = new Color(45, 52, 54);
    // public static final Color B2 = new Color(99, 110, 114);
    // public static final Color C1 = new Color(85, 239, 196);
    // public static final Color C2 = new Color(0, 184, 148);
    // public static final Color E = new Color(255, 118, 117);

    //TODO! UI

    public static void createUI() {
        JFrame main = new JFrame("Club Booking System - Choose role");
        main.getContentPane().setBackground(B1);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;
        int windows_height = height/4;
        int windows_width = width/4;
        
        JButton client = new JButton("CLIENT");
        JButton employee = new JButton("EMPLOYEE");
        client.setBackground(Color.BLACK);
        client.setOpaque(false);
        client.setForeground(C2);
        client.setFocusable(false);
        client.setBorder(BorderFactory.createLineBorder(C2));
        employee.setBackground(Color.BLACK);
        employee.setOpaque(false);
        employee.setForeground(C2);
        employee.setFocusable(false);
        employee.setBorder(BorderFactory.createLineBorder(C2));

        JLabel wellcome1 = new JLabel("Wellcome!", SwingConstants.CENTER);
        wellcome1.setForeground(C1);
        wellcome1.setOpaque(false);
        JLabel wellcome2 = new JLabel("Choose your role.", SwingConstants.CENTER);
        wellcome2.setForeground(C1);
        wellcome2.setOpaque(false);

        wellcome1.setFont(new Font("Courier", Font.PLAIN, 15));
        wellcome2.setFont(new Font("Courier", Font.PLAIN, 15));
        client.setFont(new Font("Courier", Font.PLAIN, 15));
        employee.setFont(new Font("Courier", Font.PLAIN, 15));

        //System.out.println(windows_width);

        wellcome1.setBounds(0, windows_height/6, windows_width,20);
        wellcome2.setBounds(0, windows_height/6 + 20, windows_width, 20);

        client.setBounds(windows_width/2 - 136, windows_height/3 + 20, 126, 40);
        employee.setBounds(windows_width/2 + 10, windows_height/3 + 20, 126, 40);

        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.employeeRoleUI();
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
        main.add(employee);

        main.setBounds(width/2 - windows_width/2, height/2 - windows_height/2, width/4, height/4);
        main.setResizable(false);
        main.setLayout(null);
        main.setVisible(true);
    }

     //TODO! ClientLoginUI

    private static void clientLoginUI(Rectangle bounds) {
        JFrame ClientLogin = new JFrame("Client ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(B1);

        JLabel instruction = new JLabel("Your ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setForeground(C1);
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth(),20);
        try_again.setVisible(false);

        JButton Back = new JButton("<-");

        Back.setBackground(B1);
        Back.setOpaque(false);
        Back.setForeground(C2);
        Back.setFocusable(false);
        Back.setBorder(BorderFactory.createLineBorder(C2));
        Back.setFont(new Font("Courier", Font.PLAIN, 15));
        Back.setBounds(0, 0, 40, 40);
        Back.setVisible(true);
        
        JPasswordField id = new JPasswordField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                //System.out.println(imput);
                if(Client.findInBase(imput) != null) {
                    ClientLogin.setVisible(false);
                    UI.ClientBookedPartiesUI(imput, bounds, Client.findInBase(imput));
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
                    //System.out.println(imput);
                    if(Client.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.ClientBookedPartiesUI(imput, bounds, Client.findInBase(imput));
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
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientLogin.setVisible(false);
                UI.createUI();
            }
        });

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
        ClientLogin.add(Back);
    }

    //TODO! ClientBookedPartiesUI

    protected static void ClientBookedPartiesUI(String client_id, Rectangle bounds, Client c) {
        JFrame BookedPartiesUI = new JFrame("" + c.getName() + ": Your booked parties");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;

        JButton Back = new JButton("<-");

        Back.setBackground(B1);
        Back.setOpaque(false);
        Back.setForeground(C2);
        Back.setFocusable(false);
        Back.setBorder(BorderFactory.createLineBorder(C2));
        Back.setFont(new Font("Courier", Font.PLAIN, 15));
        Back.setBounds(0, 0, 40, 40);
        Back.setVisible(true);

        BookedPartiesUI.setBounds(width/2 - (int)bounds.getWidth(), height/2 - (int)bounds.getHeight(), (int)bounds.getWidth()*2, (int)bounds.getHeight()*2);
        BookedPartiesUI.setResizable(false);
        BookedPartiesUI.setLayout(null);
        BookedPartiesUI.setVisible(true);
        BookedPartiesUI.getContentPane().setBackground(B1);

        JTextField party_date_year = new JTextField();
        JTextField party_date_month = new JTextField();
        JTextField party_date_day = new JTextField();

        party_date_year.setBackground(B2);
        party_date_year.setForeground(C2);
        party_date_year.setHorizontalAlignment(JTextField.CENTER);
        party_date_year.setBorder(BorderFactory.createLineBorder(C2));
        party_date_year.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_year.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_year.setEditable(false);
        party_date_year.setText("YEAR");

        party_date_month.setBackground(B2);
        party_date_month.setForeground(C2);
        party_date_month.setHorizontalAlignment(JTextField.CENTER);
        party_date_month.setBorder(BorderFactory.createLineBorder(C2));
        party_date_month.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_month.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_month.setEditable(false);
        party_date_month.setText("MONTH");


        party_date_day.setBackground(B2);
        party_date_day.setForeground(C2);
        party_date_day.setHorizontalAlignment(JTextField.CENTER);
        party_date_day.setBorder(BorderFactory.createLineBorder(C2));
        party_date_day.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_day.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 150, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_day.setEditable(false);
        party_date_day.setText("DAY");

        JTextField number_of_pepole = new JTextField();

        number_of_pepole.setBackground(B2);
        number_of_pepole.setForeground(C2);
        number_of_pepole.setHorizontalAlignment(JTextField.CENTER);
        number_of_pepole.setBorder(BorderFactory.createLineBorder(C2));
        number_of_pepole.setFont(new Font("Courier", Font.PLAIN, 15));
        number_of_pepole.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 140, 50, 30);
        number_of_pepole.setEditable(false);
        number_of_pepole.setText("Number of pepole: ");

        JTextField type_of_party = new JTextField();

        type_of_party.setBackground(B2);
        type_of_party.setForeground(C2);
        type_of_party.setHorizontalAlignment(JTextField.CENTER);
        type_of_party.setBorder(BorderFactory.createLineBorder(C2));
        type_of_party.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_party.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 140, 150, 30);
        type_of_party.setEditable(false);
        type_of_party.setText("Type of party: ");

        JTextField type_of_menu = new JTextField();

        type_of_menu.setBackground(B2);
        type_of_menu.setForeground(C2);
        type_of_menu.setHorizontalAlignment(JTextField.CENTER);
        type_of_menu.setBorder(BorderFactory.createLineBorder(C2));
        type_of_menu.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_menu.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 180, 150, 30);
        type_of_menu.setEditable(false);
        type_of_menu.setText("Menu: ");

        JTextField cost = new JTextField();

        cost.setBackground(B2);
        cost.setForeground(C2);
        cost.setHorizontalAlignment(JTextField.CENTER);
        cost.setBorder(BorderFactory.createLineBorder(C2));
        cost.setFont(new Font("Courier", Font.PLAIN, 15));
        cost.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 180, 50, 30);
        cost.setEditable(false);
        cost.setText("Cost: ");

        JTextField attractions = new JTextField();

        attractions.setBackground(B2);
        attractions.setForeground(C2);
        attractions.setHorizontalAlignment(JTextField.CENTER);
        attractions.setBorder(BorderFactory.createLineBorder(C2));
        attractions.setFont(new Font("Courier", Font.PLAIN, 15));
        attractions.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 260, 240, 40);
        attractions.setEditable(false);
        attractions.setText("Attractions: ");

        JTextField music = new JTextField();

        music.setBackground(B2);
        music.setForeground(C2);
        music.setHorizontalAlignment(JTextField.CENTER);
        music.setBorder(BorderFactory.createLineBorder(C2));
        music.setFont(new Font("Courier", Font.PLAIN, 15));
        music.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 220, 240, 30);
        music.setEditable(false);
        music.setText("Music: ");

        String temp_parties = Party.findParties(c.getId_number());
        String[] temp1 = temp_parties.split(" @");
        String[] temp2;
        String[] names = new String[temp1.length - 1];

        for(int i = 0; i < temp1.length - 1; i++){
            temp2 = temp1[i].split(" ");
            names[i] = i+1 + ") " + temp2[2] + " " + temp2[5].replace("_", " ").toUpperCase() + " cost: " + temp2[8] + " PLN";
        }
        JList<String> Parties = new JList<String>(names);
        Parties.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String[] temp3 = Parties.getSelectedValue().split(" ");
                String choosen = Party.findParty(c.getId_number(), LocalDate.parse(temp3[1]));
                System.out.println(choosen);
                String[] temp4 = choosen.split(" ");
                Party edited = new Party(c.getId_number(), LocalDate.parse(temp4[2]), Integer.parseInt(temp4[3]), TypeOfMusic.valueOf(temp4[4]), TypeOfParty.valueOf(temp4[5]), Menu.valueOf(temp4[6]), temp4[7], Double.parseDouble(temp4[8]));
                String[] temp_date = temp4[2].split("-");
                party_date_year.setText(temp_date[0]);
                party_date_month.setText(temp_date[1]);
                party_date_day.setText(temp_date[2]);
                number_of_pepole.setText(temp4[3]);
                music.setText(temp4[4]);
                type_of_party.setText(temp4[5]);
                type_of_menu.setText(temp4[6]);
                attractions.setText(temp4[7]);
                cost.setText(temp4[8]);
            }
        });

        Parties.setFont(new Font("Courier", Font.PLAIN, 15));
        Parties.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4, (int)bounds.getHeight()/2 - (int)bounds.getHeight()/3, (int)bounds.getWidth(), (int)bounds.getHeight()*3/2);
        Parties.setBackground(B2);
        Parties.setForeground(C1);
        Parties.setBorder(BorderFactory.createLineBorder(C2));

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookedPartiesUI.setVisible(false);
                UI.clientLoginUI(bounds);
            }
        });

        BookedPartiesUI.add(Parties);
        BookedPartiesUI.add(party_date_year);
        BookedPartiesUI.add(party_date_month);
        BookedPartiesUI.add(party_date_day);
        BookedPartiesUI.add(number_of_pepole);
        BookedPartiesUI.add(type_of_party);
        BookedPartiesUI.add(type_of_menu);
        BookedPartiesUI.add(cost);
        BookedPartiesUI.add(music);
        BookedPartiesUI.add(attractions);
        BookedPartiesUI.add(Back);
    }

    //TODO! EmployeeRoleUI

    private static void employeeRoleUI() {

        JFrame main = new JFrame("Club Booking System - Choose role");
        main.getContentPane().setBackground(B1);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;
        int windows_height = height/4;
        int windows_width = width/4;

        JButton Back = new JButton("<-");

        Back.setBackground(B1);
        Back.setOpaque(false);
        Back.setForeground(C2);
        Back.setFocusable(false);
        Back.setBorder(BorderFactory.createLineBorder(C2));
        Back.setFont(new Font("Courier", Font.PLAIN, 15));
        Back.setBounds(0, 0, 40, 40);
        Back.setVisible(true);
        
        JButton normal = new JButton("NORMAL");
        JButton admin = new JButton("ADMIN");
        normal.setBackground(Color.BLACK);
        normal.setOpaque(false);
        normal.setForeground(C2);
        normal.setFocusable(false);
        normal.setBorder(BorderFactory.createLineBorder(C2));
        admin.setBackground(Color.BLACK);
        admin.setOpaque(false);
        admin.setForeground(C2);
        admin.setFocusable(false);
        admin.setBorder(BorderFactory.createLineBorder(C2));

        JLabel wellcome2 = new JLabel("Choose your role.", SwingConstants.CENTER);
        wellcome2.setForeground(C1);
        wellcome2.setOpaque(false);

        wellcome2.setFont(new Font("Courier", Font.PLAIN, 15));
        normal.setFont(new Font("Courier", Font.PLAIN, 15));
        admin.setFont(new Font("Courier", Font.PLAIN, 15));

        //System.out.println(windows_width);

        wellcome2.setBounds(0, windows_height/6 + 20, windows_width, 20);

        normal.setBounds(windows_width/2 - 136, windows_height/3 + 20, 126, 40);
        admin.setBounds(windows_width/2 + 10, windows_height/3 + 20, 126, 40);

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.adminLoginUI(main.getBounds());
            }
        });

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.employeeLoginUI(main.getBounds());
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                UI.createUI();
            }
        });

        main.add(wellcome2);

        main.add(normal);
        main.add(admin);
        main.add(Back);

        main.setBounds(width/2 - windows_width/2, height/2 - windows_height/2, width/4, height/4);
        main.setResizable(false);
        main.setLayout(null);
        main.setVisible(true);
    }

    //TODO! EmployeeLoginUI

    protected static void employeeLoginUI(Rectangle bounds) {
        JFrame ClientLogin = new JFrame("Employee ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
        Back.setBackground(B1);
        Back.setOpaque(false);
        Back.setForeground(C2);
        Back.setFocusable(false);
        Back.setBorder(BorderFactory.createLineBorder(C2));
        Back.setFont(new Font("Courier", Font.PLAIN, 15));
        Back.setBounds(0, 0, 40, 40);
        Back.setVisible(true);

        JLabel instruction = new JLabel("Your ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setForeground(C1);
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth(),20);
        try_again.setVisible(false);
        
        JPasswordField id = new JPasswordField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                //System.out.println(imput);
                if(Employee.findInBase(imput) != null) {
                    ClientLogin.setVisible(false);
                    UI.EmployeeUI(imput, bounds, Employee.findInBase(imput));
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
                    //System.out.println(imput);
                    if(Employee.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.EmployeeUI(imput, bounds, Employee.findInBase(imput));
                        System.out.println("ok");
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
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientLogin.setVisible(false);
                UI.employeeRoleUI();
            }
        });

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
        ClientLogin.add(Back);
    }

    //TODO! EmployeeUI

    protected static void EmployeeUI(String employee_id, Rectangle bounds, Employee emp) {
            JFrame EmployeeUI = new JFrame("EmployeeUI");
            EmployeeUI.setBounds(bounds);
            EmployeeUI.setResizable(false);
            EmployeeUI.setLayout(null);
            EmployeeUI.setVisible(true);
            EmployeeUI.getContentPane().setBackground(B1);

            JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EmployeeUI.setVisible(false);
                    UI.employeeLoginUI(bounds);
                }
            });
    
            JButton BookNewParty = new JButton("Book New Party");
            JButton EditParties = new JButton("Edit Party");
    
            BookNewParty.setBackground(Color.BLACK);
            BookNewParty.setOpaque(false);
            BookNewParty.setForeground(C2);
            BookNewParty.setFocusable(false);
            BookNewParty.setBorder(BorderFactory.createLineBorder(C2));
    
            EditParties.setBackground(Color.BLACK);
            EditParties.setOpaque(false);
            EditParties.setForeground(C2);
            EditParties.setFocusable(false);
            EditParties.setBorder(BorderFactory.createLineBorder(C2));
    
    
            BookNewParty.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EmployeeUI.setVisible(false);
                    UI.employeeEditPartyUI(emp, employee_id, bounds, 1);
                    //System.out.println(2);
                }
            });
    
            EditParties.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EmployeeUI.setVisible(false);
                    UI.employeeEditPartyUI(emp, employee_id, bounds, 2);
                    //System.out.println(2);
                }
            });
    
            JLabel Description = new JLabel("Welcome " + emp.getName(), SwingConstants.CENTER);
    
            Description.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);
            Description.setForeground(C1);
            Description.setFont(new Font("Courier", Font.PLAIN, 15));
    
            BookNewParty.setFont(new Font("Courier", Font.PLAIN, 15));
            EditParties.setFont(new Font("Courier", Font.PLAIN, 15));
    
            BookNewParty.setBounds((int)bounds.getWidth()/2 + 10, (int)bounds.getHeight()/3 + 20, 150, 40);
            EditParties.setBounds((int)bounds.getWidth()/2 - 160, (int)bounds.getHeight()/3 + 20, 150, 40);
    
            EmployeeUI.add(Description);
            EmployeeUI.add(BookNewParty);
            EmployeeUI.add(Back);
            EmployeeUI.add(EditParties);
    }

    //TODO! AdminLoginUI

    private static void adminLoginUI(Rectangle bounds){  
        JFrame ClientLogin = new JFrame("Admin ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientLogin.setVisible(false);
                    UI.employeeRoleUI();
                }
            });
    

        JLabel instruction = new JLabel("Your ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setForeground(C1);
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel id_d = new JLabel("ID: ", SwingConstants.CENTER);
        id_d.setFont(new Font("Courier", Font.PLAIN, 15));
        id_d.setForeground(C1);
        id_d.setBounds((int)bounds.getWidth()/2 - (2*(int)bounds.getWidth()/3), (int)bounds.getHeight()/3 + 25, (int)bounds.getWidth()/2, 20);

        JLabel pass_d = new JLabel("Pass: ", SwingConstants.CENTER);
        pass_d.setFont(new Font("Courier", Font.PLAIN, 15));
        pass_d.setForeground(C1);
        pass_d.setBounds((int)bounds.getWidth()/2 - (2*(int)bounds.getWidth()/3), (int)bounds.getHeight()/3 + 65, (int)bounds.getWidth()/2, 20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 85, (int)bounds.getWidth(),60);
        try_again.setVisible(false);
        
        JTextField id = new JTextField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);
        
        JPasswordField password = new JPasswordField();
        password.setBackground(B2);
        password.setForeground(C2);
        password.setBorder(BorderFactory.createLineBorder(C2));
        password.setFont(new Font("Courier", Font.PLAIN, 15));
        password.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth()/2, 30);
        

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput1 = id.getText();
                String imput2 = password.getText();
                //System.out.println(imput);
                if(Admin.findInBase(imput1, imput2) != null) {
                    ClientLogin.setVisible(false);
                    UI.adminUI(imput1, bounds, Admin.findInBase(imput1, imput2));
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
                    //System.out.println(imput);
                    if(Client.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.clientLoginUI(bounds);
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
        submit.setBounds((int)bounds.getWidth()/2 + (int)bounds.getWidth()/4 - 50, (int)bounds.getHeight()/3 + 40, 100, 30);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
        ClientLogin.add(password);
        ClientLogin.add(id_d);
        ClientLogin.add(pass_d);
        ClientLogin.add(Back);
    }
    
     //TODO! AdminUI

    private static void adminUI(String admin_id, Rectangle bounds, Admin a) {
        JFrame AdminUI = new JFrame("AdminUI");
        AdminUI.setBounds(bounds);
        AdminUI.setResizable(false);
        AdminUI.setLayout(null);
        AdminUI.setVisible(true);
        AdminUI.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdminUI.setVisible(false);
                    UI.adminLoginUI(bounds);
                }
            });

        JButton AddEmpolyee = new JButton("Add New Empolyee");
        JButton BookNewParty = new JButton("Book New Party");
        JButton EditParties = new JButton("Edit Party");

        AddEmpolyee.setBackground(Color.BLACK);
        AddEmpolyee.setOpaque(false);
        AddEmpolyee.setForeground(C2);
        AddEmpolyee.setFocusable(false);
        AddEmpolyee.setBorder(BorderFactory.createLineBorder(C2));

        BookNewParty.setBackground(Color.BLACK);
        BookNewParty.setOpaque(false);
        BookNewParty.setForeground(C2);
        BookNewParty.setFocusable(false);
        BookNewParty.setBorder(BorderFactory.createLineBorder(C2));

        EditParties.setBackground(Color.BLACK);
        EditParties.setOpaque(false);
        EditParties.setForeground(C2);
        EditParties.setFocusable(false);
        EditParties.setBorder(BorderFactory.createLineBorder(C2));

        AddEmpolyee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.setVisible(false);
                UI.AddEmployeeUI(admin_id, bounds, a);
                //System.out.println(1);
            }
        });

        BookNewParty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.setVisible(false);
                UI.adminEditPartyUI(a, admin_id, bounds, 1);
                //System.out.println(2);
            }
        });

        EditParties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUI.setVisible(false);
                UI.adminEditPartyUI(a, admin_id, bounds, 2);
                //System.out.println(2);
            }
        });

        JLabel Description = new JLabel("Welcome " + a.getName(), SwingConstants.CENTER);

        Description.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);
        Description.setForeground(C1);
        Description.setFont(new Font("Courier", Font.PLAIN, 15));

        AddEmpolyee.setFont(new Font("Courier", Font.PLAIN, 15));
        BookNewParty.setFont(new Font("Courier", Font.PLAIN, 15));
        EditParties.setFont(new Font("Courier", Font.PLAIN, 15));

        AddEmpolyee.setBounds((int)bounds.getWidth()/2 - 160, (int)bounds.getHeight()/3 + 20, 150, 40);
        BookNewParty.setBounds((int)bounds.getWidth()/2 + 10, (int)bounds.getHeight()/3 + 20, 150, 40);
        EditParties.setBounds((int)bounds.getWidth()/2 - 75, (int)bounds.getHeight()/3 + 70, 150, 40);

        AdminUI.add(Description);
        AdminUI.add(BookNewParty);
        AdminUI.add(AddEmpolyee);
        AdminUI.add(EditParties);
        AdminUI.add(Back);
    }

     //TODO! BookPartyUI

    protected static void BookPartyUI(String admin_id, String client_id, Rectangle bounds, Client c, Admin a) {
        JFrame BookPartyUI = new JFrame("Add Party:");
        BookPartyUI.setBounds(bounds);
        BookPartyUI.setResizable(false);
        BookPartyUI.setLayout(null);
        BookPartyUI.setVisible(true);
        BookPartyUI.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BookPartyUI.setVisible(false);
                    UI.adminUI(admin_id, bounds, a);
                }
            });

        JTextField party_date_year = new JTextField();
        JTextField party_date_month = new JTextField();
        JTextField party_date_day = new JTextField();

        party_date_year.setBackground(B2);
        party_date_year.setForeground(C2);
        party_date_year.setHorizontalAlignment(JTextField.CENTER);
        party_date_year.setBorder(BorderFactory.createLineBorder(C2));
        party_date_year.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_year.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10, 60, 30);
        party_date_year.setEditable(false);
        party_date_year.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_year.getText();
                party_date_year.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_year.setText("YEAR");
        party_date_year.setVisible(false);

        ArrayList<String> years = new ArrayList<String>();
        years.add(String.valueOf(LocalDate.now().getYear()));
        years.add(String.valueOf(LocalDate.now().getYear() + 1));
        years.add(String.valueOf(LocalDate.now().getYear() + 2));
        years.add(String.valueOf(LocalDate.now().getYear() + 3));
        years.add(String.valueOf(LocalDate.now().getYear() + 4));
        years.add(String.valueOf(LocalDate.now().getYear() + 5));
        years.add(String.valueOf(LocalDate.now().getYear() + 6));
        years.add(String.valueOf(LocalDate.now().getYear() + 7));
        years.add(String.valueOf(LocalDate.now().getYear() + 8));
        years.add(String.valueOf(LocalDate.now().getYear() + 9));

        JComboBox year_combobox = new JComboBox<String>();
        year_combobox.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));

        year_combobox.setBackground(B1);
        year_combobox.setForeground(C2);
        year_combobox.setBorder(BorderFactory.createLineBorder(C2));
        year_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        year_combobox.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10, 60, 30);
        ItemListener year = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_year.setText((String) e.getItem());
            }
        };
        year_combobox.addItemListener(year);
        year_combobox.setVisible(true);

        party_date_month.setBackground(B2);
        party_date_month.setForeground(C2);
        party_date_month.setHorizontalAlignment(JTextField.CENTER);
        party_date_month.setBorder(BorderFactory.createLineBorder(C2));
        party_date_month.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_month.setBounds((int)bounds.getWidth()/10 + 70, (int)bounds.getHeight()/10, 60, 30);
        party_date_month.setEditable(false);
        party_date_month.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_month.getText();
                party_date_month.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_month.setText("MONTH");
        party_date_month.setVisible(false);

        ArrayList<String> months = new ArrayList<String>();
        for(int i = 1; i<13; i++) {
            if(i<10) {
                months.add("0" + String.valueOf(i));
            }
            else {
                months.add(String.valueOf(i));
            }
        }

        JComboBox months_combobox = new JComboBox<String>();
        months_combobox.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));

        months_combobox.setBackground(B1);
        months_combobox.setForeground(C2);
        months_combobox.setBorder(BorderFactory.createLineBorder(C2));
        months_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        months_combobox.setBounds((int)bounds.getWidth()/10 + 70, (int)bounds.getHeight()/10, 60, 30);
        ItemListener month = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_month.setText((String) e.getItem());
            }
        };
        months_combobox.addItemListener(month);
        months_combobox.setVisible(true);

        party_date_day.setBackground(B2);
        party_date_day.setForeground(C2);
        party_date_day.setHorizontalAlignment(JTextField.CENTER);
        party_date_day.setBorder(BorderFactory.createLineBorder(C2));
        party_date_day.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_day.setBounds((int)bounds.getWidth()/10 + 140, (int)bounds.getHeight()/10, 60, 30);
        party_date_day.setEditable(false);
        party_date_day.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_day.getText();
                party_date_day.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_day.setText("DAY");
        party_date_day.setVisible(false);

        ArrayList<String> days = new ArrayList<String>();
        for(int i = 1; i<32; i++) {
            if(i<10) {
                days.add("0" + String.valueOf(i));
            }
            else {
                days.add(String.valueOf(i));
            }
        }


        JComboBox days_combobox = new JComboBox<String>();
        days_combobox.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));

        days_combobox.setBackground(B1);
        days_combobox.setForeground(C2);
        days_combobox.setBorder(BorderFactory.createLineBorder(C2));
        days_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        days_combobox.setBounds((int)bounds.getWidth()/10 + 140, (int)bounds.getHeight()/10, 60, 30);
        ItemListener day = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_day.setText((String) e.getItem());
            }
        };
        days_combobox.addItemListener(day);
        days_combobox.setVisible(true);

        JTextField number_of_pepole = new JTextField();

        number_of_pepole.setBackground(B1);
        number_of_pepole.setForeground(C2);
        number_of_pepole.setHorizontalAlignment(JTextField.CENTER);
        number_of_pepole.setBorder(BorderFactory.createLineBorder(C2));
        number_of_pepole.setFont(new Font("Courier", Font.PLAIN, 15));
        number_of_pepole.setBounds((int)bounds.getWidth()/10 + 210, (int)bounds.getHeight()/10, 50, 30);
        number_of_pepole.setEditable(true);
        number_of_pepole.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = number_of_pepole.getText();
                number_of_pepole.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = number_of_pepole.getText();
                number_of_pepole.setText(before);
            }
        });
        number_of_pepole.setText("Number of pepole: ");

        JTextField type_of_party = new JTextField();

        type_of_party.setBackground(B1);
        type_of_party.setForeground(C2);
        type_of_party.setHorizontalAlignment(JTextField.CENTER);
        type_of_party.setBorder(BorderFactory.createLineBorder(C2));
        type_of_party.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_party.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10 + 40, 150, 30);
        type_of_party.setEditable(true);
        type_of_party.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_party.getText();
                type_of_party.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = type_of_party.getText();
                type_of_party.setText(before);
            }
        });
        type_of_party.setText("Type of party: ");

        JTextField type_of_menu = new JTextField();

        type_of_menu.setBackground(B1);
        type_of_menu.setForeground(C2);
        type_of_menu.setHorizontalAlignment(JTextField.CENTER);
        type_of_menu.setBorder(BorderFactory.createLineBorder(C2));
        type_of_menu.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_menu.setBounds((int)bounds.getWidth()/10 + 160, (int)bounds.getHeight()/10 + 40, 150, 30);
        type_of_menu.setEditable(true);
        type_of_menu.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_menu.getText();
                type_of_menu.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = type_of_menu.getText();
                type_of_menu.setText(before);
            }
        });
        type_of_menu.setText("Menu: ");

        JTextField cost = new JTextField();

        cost.setBackground(B2);
        cost.setForeground(C2);
        cost.setHorizontalAlignment(JTextField.CENTER);
        cost.setBorder(BorderFactory.createLineBorder(C2));
        cost.setFont(new Font("Courier", Font.PLAIN, 15));
        cost.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10 + 120, 50, 30);
        cost.setEditable(false);
        cost.setText("Cost: ");

        JTextField attractions = new JTextField();

        attractions.setBackground(B1);
        attractions.setForeground(C2);
        attractions.setHorizontalAlignment(JTextField.CENTER);
        attractions.setBorder(BorderFactory.createLineBorder(C2));
        attractions.setFont(new Font("Courier", Font.PLAIN, 15));
        attractions.setBounds((int)bounds.getWidth()/10 + 60, (int)bounds.getHeight()/10 + 120, 240, 40);
        attractions.setEditable(true);
        attractions.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = attractions.getText();
                attractions.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = attractions.getText();
                attractions.setText(before);
            }
        });
        attractions.setText("Attractions: ");

        JTextField music = new JTextField();

        music.setBackground(B1);
        music.setForeground(C2);
        music.setHorizontalAlignment(JTextField.CENTER);
        music.setBorder(BorderFactory.createLineBorder(C2));
        music.setFont(new Font("Courier", Font.PLAIN, 15));
        music.setBounds((int)bounds.getWidth()/2 - 120, (int)bounds.getHeight()/10 + 80, 150, 30);
        music.setEditable(true);
        music.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = music.getText();
                music.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = music.getText();
                music.setText(before);
            }
        });
        music.setText("Music: ");

        JLabel try_again = new JLabel("Party Exists / Data Mistake - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight() - 100, (int)bounds.getWidth(),60);
        try_again.setVisible(false);

        JButton submit = new JButton("ADD");
        JButton count = new JButton("Count");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput1 = party_date_year.getText();
                String imput2 = party_date_month.getText();
                String imput3 = party_date_day.getText();
                String imput4 = number_of_pepole.getText();
                String imput5 = music.getText();
                String imput6 = type_of_party.getText();
                String imput7 = type_of_menu.getText();
                String imput8 = attractions.getText();
                String imput9 = cost.getText();

                try{
                    String date = imput1 + "-" + imput2 + "-" + imput3;
                    if(imput8.equals("")) {
                        imput8 = "-";
                    }
                    Party p1 = new Party(client_id, LocalDate.parse(date), Integer.parseInt(imput4), TypeOfMusic.valueOf(imput5), TypeOfParty.valueOf(imput6), Menu.valueOf(imput7), imput8, Double.parseDouble(imput9));
                    BookPartyUI.setVisible(false);
                    UI.adminUI(admin_id, bounds, a);
                }
                catch(Exception ex) {
                    try_again.setVisible(true);
                }
            }
        });
        submit.setBounds((int)bounds.getWidth() - 100, (int)bounds.getHeight()/3, 50, 30);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setVisible(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.setVisible(false);
                submit.setVisible(true);
                String[] count_attractions = attractions.getText().split(",");
                System.out.println(number_of_pepole.getText());
                Double c = (Double.parseDouble(number_of_pepole.getText()) * 50.0) + (count_attractions.length * 100.0);
                System.out.println(c);
                cost.setText(c.toString());
            }
        });
        count.setBounds((int)bounds.getWidth() - 100, (int)bounds.getHeight()/3, 50, 30);
        count.setFont(new Font("Courier", Font.PLAIN, 15));
        count.setBackground(Color.BLACK);
        count.setOpaque(false);
        count.setForeground(C2);
        count.setBorder(BorderFactory.createLineBorder(C2));

        BookPartyUI.add(party_date_year);
        BookPartyUI.add(year_combobox);
        BookPartyUI.add(party_date_month);
        BookPartyUI.add(months_combobox);
        BookPartyUI.add(party_date_day);
        BookPartyUI.add(days_combobox);
        BookPartyUI.add(number_of_pepole);
        BookPartyUI.add(type_of_party);
        BookPartyUI.add(type_of_menu);
        BookPartyUI.add(cost);
        BookPartyUI.add(music);
        BookPartyUI.add(attractions);
        BookPartyUI.add(try_again);
        BookPartyUI.add(submit);
        BookPartyUI.add(count);
        BookPartyUI.add(Back);
    }

     //TODO! AddEmployeeUI

    private static void AddEmployeeUI(String admin_id, Rectangle bounds, Admin a) {
        JFrame EmployeeUI = new JFrame("Add Employee:");
        EmployeeUI.setBounds(bounds);
        EmployeeUI.setResizable(false);
        EmployeeUI.setLayout(null);
        EmployeeUI.setVisible(true);
        EmployeeUI.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EmployeeUI.setVisible(false);
                    UI.adminUI(admin_id, bounds, a);
                }
            });

        JPasswordField password = new JPasswordField();
        password.setBackground(B2);
        password.setForeground(C2);
        password.setBorder(BorderFactory.createLineBorder(C2));
        password.setFont(new Font("Courier", Font.PLAIN, 15));
        password.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/3 + (int)bounds.getWidth()/4 - 20, (int)bounds.getHeight()/4 + 90, (int)bounds.getWidth()/4, 30);
        password.setVisible(false);

        JLabel pass = new JLabel("Password: ", SwingConstants.CENTER);
        pass.setBackground(B2);
        pass.setForeground(C2);
        pass.setFont(new Font("Courier", Font.PLAIN, 15));
        pass.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/3  - 20, (int)bounds.getHeight()/4 + 90, (int)bounds.getWidth()/4, 30);
        pass.setVisible(false);

        JCheckBox newAdmin = new JCheckBox("Admin");
        newAdmin.setBackground(B1);
        newAdmin.setForeground(C2);
        newAdmin.setBorder(BorderFactory.createLineBorder(C2));
        newAdmin.setFont(new Font("Courier", Font.PLAIN, 15));
        newAdmin.setBounds((int)bounds.getWidth()/2 - 35, (int)bounds.getHeight()/2 - (2*(int)bounds.getHeight()/5), 70, 30);  
        newAdmin.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
                if(password.isVisible()) {
                    password.setVisible(false);
                    pass.setVisible(false);
                }
                else {
                    password.setVisible(true);
                    pass.setVisible(true);
                }
            }    
         });    

        JTextField name = new JTextField();
        name.setBackground(B2);
        name.setForeground(C2);
        name.setBorder(BorderFactory.createLineBorder(C2));
        name.setFont(new Font("Courier", Font.PLAIN, 15));
        name.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 10, (int)bounds.getHeight()/4, (int)bounds.getWidth()/4, 30);
        name.setText("Name: ");
        name.addFocusListener(new FocusListener() {
            String before = "Name: ";
            public void focusGained(FocusEvent e) {
                before = name.getText();
                name.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = name.getText();
                name.setText(before);
            }
        });

        JTextField surname = new JTextField();
        surname.setBackground(B2);
        surname.setForeground(C2);
        surname.setBorder(BorderFactory.createLineBorder(C2));
        surname.setFont(new Font("Courier", Font.PLAIN, 15));
        surname.setBounds((int)bounds.getWidth()/2 + 10, (int)bounds.getHeight()/4, (int)bounds.getWidth()/4, 30);
        surname.setText("Surname: ");
        surname.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = surname.getText();
                surname.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = surname.getText();
                surname.setText(before);
            }
        });

        JTextField address = new JTextField();
        address.setBackground(B2);
        address.setForeground(C2);
        address.setBorder(BorderFactory.createLineBorder(C2));
        address.setFont(new Font("Courier", Font.PLAIN, 15));
        address.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/3 - 30, (int)bounds.getHeight()/4 + 50, (int)bounds.getWidth()/4, 30);
        address.setText("Address: ");
        address.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = address.getText();
                address.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = address.getText();
                address.setText(before);
            }
        });

        JTextField id = new JTextField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/3 + (int)bounds.getWidth()/4 - 20, (int)bounds.getHeight()/4 + 50, (int)bounds.getWidth()/4, 30);
        id.setText("ID: ");
        id.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = id.getText();
                id.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = id.getText();
                id.setText(before);
            }
        });

        JTextField telephone = new JTextField();
        telephone.setBackground(B2);
        telephone.setForeground(C2);
        telephone.setBorder(BorderFactory.createLineBorder(C2));
        telephone.setFont(new Font("Courier", Font.PLAIN, 15));
        telephone.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/3 + (2*(int)bounds.getWidth()/4) - 10, (int)bounds.getHeight()/4 + 50, (int)bounds.getWidth()/4, 30);
        telephone.setText("Tel: ");
        telephone.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = telephone.getText();
                telephone.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = telephone.getText();
                telephone.setText(before);
            }
        });

        JLabel try_again = new JLabel("Employee Exists - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/14, (int)bounds.getWidth(),60);
        try_again.setVisible(false);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput1 = name.getText();
                String imput2 = surname.getText();
                String imput3 = address.getText();
                String imput4 = id.getText();
                String imput5 = telephone.getText();
                if(newAdmin.isSelected()){
                    String imput6 = password.getText();
                    Employee emp = new Admin(imput1, imput2, imput3, imput4, Integer.parseInt(imput5), imput6);
                }
                else {
                    Employee emp = new Employee(imput1, imput2, imput3, imput4, Integer.parseInt(imput5));
                }
                EmployeeUI.setVisible(false);
                UI.adminLoginUI(bounds);
            }
        });
        id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String imput1 = name.getText();
                        String imput2 = surname.getText();
                        String imput3 = address.getText();
                        String imput4 = id.getText();
                        String imput5 = telephone.getText();
                        Employee emp = new Employee(imput1, imput2, imput3, imput4, Integer.parseInt(imput5));
                        EmployeeUI.setVisible(false);
                        UI.adminUI(admin_id, bounds, a);
                    }
                }
                catch (Exception ex) {
                    try_again.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // do nothing
            }
        });
        submit.setBounds((int)bounds.getWidth()/2 - 50, (int)bounds.getHeight()/4 + 130, 100, 30);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        EmployeeUI.add(name);
        EmployeeUI.add(surname);
        EmployeeUI.add(address);
        EmployeeUI.add(id);
        EmployeeUI.add(telephone);
        EmployeeUI.add(submit);
        EmployeeUI.add(try_again);
        EmployeeUI.add(newAdmin);
        EmployeeUI.add(password);
        EmployeeUI.add(pass);
        EmployeeUI.add(Back);
    }

     //TODO! AdminEditPartyUI

    private static void adminEditPartyUI(Admin a, String admin_id, Rectangle bounds, int option) {
        JFrame ClientLogin = new JFrame("Client ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientLogin.setVisible(false);
                    UI.adminUI(admin_id, bounds, a);
                }
            });

        JLabel instruction = new JLabel("Client ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setForeground(C1);
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth(),20);
        try_again.setVisible(false);
        
        JPasswordField id = new JPasswordField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                //System.out.println(imput);
                if(Client.findInBase(imput) != null) {
                    ClientLogin.setVisible(false);
                    if(option == 1) {
                        UI.BookPartyUI(admin_id, imput, bounds, Client.findInBase(imput), a);
                    }
                    else if(option == 2) {
                        UI.BookedPartiesUI(imput, bounds, Client.findInBase(imput));
                    }  
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
                    //System.out.println(imput);
                    if(Client.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.clientLoginUI(bounds);
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
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
        ClientLogin.add(Back);
    }

    private static void employeeEditPartyUI(Employee emp, String employee_id, Rectangle bounds, int option) {
        JFrame ClientLogin = new JFrame("Client ID - login");
        ClientLogin.setBounds(bounds);
        ClientLogin.setResizable(false);
        ClientLogin.setLayout(null);
        ClientLogin.setVisible(true);
        ClientLogin.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientLogin.setVisible(false);
                    UI.EmployeeUI(employee_id, bounds, emp);
                }
            });
    

        JLabel instruction = new JLabel("Client ID:", SwingConstants.CENTER);
        instruction.setFont(new Font("Courier", Font.PLAIN, 15));
        instruction.setForeground(C1);
        instruction.setBounds(0, (int)bounds.getHeight()/6, (int)bounds.getWidth(),20);

        JLabel try_again = new JLabel("Bad ID - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight()/3 + 60, (int)bounds.getWidth(),20);
        try_again.setVisible(false);
        
        JPasswordField id = new JPasswordField();
        id.setBackground(B2);
        id.setForeground(C2);
        id.setBorder(BorderFactory.createLineBorder(C2));
        id.setFont(new Font("Courier", Font.PLAIN, 15));
        id.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4 - 55, (int)bounds.getHeight()/3 + 20, (int)bounds.getWidth()/2, 30);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput = id.getText();
                //System.out.println(imput);
                if(Client.findInBase(imput) != null) {
                    ClientLogin.setVisible(false);
                    if(option == 1) {
                        UI.BookPartyUI(employee_id, imput, bounds, Client.findInBase(imput), emp);
                    }
                    else if(option == 2) {
                        UI.BookedPartiesUI(imput, bounds, Client.findInBase(imput));
                    }  
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
                    //System.out.println(imput);
                    if(Client.findInBase(imput) != null) {
                        ClientLogin.setVisible(false);
                        UI.clientLoginUI(bounds);
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
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        ClientLogin.add(instruction);
        ClientLogin.add(id);
        ClientLogin.add(submit);
        ClientLogin.add(try_again);
        ClientLogin.add(Back);
    }

    protected static void BookPartyUI(String employee_id, String client_id, Rectangle bounds, Client c, Employee emp) {
        JFrame BookPartyUI = new JFrame("Add Party:");
        BookPartyUI.setBounds(bounds);
        BookPartyUI.setResizable(false);
        BookPartyUI.setLayout(null);
        BookPartyUI.setVisible(true);
        BookPartyUI.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BookPartyUI.setVisible(false);
                    UI.EmployeeUI(employee_id, bounds, emp);
                }
            });

        JTextField party_date_year = new JTextField();
        JTextField party_date_month = new JTextField();
        JTextField party_date_day = new JTextField();

        party_date_year.setBackground(B2);
        party_date_year.setForeground(C2);
        party_date_year.setHorizontalAlignment(JTextField.CENTER);
        party_date_year.setBorder(BorderFactory.createLineBorder(C2));
        party_date_year.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_year.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10, 60, 30);
        party_date_year.setEditable(false);
        party_date_year.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_year.getText();
                party_date_year.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_year.setText("YEAR");
        party_date_year.setVisible(false);

        ArrayList<String> years = new ArrayList<String>();
        years.add(String.valueOf(LocalDate.now().getYear()));
        years.add(String.valueOf(LocalDate.now().getYear() + 1));
        years.add(String.valueOf(LocalDate.now().getYear() + 2));
        years.add(String.valueOf(LocalDate.now().getYear() + 3));
        years.add(String.valueOf(LocalDate.now().getYear() + 4));
        years.add(String.valueOf(LocalDate.now().getYear() + 5));
        years.add(String.valueOf(LocalDate.now().getYear() + 6));
        years.add(String.valueOf(LocalDate.now().getYear() + 7));
        years.add(String.valueOf(LocalDate.now().getYear() + 8));
        years.add(String.valueOf(LocalDate.now().getYear() + 9));

        JComboBox year_combobox = new JComboBox<String>();
        year_combobox.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));

        year_combobox.setBackground(B1);
        year_combobox.setForeground(C2);
        year_combobox.setBorder(BorderFactory.createLineBorder(C2));
        year_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        year_combobox.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10, 60, 30);
        ItemListener year = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_year.setText((String) e.getItem());
            }
        };
        year_combobox.addItemListener(year);
        year_combobox.setVisible(true);

        party_date_month.setBackground(B2);
        party_date_month.setForeground(C2);
        party_date_month.setHorizontalAlignment(JTextField.CENTER);
        party_date_month.setBorder(BorderFactory.createLineBorder(C2));
        party_date_month.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_month.setBounds((int)bounds.getWidth()/10 + 70, (int)bounds.getHeight()/10, 60, 30);
        party_date_month.setEditable(false);
        party_date_month.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_month.getText();
                party_date_month.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_month.setText("MONTH");
        party_date_month.setVisible(false);

        ArrayList<String> months = new ArrayList<String>();
        for(int i = 1; i<13; i++) {
            if(i<10) {
                months.add("0" + String.valueOf(i));
            }
            else {
                months.add(String.valueOf(i));
            }
        }

        JComboBox months_combobox = new JComboBox<String>();
        months_combobox.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));

        months_combobox.setBackground(B1);
        months_combobox.setForeground(C2);
        months_combobox.setBorder(BorderFactory.createLineBorder(C2));
        months_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        months_combobox.setBounds((int)bounds.getWidth()/10 + 70, (int)bounds.getHeight()/10, 60, 30);
        ItemListener month = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_month.setText((String) e.getItem());
            }
        };
        months_combobox.addItemListener(month);
        months_combobox.setVisible(true);

        party_date_day.setBackground(B2);
        party_date_day.setForeground(C2);
        party_date_day.setHorizontalAlignment(JTextField.CENTER);
        party_date_day.setBorder(BorderFactory.createLineBorder(C2));
        party_date_day.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_day.setBounds((int)bounds.getWidth()/10 + 140, (int)bounds.getHeight()/10, 60, 30);
        party_date_day.setEditable(false);
        party_date_day.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_day.getText();
                party_date_day.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_day.setText("DAY");
        party_date_day.setVisible(false);

        ArrayList<String> days = new ArrayList<String>();
        for(int i = 1; i<32; i++) {
            if(i<10) {
                days.add("0" + String.valueOf(i));
            }
            else {
                days.add(String.valueOf(i));
            }
        }


        JComboBox days_combobox = new JComboBox<String>();
        days_combobox.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));

        days_combobox.setBackground(B1);
        days_combobox.setForeground(C2);
        days_combobox.setBorder(BorderFactory.createLineBorder(C2));
        days_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        days_combobox.setBounds((int)bounds.getWidth()/10 + 140, (int)bounds.getHeight()/10, 60, 30);
        ItemListener day = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_day.setText((String) e.getItem());
            }
        };
        days_combobox.addItemListener(day);
        days_combobox.setVisible(true);

        JTextField number_of_pepole = new JTextField();

        number_of_pepole.setBackground(B1);
        number_of_pepole.setForeground(C2);
        number_of_pepole.setHorizontalAlignment(JTextField.CENTER);
        number_of_pepole.setBorder(BorderFactory.createLineBorder(C2));
        number_of_pepole.setFont(new Font("Courier", Font.PLAIN, 15));
        number_of_pepole.setBounds((int)bounds.getWidth()/10 + 210, (int)bounds.getHeight()/10, 50, 30);
        number_of_pepole.setEditable(true);
        number_of_pepole.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = number_of_pepole.getText();
                number_of_pepole.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = number_of_pepole.getText();
                number_of_pepole.setText(before);
            }
        });
        number_of_pepole.setText("Number of pepole: ");

        JTextField type_of_party = new JTextField();

        type_of_party.setBackground(B1);
        type_of_party.setForeground(C2);
        type_of_party.setHorizontalAlignment(JTextField.CENTER);
        type_of_party.setBorder(BorderFactory.createLineBorder(C2));
        type_of_party.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_party.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10 + 40, 150, 30);
        type_of_party.setEditable(true);
        type_of_party.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_party.getText();
                type_of_party.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = type_of_party.getText();
                type_of_party.setText(before);
            }
        });
        type_of_party.setText("Type of party: ");

        JTextField type_of_menu = new JTextField();

        type_of_menu.setBackground(B1);
        type_of_menu.setForeground(C2);
        type_of_menu.setHorizontalAlignment(JTextField.CENTER);
        type_of_menu.setBorder(BorderFactory.createLineBorder(C2));
        type_of_menu.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_menu.setBounds((int)bounds.getWidth()/10 + 160, (int)bounds.getHeight()/10 + 40, 150, 30);
        type_of_menu.setEditable(true);
        type_of_menu.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_menu.getText();
                type_of_menu.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = type_of_menu.getText();
                type_of_menu.setText(before);
            }
        });
        type_of_menu.setText("Menu: ");

        JTextField cost = new JTextField();

        cost.setBackground(B2);
        cost.setForeground(C2);
        cost.setHorizontalAlignment(JTextField.CENTER);
        cost.setBorder(BorderFactory.createLineBorder(C2));
        cost.setFont(new Font("Courier", Font.PLAIN, 15));
        cost.setBounds((int)bounds.getWidth()/10, (int)bounds.getHeight()/10 + 120, 50, 30);
        cost.setEditable(false);
        cost.setText("Cost: ");

        JTextField attractions = new JTextField();

        attractions.setBackground(B1);
        attractions.setForeground(C2);
        attractions.setHorizontalAlignment(JTextField.CENTER);
        attractions.setBorder(BorderFactory.createLineBorder(C2));
        attractions.setFont(new Font("Courier", Font.PLAIN, 15));
        attractions.setBounds((int)bounds.getWidth()/10 + 60, (int)bounds.getHeight()/10 + 120, 240, 40);
        attractions.setEditable(true);
        attractions.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = attractions.getText();
                attractions.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = attractions.getText();
                attractions.setText(before);
            }
        });
        attractions.setText("Attractions: ");

        JTextField music = new JTextField();

        music.setBackground(B1);
        music.setForeground(C2);
        music.setHorizontalAlignment(JTextField.CENTER);
        music.setBorder(BorderFactory.createLineBorder(C2));
        music.setFont(new Font("Courier", Font.PLAIN, 15));
        music.setBounds((int)bounds.getWidth()/2 - 120, (int)bounds.getHeight()/10 + 80, 150, 30);
        music.setEditable(true);
        music.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = music.getText();
                music.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                before = music.getText();
                music.setText(before);
            }
        });
        music.setText("Music: ");

        JLabel try_again = new JLabel("Party Exists / Data Mistake - Try Again.", SwingConstants.CENTER);
        try_again.setFont(new Font("Courier", Font.PLAIN, 12));
        try_again.setForeground(E);
        try_again.setBounds(0, (int)bounds.getHeight() - 100, (int)bounds.getWidth(),60);
        try_again.setVisible(false);

        JButton submit = new JButton("ADD");
        JButton count = new JButton("Count");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imput1 = party_date_year.getText();
                String imput2 = party_date_month.getText();
                String imput3 = party_date_day.getText();
                String imput4 = number_of_pepole.getText();
                String imput5 = music.getText();
                String imput6 = type_of_party.getText();
                String imput7 = type_of_menu.getText();
                String imput8 = attractions.getText();
                String imput9 = cost.getText();

                try{
                    String date = imput1 + "-" + imput2 + "-" + imput3;
                    if(imput8.equals("")) {
                        imput8 = "-";
                    }
                    Party p1 = new Party(client_id, LocalDate.parse(date), Integer.parseInt(imput4), TypeOfMusic.valueOf(imput5), TypeOfParty.valueOf(imput6), Menu.valueOf(imput7), imput8, Double.parseDouble(imput9));
                    BookPartyUI.setVisible(false);
                    UI.EmployeeUI(employee_id, bounds, emp);
                }
                catch(Exception ex) {
                    try_again.setVisible(true);
                }
            }
        });
        submit.setBounds((int)bounds.getWidth() - 100, (int)bounds.getHeight()/3, 50, 30);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));
        submit.setBackground(Color.BLACK);
        submit.setOpaque(false);
        submit.setVisible(false);
        submit.setForeground(C2);
        submit.setBorder(BorderFactory.createLineBorder(C2));

        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.setVisible(false);
                submit.setVisible(true);
                String[] count_attractions = attractions.getText().split(",");
                System.out.println(number_of_pepole.getText());
                Double c = (Double.parseDouble(number_of_pepole.getText()) * 50.0) + (count_attractions.length * 100.0);
                System.out.println(c);
                cost.setText(c.toString());
            }
        });
        count.setBounds((int)bounds.getWidth() - 100, (int)bounds.getHeight()/3, 50, 30);
        count.setFont(new Font("Courier", Font.PLAIN, 15));
        count.setBackground(Color.BLACK);
        count.setOpaque(false);
        count.setForeground(C2);
        count.setBorder(BorderFactory.createLineBorder(C2));

        BookPartyUI.add(party_date_year);
        BookPartyUI.add(year_combobox);
        BookPartyUI.add(party_date_month);
        BookPartyUI.add(months_combobox);
        BookPartyUI.add(party_date_day);
        BookPartyUI.add(days_combobox);
        BookPartyUI.add(number_of_pepole);
        BookPartyUI.add(type_of_party);
        BookPartyUI.add(type_of_menu);
        BookPartyUI.add(cost);
        BookPartyUI.add(music);
        BookPartyUI.add(attractions);
        BookPartyUI.add(try_again);
        BookPartyUI.add(submit);
        BookPartyUI.add(count);
        BookPartyUI.add(Back);
    }

     //TODO! BookedPartiesUI

    private static void BookedPartiesUI(String client_id, Rectangle bounds, Client c) {
        JFrame BookedPartiesUI = new JFrame("" + c.getName() + ": Your booked parties");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;

        BookedPartiesUI.setBounds(width/2 - (int)bounds.getWidth(), height/2 - (int)bounds.getHeight(), (int)bounds.getWidth()*2, (int)bounds.getHeight()*2);
        BookedPartiesUI.setResizable(false);
        BookedPartiesUI.setLayout(null);
        BookedPartiesUI.setVisible(true);
        BookedPartiesUI.getContentPane().setBackground(B1);

        JButton Back = new JButton("<-");
            Back.setBackground(B1);
            Back.setOpaque(false);
            Back.setForeground(C2);
            Back.setFocusable(false);
            Back.setBorder(BorderFactory.createLineBorder(C2));
            Back.setFont(new Font("Courier", Font.PLAIN, 15));
            Back.setBounds(0, 0, 40, 40);
            Back.setVisible(true);
    
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BookedPartiesUI.setVisible(false);
                    UI.employeeLoginUI(bounds);
                }
            });

        JButton EditParty = new JButton("Edit this party:");

        EditParty.setBackground(Color.BLACK);
        EditParty.setOpaque(false);
        EditParty.setForeground(C2);
        EditParty.setFocusable(false);
        EditParty.setBorder(BorderFactory.createLineBorder(C2));

        EditParty.setFont(new Font("Courier", Font.PLAIN, 15));

        EditParty.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 20, 200, 40);

        JTextField party_date_year = new JTextField();
        JTextField party_date_month = new JTextField();
        JTextField party_date_day = new JTextField();

        party_date_year.setBackground(B2);
        party_date_year.setForeground(C2);
        party_date_year.setHorizontalAlignment(JTextField.CENTER);
        party_date_year.setBorder(BorderFactory.createLineBorder(C2));
        party_date_year.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_year.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_year.setEditable(false);
        party_date_year.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_year.getText();
                party_date_year.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_year.setText("YEAR");

        ArrayList<String> years = new ArrayList<String>();
        years.add(String.valueOf(LocalDate.now().getYear()));
        years.add(String.valueOf(LocalDate.now().getYear() + 1));
        years.add(String.valueOf(LocalDate.now().getYear() + 2));
        years.add(String.valueOf(LocalDate.now().getYear() + 3));
        years.add(String.valueOf(LocalDate.now().getYear() + 4));
        years.add(String.valueOf(LocalDate.now().getYear() + 5));
        years.add(String.valueOf(LocalDate.now().getYear() + 6));
        years.add(String.valueOf(LocalDate.now().getYear() + 7));
        years.add(String.valueOf(LocalDate.now().getYear() + 8));
        years.add(String.valueOf(LocalDate.now().getYear() + 9));

        JComboBox year_combobox = new JComboBox<String>();
        year_combobox.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));

        year_combobox.setBackground(B1);
        year_combobox.setForeground(C2);
        year_combobox.setBorder(BorderFactory.createLineBorder(C2));
        year_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        year_combobox.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 100, 60, 30);
        ItemListener year = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_year.setText((String) e.getItem());
            }
        };
        year_combobox.addItemListener(year);
        year_combobox.setVisible(false);

        party_date_month.setBackground(B2);
        party_date_month.setForeground(C2);
        party_date_month.setHorizontalAlignment(JTextField.CENTER);
        party_date_month.setBorder(BorderFactory.createLineBorder(C2));
        party_date_month.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_month.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_month.setEditable(false);
        party_date_month.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_month.getText();
                party_date_month.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_month.setText("MONTH");

        ArrayList<String> months = new ArrayList<String>();
        for(int i = 1; i<13; i++) {
            if(i<10) {
                months.add("0" + String.valueOf(i));
            }
            else {
                months.add(String.valueOf(i));
            }
        }

        JComboBox months_combobox = new JComboBox<String>();
        months_combobox.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));

        months_combobox.setBackground(B1);
        months_combobox.setForeground(C2);
        months_combobox.setBorder(BorderFactory.createLineBorder(C2));
        months_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        months_combobox.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 100, 60, 30);
        ItemListener month = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_month.setText((String) e.getItem());
            }
        };
        months_combobox.addItemListener(month);
        months_combobox.setVisible(false);

        party_date_day.setBackground(B2);
        party_date_day.setForeground(C2);
        party_date_day.setHorizontalAlignment(JTextField.CENTER);
        party_date_day.setBorder(BorderFactory.createLineBorder(C2));
        party_date_day.setFont(new Font("Courier", Font.PLAIN, 15));
        party_date_day.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 150, (int)bounds.getHeight()/3 + 100, 60, 30);
        party_date_day.setEditable(false);
        party_date_day.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = party_date_day.getText();
                party_date_day.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        party_date_day.setText("DAY");

        ArrayList<String> days = new ArrayList<String>();
        for(int i = 1; i<32; i++) {
            if(i<10) {
                days.add("0" + String.valueOf(i));
            }
            else {
                days.add(String.valueOf(i));
            }
        }


        JComboBox days_combobox = new JComboBox<String>();
        days_combobox.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));

        days_combobox.setBackground(B1);
        days_combobox.setForeground(C2);
        days_combobox.setBorder(BorderFactory.createLineBorder(C2));
        days_combobox.setFont(new Font("Courier", Font.PLAIN, 15));
        days_combobox.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 150, (int)bounds.getHeight()/3 + 100, 60, 30);
        ItemListener day = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                party_date_day.setText((String) e.getItem());
            }
        };
        days_combobox.addItemListener(day);
        days_combobox.setVisible(false);

        JTextField number_of_pepole = new JTextField();

        number_of_pepole.setBackground(B2);
        number_of_pepole.setForeground(C2);
        number_of_pepole.setHorizontalAlignment(JTextField.CENTER);
        number_of_pepole.setBorder(BorderFactory.createLineBorder(C2));
        number_of_pepole.setFont(new Font("Courier", Font.PLAIN, 15));
        number_of_pepole.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 140, 50, 30);
        number_of_pepole.setEditable(false);
        number_of_pepole.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = number_of_pepole.getText();
                number_of_pepole.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        number_of_pepole.setText("Number of pepole: ");

        JTextField type_of_party = new JTextField();

        type_of_party.setBackground(B2);
        type_of_party.setForeground(C2);
        type_of_party.setHorizontalAlignment(JTextField.CENTER);
        type_of_party.setBorder(BorderFactory.createLineBorder(C2));
        type_of_party.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_party.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 140, 150, 30);
        type_of_party.setEditable(false);
        type_of_party.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_party.getText();
                type_of_party.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        type_of_party.setText("Type of party: ");

        JTextField type_of_menu = new JTextField();

        type_of_menu.setBackground(B2);
        type_of_menu.setForeground(C2);
        type_of_menu.setHorizontalAlignment(JTextField.CENTER);
        type_of_menu.setBorder(BorderFactory.createLineBorder(C2));
        type_of_menu.setFont(new Font("Courier", Font.PLAIN, 15));
        type_of_menu.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) + 70, (int)bounds.getHeight()/3 + 180, 150, 30);
        type_of_menu.setEditable(false);
        type_of_menu.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = type_of_menu.getText();
                type_of_menu.setText("");
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        type_of_menu.setText("Menu: ");

        JTextField cost = new JTextField();

        cost.setBackground(B2);
        cost.setForeground(C2);
        cost.setHorizontalAlignment(JTextField.CENTER);
        cost.setBorder(BorderFactory.createLineBorder(C2));
        cost.setFont(new Font("Courier", Font.PLAIN, 15));
        cost.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 180, 50, 30);
        cost.setEditable(false);
        cost.setText("Cost: ");

        JTextField attractions = new JTextField();

        attractions.setBackground(B2);
        attractions.setForeground(C2);
        attractions.setHorizontalAlignment(JTextField.CENTER);
        attractions.setBorder(BorderFactory.createLineBorder(C2));
        attractions.setFont(new Font("Courier", Font.PLAIN, 15));
        attractions.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 260, 240, 40);
        attractions.setEditable(false);
        attractions.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = attractions.getText();
                attractions.setText(before);
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        attractions.setText("Attractions: ");

        JTextField music = new JTextField();

        music.setBackground(B2);
        music.setForeground(C2);
        music.setHorizontalAlignment(JTextField.CENTER);
        music.setBorder(BorderFactory.createLineBorder(C2));
        music.setFont(new Font("Courier", Font.PLAIN, 15));
        music.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 20, (int)bounds.getHeight()/3 + 220, 240, 30);
        music.setEditable(false);
        music.addFocusListener(new FocusListener() {
            String before;
            public void focusGained(FocusEvent e) {
                before = music.getText();
                music.setText(before);
            }
        
            public void focusLost(FocusEvent e) {
            }
        });
        music.setText("Music: ");

        // ImageIcon test = new ImageIcon("icons/icons8-calendar-24.png");
        // JButton testB = new JButton("", test);
        // JLabel callendar = new JLabel(new ImageIcon("icons/icons8-calendar-24.png"),JLabel.CENTER);
        // //callendar.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 50, (int)bounds.getHeight()/3 + 140, 24, 24);
        // testB.setBounds(10, 10, 100, 100);
        // callendar.setBounds(10, 120, 100, 100);

        JButton CountCost = new JButton("COUNT COST");
        JButton SavesChanges = new JButton("SAVE");

        String temp_parties = Party.findParties(c.getId_number());
        String[] temp1 = temp_parties.split(" @");
        String[] temp2;
        String[] names = new String[temp1.length - 1];

        for(int i = 0; i < temp1.length - 1; i++){
            temp2 = temp1[i].split(" ");
            names[i] = i+1 + ") " + temp2[2] + " " + temp2[5].replace("_", " ").toUpperCase() + " cost: " + temp2[8] + " PLN";
        }
        JList<String> Parties = new JList<String>(names);
        Parties.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String[] temp3 = Parties.getSelectedValue().split(" ");
                String choosen = Party.findParty(c.getId_number(), LocalDate.parse(temp3[1]));
                System.out.println(choosen);
                String[] temp4 = choosen.split(" ");
                Party edited = new Party(c.getId_number(), LocalDate.parse(temp4[2]), Integer.parseInt(temp4[3]), TypeOfMusic.valueOf(temp4[4]), TypeOfParty.valueOf(temp4[5]), Menu.valueOf(temp4[6]), temp4[7], Double.parseDouble(temp4[8]));
                String[] temp_date = temp4[2].split("-");
                party_date_year.setText(temp_date[0]);
                party_date_month.setText(temp_date[1]);
                party_date_day.setText(temp_date[2]);
                number_of_pepole.setText(temp4[3]);
                music.setText(temp4[4]);
                type_of_party.setText(temp4[5]);
                type_of_menu.setText(temp4[6]);
                attractions.setText(temp4[7]);
                cost.setText(temp4[8]);

                party_date_year.setVisible(true);
                year_combobox.setVisible(false);
                party_date_month.setVisible(true);
                months_combobox.setVisible(false);
                party_date_day.setVisible(true);
                days_combobox.setVisible(false);
                CountCost.setVisible(true);
            }
        });


        SavesChanges.setBackground(Color.BLACK);
        SavesChanges.setOpaque(false);
        SavesChanges.setForeground(C2);
        SavesChanges.setFocusable(false);
        SavesChanges.setBorder(BorderFactory.createLineBorder(C2));
        SavesChanges.setVisible(false);

        SavesChanges.setFont(new Font("Courier", Font.PLAIN, 15));

        SavesChanges.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 310, 200, 40);
        SavesChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temp = Parties.getSelectedValue().split(" ");
                String toSave = Party.findParty(c.getId_number(), LocalDate.parse(temp[1]));
                System.out.println("test " + toSave);
                String[] temp_save = toSave.split(" ");
                Party toDelete = new Party(temp_save[1], LocalDate.parse(temp_save[2]), Integer.parseInt(temp_save[3]), TypeOfMusic.valueOf(temp_save[4]), TypeOfParty.valueOf(temp_save[5]), Menu.valueOf(temp_save[6]), temp_save[7], Double.parseDouble(temp_save[8]));
                System.out.println(toDelete.toString());
                String editedDay = "";
                editedDay = editedDay.concat(party_date_year.getText() + "-");
                editedDay = editedDay.concat(party_date_month.getText() + "-");
                editedDay = editedDay.concat(party_date_day.getText());

                toDelete.deleteParty();

                Party saved = new Party(client_id, LocalDate.parse(editedDay), Integer.parseInt(number_of_pepole.getText()), TypeOfMusic.valueOf(music.getText()), TypeOfParty.valueOf(type_of_party.getText()), Menu.valueOf(type_of_menu.getText()), attractions.getText(), Double.parseDouble(cost.getText()));

                //saved.getAttractions();
                
                SwingUtilities.updateComponentTreeUI(BookedPartiesUI);
                BookedPartiesUI.invalidate();
                BookedPartiesUI.validate();
                BookedPartiesUI.repaint();

                SavesChanges.setVisible(false);
                BookedPartiesUI.setVisible(false);
                UI.clientLoginUI(bounds);
            }
        });


        CountCost.setBackground(Color.BLACK);
        CountCost.setOpaque(false);
        CountCost.setForeground(C2);
        CountCost.setFocusable(false);
        CountCost.setBorder(BorderFactory.createLineBorder(C2));
        CountCost.setVisible(false);

        CountCost.setFont(new Font("Courier", Font.PLAIN, 15));

        CountCost.setBounds((int)BookedPartiesUI.getWidth() - ((int)bounds.getWidth()/2 + (int)bounds.getWidth()/12) - 10, (int)bounds.getHeight()/3 + 310, 200, 40);
        CountCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountCost.setVisible(false);
                SavesChanges.setVisible(true);
                String[] count_attractions = attractions.getText().split(",");
                System.out.println(number_of_pepole.getText());
                Double c = (Double.parseDouble(number_of_pepole.getText()) * 50.0) + (count_attractions.length * 100.0);
                System.out.println(c);
                cost.setText(c.toString());
            }
        });

        EditParty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                party_date_year.setVisible(false);
                year_combobox.setVisible(true);
                party_date_month.setVisible(false);
                months_combobox.setVisible(true);
                party_date_day.setVisible(false);
                days_combobox.setVisible(true);
                number_of_pepole.setEditable(true);
                type_of_party.setEditable(true);
                type_of_menu.setEditable(true);
                music.setEditable(true);
                attractions.setEditable(true);

                party_date_year.setBackground(B1);
                party_date_month.setBackground(B1);
                party_date_day.setBackground(B1);
                number_of_pepole.setBackground(B1);
                type_of_party.setBackground(B1);
                type_of_menu.setBackground(B1);
                music.setBackground(B1);
                attractions.setBackground(B1);
            }
        });

        Parties.setFont(new Font("Courier", Font.PLAIN, 15));
        Parties.setBounds((int)bounds.getWidth()/2 - (int)bounds.getWidth()/4, (int)bounds.getHeight()/2 - (int)bounds.getHeight()/3, (int)bounds.getWidth(), (int)bounds.getHeight()*3/2);
        Parties.setBackground(B2);
        Parties.setForeground(C1);
        Parties.setBorder(BorderFactory.createLineBorder(C2));

        BookedPartiesUI.add(Parties);
        //BookedPartiesUI.add(FullInfo);
        BookedPartiesUI.add(EditParty);
        BookedPartiesUI.add(party_date_year);
        BookedPartiesUI.add(year_combobox);
        BookedPartiesUI.add(party_date_month);
        BookedPartiesUI.add(months_combobox);
        BookedPartiesUI.add(party_date_day);
        BookedPartiesUI.add(days_combobox);
        BookedPartiesUI.add(number_of_pepole);
        BookedPartiesUI.add(type_of_party);
        BookedPartiesUI.add(type_of_menu);
        BookedPartiesUI.add(cost);
        BookedPartiesUI.add(music);
        BookedPartiesUI.add(attractions);
        BookedPartiesUI.add(SavesChanges);
        BookedPartiesUI.add(CountCost);
        BookedPartiesUI.add(Back);
        // BookedPartiesUI.add(callendar);
        // BookedPartiesUI.add(testB);
    }
}
