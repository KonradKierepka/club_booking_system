package Projekt;

import java.io.File; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Klient {
    protected String name; 
    protected String surname;
    protected String id_number;
    protected Integer telephone_number;

    public Klient(String name, String surname, String id_number, Integer telephone_number) {
        this.name = name;
        this.surname = surname;
        this.id_number = id_number;
        this.telephone_number = telephone_number;
        this.saveIntoBase();
    }

    private void saveIntoBase() {
        try {
            File base = new File("clients.txt");
            if (base.createNewFile()) {
              System.out.println("File created: " + base.getName());
            } else {
              System.out.println("File already exists.");
            }

            BufferedReader reader = new BufferedReader(new FileReader("clients.txt"));

            int lines = 0;
            String test = reader.readLine();
            while (test != null) {
                lines++;
                if(test.compareTo(lines + ") " + this.name + " " + this.surname + " " + this.id_number + " " + this.telephone_number) == 0) {
                    throw new IOException();
                }
                test = reader.readLine();
            }

            reader.close();
            lines++;

            BufferedWriter writer = new BufferedWriter(new FileWriter("clients.txt", true));
            writer.write(lines + ") " + this.name + " " + this.surname + " " + this.id_number + " " + this.telephone_number + "\n");
            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }

    public void deleteClient() {
        try {
          File base = new File("clients.txt");
          File temp = new File("temp_clients.txt");
          if (base.createNewFile() || temp.createNewFile()) {
            System.out.println("File created: " + base.getName() + " " + temp.getName());
          } else {
            System.out.println("File already exists.");
          }
  
          BufferedReader reader = new BufferedReader(new FileReader("clients.txt"));
          BufferedWriter writer = new BufferedWriter(new FileWriter("temp_clents.txt"));
  
          String line1 = reader.readLine();
          String toDelete =  this.name + " " + this.surname + " " + this.id_number + " " + this.telephone_number;
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
        } catch (IOException e) {
          System.out.println("An error occurred.");
        } catch (NullPointerException e) {
          System.out.println("End of file.");
        }
      }
}
