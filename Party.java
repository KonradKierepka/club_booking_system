package club_booking_system;

import java.time.LocalDate;

import java.io.File; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Party {

    public enum TypeOfMusic {
        pop, rock, disco, rap, dance;
    }

    public enum TypeOfParty {
        birthday_party, bachelor_party, corporate_party, family_event, other;
    }

    public enum Menu {
        normal, vegetarian, vegan;
    }

    private String client_id;
    private LocalDate date;
    private int number_of_people;
    private TypeOfMusic type_of_music;
    private TypeOfParty type_of_party;
    private Menu menu;
    private String attractions;
    private double cost;

    public Party(String client_id, LocalDate date, int number_of_people, TypeOfMusic type_of_music, TypeOfParty type_of_party, Menu menu, String attractions, double cost) {
        this.client_id = client_id;
        this.date = date;
        this.number_of_people = number_of_people;
        this.type_of_music = type_of_music;
        this.type_of_party = type_of_party;
        this.menu = menu;
        this.attractions = attractions;
        this.cost = cost;
        this.saveIntoBase();
    }

    private void saveIntoBase() {
        try {
            File base = new File("parties.txt");
            if (base.createNewFile()) {
              System.out.println("File created: " + base.getName());
            } else {
              System.out.println("File already exists.");
            }

            BufferedReader reader = new BufferedReader(new FileReader("parties.txt"));

            int lines = 0;
            String test = reader.readLine();
            while (test != null) {
                lines++;
                System.out.println(test.compareTo(lines + ") " + this.client_id + " " + this.date + " " + this.number_of_people + " " + this.type_of_music + " " + this.type_of_party + " " + this.menu + " " + this.attractions + " " + this.cost));
                if(test.compareTo(lines + ") " + this.client_id + " " + this.date + " " + this.number_of_people + " " + this.type_of_music + " " + this.type_of_party + " " + this.menu + " " + this.attractions + " " + this.cost) == 0) {
                    throw new IOException();
                }
                test = reader.readLine();
            }

            reader.close();
            lines++;

            BufferedWriter writer = new BufferedWriter(new FileWriter("parties.txt", true));
            writer.write(lines + ") " + this.client_id + " " + this.date + " " + this.number_of_people + " " + this.type_of_music + " " + this.type_of_party + " " + this.menu + " " + this.attractions + " " + this.cost + "\n");
            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }

    public void editParty(LocalDate date, int number_of_people, TypeOfMusic type_of_music, TypeOfParty type_of_party, Menu menu, String attractions, double cost) {
        try {
            LocalDate oldDate = this.date;
            int oldNumber_of_people = this.number_of_people;
            TypeOfMusic oldType_of_music = this.type_of_music;
            TypeOfParty oldType_of_party = this.type_of_party;
            Menu oldMenu = this.menu;
            String oldAttractions = this.attractions;
            double oldCost = this.cost;

            File base = new File("parties.txt");
            File temp = new File("temp_parties.txt");
            if (base.createNewFile() || temp.createNewFile()) {
              System.out.println("File created: " + base.getName() + " " + temp.getName());
            } else {
              System.out.println("File already exists.");
            }
    
            BufferedReader reader = new BufferedReader(new FileReader("parties.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp_parties.txt"));
    
            String line1 = reader.readLine();
            String toEdit =  this.client_id + " " + oldDate + " " + oldNumber_of_people + " " + oldType_of_music + " " + oldType_of_party + " " + oldMenu + " " + oldAttractions + " " + oldCost;
            int line_number = 1;
            toEdit = line_number + ") " + toEdit;
    
            while (line1 != null) {
              toEdit = toEdit.replaceFirst("[0-99]", "" + line_number);
              if(line1.compareTo(toEdit)==0) {
                String edited = line_number + ") " + this.client_id + " " + date + " " + number_of_people + " " + type_of_music + " " + type_of_party + " " + menu + " " + attractions + " " + cost;
                writer.write(edited);
                writer.newLine();
                line1 = reader.readLine();
                line_number++;
                continue;
              }
              else {
                String edited = line1.replaceFirst("[0-99]", "" + line_number);
                writer.write(edited);
                writer.newLine();
                line_number++;
              }
    
              line1 = reader.readLine();
            }
            writer.flush();
            writer.close();
            reader.close();
    
            base.delete();
            temp.renameTo(base);

            this.date = date;
            this.number_of_people = number_of_people;
            this.type_of_music = type_of_music;
            this.type_of_party = type_of_party;
            this.menu = menu;
            this.attractions = attractions;
            this.cost = cost;
          } catch (IOException e) {
            System.out.println("An error occurred.");
          } catch (NullPointerException e) {
            System.out.println("End of file.");
          }
    }

    public void deleteParty() {
        try {
            File base = new File("parties.txt");
            File temp = new File("temp_parties.txt");
            if (base.createNewFile() || temp.createNewFile()) {
              System.out.println("File created: " + base.getName() + " " + temp.getName());
            } else {
              System.out.println("File already exists.");
            }
    
            BufferedReader reader = new BufferedReader(new FileReader("parties.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp_parties.txt"));
    
            String line1 = reader.readLine();
            String toDelete =  this.client_id + " " + this.date + " " + this.number_of_people + " " + this.type_of_music + " " + this.type_of_party + " " + this.menu + " " + this.attractions + " " + this.cost;
            int line_number = 1;
            int number_of_deleted = 0;
            toDelete = line_number + ") " + toDelete;
    
            while (line1 != null) {
              toDelete = toDelete.replaceFirst("[0-99]", "" + line_number);
              if(line1.compareTo(toDelete)==0) {
                System.out.println("Deleted!");
                line1 = reader.readLine();
                line_number++;
                number_of_deleted++;
                continue;
              }
              else {
                String edited = line1.replaceFirst("[0-99]", "" + (line_number - number_of_deleted));
                writer.write(edited);
                writer.newLine();
                line_number++;
              }
    
              line1 = reader.readLine();
            }
            writer.flush();
            writer.close();
            reader.close();
    
            base.delete();
            temp.renameTo(base);
   
            this.date = null;
            this.number_of_people = 0;
            this.type_of_music = null;
            this.type_of_party = null;
            this.menu = null;
            this.attractions = null;
            this.cost = 0.0;
          } catch (IOException e) {
            System.out.println("An error occurred.");
          } catch (NullPointerException e) {
            System.out.println("End of file.");
          }
    }    

    public String getClient_id() {
        return this.client_id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getNumber_of_people() {
        return this.number_of_people;
    }

    public TypeOfMusic getType_of_music() {
        return this.type_of_music;
    }

    public TypeOfParty getType_of_party() {
        return this.type_of_party;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public String getAttractions() {
        return this.attractions;
    }

    public double getCost() {
        return this.cost;
    }
    
}
