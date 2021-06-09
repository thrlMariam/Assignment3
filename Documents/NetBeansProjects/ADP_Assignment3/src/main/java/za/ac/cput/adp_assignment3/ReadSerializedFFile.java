/* Assignment 3
 * Mariam Gallie - 219094837
 */
package za.ac.cput.adp_assignment3;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mariaam
 */
public class ReadSerializedFFile {
    
    private ObjectInputStream input;
    private BufferedWriter mg;
    
    
    Customer customer;
    static ArrayList<Customer> customerArray = new ArrayList<>();
    
    Supplier supplier;
    private ArrayList<Supplier> supplierArray = new ArrayList<>();
 
    Object me;
    
    
    public void openFile(){
        try{
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("*** serialized file opened for reading ***");
        }
        catch(IOException ioe){
            System.out.println("Error opening the serialized file: " + ioe.getMessage());
        }
    }
    public void closeFile(){
        try{
            input.close();
        }
        catch(IOException ioe){
            System.out.println("Error closing the serialized file: " + ioe.getMessage());
        }
    }
    
    public void readFromFile(){
        try{
            while(true){
                
                me=input.readObject();
                if (me.getClass() == Customer.class){
                    customer=(Customer) me;
                    customerArray.add(customer);
                   
                }
                        }
                    
                     
                
                }
                
                                
           
        catch(ClassNotFoundException ioe){
            System.out.println(" Error reading serialized file: " + ioe);
        }
        catch(IOException ioe){
            System.out.println(" Error reading from serialized file: " + ioe);
        }
        
        finally{
            closeFile();
        }   System.out.println("*** file has been closed ***");
      }
    
        public void openFileM(){
        try{
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("*** serialized file opened for reading ***");
        }
        
        catch(IOException ioe){
            System.out.println("Error opening the serialized file: " + ioe.getMessage());
        }
    }

    public void closeFileM(){
        try{
            input.close();
        }
        catch(IOException ioe){
            System.out.println("Error closing the serialized file: " + ioe.getMessage());
        }
    }
    
    public void readFromFileM(){
        try{
            while(true){
                
                 me= input.readObject();
                 if (me.getClass()== Supplier.class){
                     supplier=(Supplier) me;
                   supplierArray.add(supplier);
                   
                   
                 }
                 
                  
                
                }
        }
                                
           
        catch(ClassNotFoundException ioe){
            System.out.println("Error reading serialized file: " + ioe);
        }
        catch(IOException ioe){
            System.out.println("Error reading from serialized file: " + ioe);
        }
        finally{
            closeFileM();
            System.out.println("*** file has been closed ***");
        }
        
        
    }

   // Customer
     public int CalAge(String dob){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.parse(dob,DateTimeFormatter.ISO_LOCAL_DATE);
        return Period.between(date2,date1).getYears();
        
    }
    
   public void openFileG(){
       try{
           mg = new BufferedWriter(new FileWriter("customerOutFile.txt"));
       }
       catch(IOException ioe){
            System.out.println("Error opening the text file: " + ioe.getMessage());
        }
    
   }
        public void closeFileG(){
        try{
            mg.close();
        }
        catch( IOException ioe){
            System.out.println("Error closing the text file: " + ioe.getMessage());
        }
    }
      public void writeToFileG(){
          try{
              int rent= 0;
              
          mg.write("====================Customer==============================================\n");
          
          mg.write(String.format("%-15s%-15s%-15s%-15s%-15s\n","ID","Name", "Surname", "Date of Birth","Age"));
          mg.write("=============================================================================\n");
          
          for ( int i=0; i<customerArray.size(); i++){
             mg.write(String.format("%-15s%-15s%-15s%-15s%-15d\n", customerArray.get(i).getStHolderId(),customerArray.get(i).getFirstName(),customerArray.get(i).getSurName(),customerArray.get(i).getDateOfBirth(),CalAge(customerArray.get(i).getDateOfBirth())));
             if (customerArray.get(i).getCanRent() == true){
                 rent++;
             } 
             
          }  
          mg.write("\nNumber of customers that can rent: " + rent);
          mg.write("\nNumber of customers that cannot rent: " + (customerArray.size() - rent));
                
          }
               catch( IOException ioe){
            System.out.println("Error closing the text file: " + ioe.getMessage());
        }
    }
      // Supplier 
       public void openFileS(){
       try{
           mg = new BufferedWriter(new FileWriter("supplierOutFile.txt"));
       }
       catch(IOException ioe){
            System.out.println("Error opening the text file: " + ioe.getMessage());
        }
    
   
        }
       
   
      public void writeToFileS(){
          try{
              
          mg.write("============================Supplier=====================================\n");
          
          mg.write(String.format("%-15s%-20s%-20s%-15s\n","ID","Name", "Product Type", "Description"));
          mg.write("=============================================================================\n");
          
          for ( int i=0; i<supplierArray.size(); i++){
          mg.write(String.format("%-15s%-20s%-20s%-15s\n", supplierArray.get(i).getStHolderId(),supplierArray.get(i).getName(),supplierArray.get(i).getProductType(),supplierArray.get(i).getProductDescription()));
            
             
          }  
         
                
          }
               catch( IOException ioe){
            System.out.println("Error closing the text file: " + ioe.getMessage());
        }
    } 
   
      public void sortC(){
          Collections.sort(customerArray,(customer1,customer2)->{
              return customer1.getStHolderId().compareTo(customer2.getStHolderId());
                   
          });
      }
    public void sortS(){
          Collections.sort(supplierArray,(supplier1,supplier2)->{
              return supplier1.getName().compareTo(supplier2.getName());
                   
          });
      }
    
 
           
    
    public static void main(String args[]){
        
     ReadSerializedFFile obj = new ReadSerializedFFile();
        
     
        obj.openFile();
        obj.readFromFile();
        obj.openFileM();
        obj.readFromFileM();
      
        obj.sortC();
        obj.openFileG();
        obj.writeToFileG();
        obj.closeFileG();
        
        obj.sortS();
        obj.openFileS();
        obj.writeToFileS();
        obj.closeFileG();
    }   
}

   
   
    

