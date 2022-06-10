package Projekt;

import java.io.File; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class Attraction {
    private String name;
    private Double cost; 


    public Attraction(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.saveIntoBase();
    }

    private void saveIntoBase() {
        try {
            File base = new File("attractions.txt");
            if (base.createNewFile()) {
              System.out.println("File created: " + base.getName());
            } else {
              System.out.println("File already exists.");
            }

            BufferedReader reader = new BufferedReader(new FileReader("attractions.txt"));

            int lines = 0;
            String test = reader.readLine();
            while (test != null) {
                lines++;
                System.out.println(test.compareTo(lines + ") " + this.name + " " + this.cost));
                if(test.compareTo(lines + ") " + this.name + " " + this.cost) == 0) {
                    throw new IOException();
                }
                test = reader.readLine();
            }

            reader.close();
            lines++;

            BufferedWriter writer = new BufferedWriter(new FileWriter("attractions.txt", true));
            writer.write(lines + ") " + this.name + " " + this.cost + "\n");
            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }

    public void editAttraction(String name, Double cost) {
        try {
            String oldName = this.name;
            Double oldCost = this.cost;

            File base = new File("attractions.txt");
            File temp = new File("temp_attractions.txt");
            if (base.createNewFile() || temp.createNewFile()) {
              System.out.println("File created: " + base.getName() + " " + temp.getName());
            } else {
              System.out.println("File already exists.");
            }
    
            BufferedReader reader = new BufferedReader(new FileReader("attractions.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp_attractions.txt"));
    
            String line1 = reader.readLine();
            String toEdit =  oldName + " " + oldCost;
            int line_number = 1;
            toEdit = line_number + ") " + toEdit;
    
            while (line1 != null) {
              toEdit = toEdit.replaceFirst("[0-99]", "" + line_number);
              if(line1.compareTo(toEdit)==0) {
                String edited = line_number + ") " + name + " " + cost;
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

            this.name = name;
            this.cost = cost;
          } catch (IOException e) {
            System.out.println("An error occurred.");
          } catch (NullPointerException e) {
            System.out.println("End of file.");
          }
    }

    public void deleteAttraction() {
        try {
            File base = new File("attractions.txt");
            File temp = new File("temp_attractions.txt");
            if (base.createNewFile() || temp.createNewFile()) {
              System.out.println("File created: " + base.getName() + " " + temp.getName());
            } else {
              System.out.println("File already exists.");
            }
    
            BufferedReader reader = new BufferedReader(new FileReader("attractions.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp_attractions.txt"));
    
            String line1 = reader.readLine();
            String toDelete =  this.name + " " + this.cost;
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
   
            this.name = null;
            this.cost = null;
          } catch (IOException e) {
            System.out.println("An error occurred.");
          } catch (NullPointerException e) {
            System.out.println("End of file.");
          }
    }    
}
