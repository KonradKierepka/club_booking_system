package club_booking_system;

import java.time.LocalDate;
import java.io.File; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Employee implements Serializable{

    protected String name; 
    protected String surname;
    protected String address;
    protected String id_number;
    protected Integer telephone_number;
    protected double discount;
    protected LocalDate date_of_employment;


    public Employee(String name, String surname, String address, String id_number, Integer telephone_number) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id_number = id_number;
        this.telephone_number = telephone_number;
        this.discount = 0.3;
        this.date_of_employment = LocalDate.now();
        this.saveIntoBase();
    }

    public Employee(String name, String surname, String address, String id_number, Integer telephone_number, LocalDate date) {
      this.name = name;
      this.surname = surname;
      this.address = address;
      this.id_number = id_number;
      this.telephone_number = telephone_number;
      this.discount = 0.3;
      this.date_of_employment = date;
      this.saveIntoBase();
  }

    private void saveIntoBase() {
        try {
            File base = new File("employees.txt");
            if (base.createNewFile()) {
              System.out.println("File created: " + base.getName());
            } else {
              System.out.println("File already exists.");
            }

            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));

            int lines = 0;
            String test = reader.readLine();
            while (test != null) {
              lines++;
              if(test.compareTo(lines + ") " + this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment) == 0) {
                 throw new IOException();
              }
              test = reader.readLine();
            }
            
            reader.close();
            lines++;

            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
            writer.write(lines + ") " + this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment + "\n");
            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }

    public static Employee findInBase(String id) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
  
        String line1 = reader.readLine();
        String[] words; 
  
        while (line1 != null) {
          words = line1.split(" ");
          System.out.println(words[4]);
          if(words[4].equals(id)) {
            reader.close();
            return new Employee(words[1], words[2], words[3], words[4], Integer.parseInt(words[5]), LocalDate.parse(words[7]));
          }
          line1 = reader.readLine();
        }
  
        reader.close();
        throw new IOException();
  
      } catch (IOException e) {
        System.out.println("Not found!");
      } catch (NullPointerException e) {
        System.out.println("End of file.");
      }
      return null;
    }

    public void deleteEmployee() {
      try {
        File base = new File("employees.txt");
        File temp = new File("temp_employees.txt");
        if (base.createNewFile() || temp.createNewFile()) {
          System.out.println("File created: " + base.getName() + " " + temp.getName());
        } else {
          System.out.println("File already exists.");
        }

        BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp_employees.txt"));

        String line1 = reader.readLine();
        String toDelete =  this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment;
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

        this.address = null;
        this.date_of_employment = null;
        this.discount = 0.0;
        this.id_number = null;
        this.name = null;
        this.surname = null;
        this.telephone_number = null;
      } catch (IOException e) {
        System.out.println("An error occurred.");
      } catch (NullPointerException e) {
        System.out.println("End of file.");
      }
    }


    public String getName() {
      return this.name;
    }

    public String getSurname() {
      return this.surname;
    }

    public String getAddress() {
      return this.address;
    }

    public String getId_number() {
      return this.id_number;
    }

    public Integer getTelephone_number() {
      return this.telephone_number;
    }

    public double getDiscount() {
      return this.discount;
    }

    public LocalDate getDate_of_employment() {
      return this.date_of_employment;
    }

}
 