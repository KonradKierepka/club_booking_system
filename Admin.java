package club_booking_system;

import java.util.Random;

import java.io.File; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class Admin extends Employee implements Serializable{

    private String password;
    private String key;

    public Admin(String imie, String nazwisko, String adres, String nr_dowodu, Integer nr_telefonu, String password) {
        super(imie, nazwisko, adres, nr_dowodu, nr_telefonu);
        this.key = generateKey(password);
        this.password = encryptPassword(password, key);
        this.saveIntoAdminBase();
    }

    private Admin(String imie, String nazwisko, String adres, String nr_dowodu, Integer nr_telefonu, String password, String key, LocalDate date){
      super(imie, nazwisko, adres, nr_dowodu, nr_telefonu, date);
        this.key = key;
        this.password = password;
        this.saveIntoAdminBase();
    }
    
    private String encryptPassword(String password, String key) {
        String encrypted = new String("");
        for(byte i = 0; i < password.length(); i++) {
            encrypted = encrypted + (password.charAt(i) ^ key.charAt(i)) + " ";
        }
        
        System.out.println(encrypted);

        return encrypted;
    }

    private static String decryptPassword(String password, String key) {
        System.out.println(password);
        String decrypted = new String("");
        String[] passwordWords = password.split(" "); 
        for(byte i = 0; i < key.length(); i++) {
            decrypted = decrypted + (char)(Integer.valueOf(passwordWords[i]) ^ key.charAt(i));
        }

        return decrypted;
    }

    /*
        Generate Key for xor encryption algorythm.
    */

    private String generateKey(String password) {
        String key = new String("");
        Random rand = new Random();
        for(byte i = 0; i<password.length(); i++) {
            key = key + (char)(rand.nextInt((126 - 32) + 1) + 32);
        }

        System.out.println(key);

        return key;
    }

    public String getPassword() {
        String pass = decryptPassword(password, key);
        return pass;
    }

    public static String checkPassword(String password, String key) {
        String pass = decryptPassword(password, key);
        return pass;
    }

    private void saveIntoAdminBase() {
        try {
            File base = new File("admins.txt");
            if (base.createNewFile()) {
              System.out.println("File created: " + base.getName());
            } else {
              System.out.println("File already exists.");
            }

            BufferedReader reader = new BufferedReader(new FileReader("admins.txt"));

            int lines = 0;
            String test = reader.readLine();
            while (test != null) {
                lines++;
                if(test.compareTo(lines + ") " + this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment + " : " + this.password + " : key: " + this.key) == 0) {
                    throw new IOException();
                }
                test = reader.readLine();
            }

            reader.close();
            lines++;

            BufferedWriter writer = new BufferedWriter(new FileWriter("admins.txt", true));
            writer.write(lines + ") " + this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment + " : " + this.password + " : key: " + this.key + "\n");
            writer.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }

    @Override
    public void deleteEmployee() {
        super.deleteEmployee();
        try {
          File base = new File("admins.txt");
          File temp = new File("temp_admins.txt");
          if (base.createNewFile() || temp.createNewFile()) {
            System.out.println("File created: " + base.getName() + " " + temp.getName());
          } else {
            System.out.println("File already exists.");
          }
  
          BufferedReader reader = new BufferedReader(new FileReader("admins.txt"));
          BufferedWriter writer = new BufferedWriter(new FileWriter("temp_admins.txt"));
  
          String line1 = reader.readLine();
          String toDelete =   this.name + " " + this.surname + " " + this.address + " " + this.id_number + " " + this.telephone_number + " " + this.discount + " " + this.date_of_employment + " : " + this.password + " : key: " + this.key;
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

          this.id_number = null;
          this.name = null;
          this.surname = null;
          this.telephone_number = null;
          this.key = null;
          this.password = null;
          this.address = null;
          this.date_of_employment = null;
          this.discount = 0.0;
        } catch (IOException e) {
          System.out.println("An error occurred.");
        } catch (NullPointerException e) {
          System.out.println("End of file.");
        }
      }

      public static Admin findInBase(String id, String password) {
        try {
          BufferedReader reader = new BufferedReader(new FileReader("admins.txt"));
    
          String line1 = reader.readLine();
          String[] words; 
          String[] key; 
          String[] password_check; 
    
          while (line1 != null) {
            words = line1.split(" ");
            key = line1.split("key: ");
            password_check = line1.split(" : ");
            if(words[4].equals(id) && password.equals(checkPassword(password_check[1], key[1]))) {
              reader.close();
              return new Admin(words[1], words[2], words[3], words[4], Integer.parseInt(words[5]), password, key[1], LocalDate.parse(words[7]));
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

    public String getKey() {
      return this.key;
    }
}
