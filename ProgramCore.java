

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ProgramCore extends JFrame{
    //create hashmap
    HashMap<Integer, Property> map = new HashMap<>(); 

            //ProgramCore variables
            public JFrame frame;
            int transactionNum;
            String address;
            int bedrooms;
            int squareFootage;
            int price;
            String process;
            int statusInt;
            boolean duplicateFound;
            
            //strings for combo boxes
           String[] processTypeList = {"Insert", "Delete", "Find"};
           String[] statusTypeList = {"FOR_SALE", "UNDER_CONTRACT", "SOLD"};
           //String[] fillTypeList = {"Solid", "Hollow"};
           
           //variables for labels
           public  JLabel transactionL = new JLabel("Transaction NO:");
           private JLabel addressL = new JLabel("Address:");
           private JLabel bedroomsL = new JLabel("Bedrooms:");
           private JLabel squareFootageL = new JLabel("Square Footage:");
           private JLabel priceL = new JLabel("Price:");
           
           
           //variables for combo boxes
           private JComboBox processTypeCB = new JComboBox(processTypeList);
           private JComboBox statusTypeCB = new JComboBox(statusTypeList);
           //private JComboBox colorCB = new JComboBox(colorTypeList);
           
           //varaibles for text fields
           public JTextField transactionTF = new JTextField(12);
           private JTextField addressTF = new JTextField(12);
           private JTextField bedroomsTF = new JTextField(12);
           private JTextField squareFootageTF = new JTextField(12);
           private JTextField priceTF = new JTextField(12);
           
           //variable for draw button
           private JButton processB = new JButton("Process");
           private JButton changeStatusB = new JButton("Change Status");
           
           
           public void gui() {
               setLayout(null);
               
             
                //select index methods for combo boxes

                processTypeCB.setSelectedIndex(3);
                statusTypeCB.setSelectedIndex(3);


           }

           //sets the bounds of all the labels, text fields, buttons and boxes
           public void setAllBounds() {
                //set bounds methods
                transactionL.setBounds(10, 10, 100, 20);
                addressL.setBounds(10, 40, 100, 20);
                bedroomsL.setBounds(10, 70, 100, 20);
                squareFootageL.setBounds(10, 100, 100, 20);
                priceL.setBounds(10, 130, 100, 20);
                //xCoordinateL.setBounds(10, 160, 100, 20);
                //yCoordinateL.setBounds(10, 190, 100, 20);
                transactionTF.setBounds(200, 10, 120, 20);
                addressTF.setBounds(200, 40, 100, 20);
                bedroomsTF.setBounds(200, 70, 100, 20); 
                squareFootageTF.setBounds(200, 100, 100, 20);
                priceTF.setBounds(200, 130, 100, 20);
                processTypeCB.setBounds(200, 160, 100, 20);
                statusTypeCB.setBounds(200, 190, 100, 20);
                
                processB.setBounds(50, 160, 100, 20);
                changeStatusB.setBounds(50, 190, 100, 20);
               
             }
           
           //adds everything to the gui
           public void addsTextFields() {
               frame.add(transactionTF);
               frame.add(addressTF);
               frame.add(bedroomsTF);
               frame.add(squareFootageTF);
               frame.add(priceTF);
               frame.add(processTypeCB);
               frame.add(statusTypeCB);
               frame.add(processB);
               frame.add(changeStatusB);
           }
           
           //message pane
           public void optionPane(String message) {
               JOptionPane.showMessageDialog(frame, message);
               
           }
           //checks transaction number
           public void transactionNumCheck(int tNum) {
               if(tNum == transactionNum) {
                   
                   duplicateFound = true;
               }
               
           }
           //ads action listener for process button
           public void addActionListener() {
             processB.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){

                 try {
                        transactionNum = Integer.parseInt(transactionTF.getText());
                        address = addressTF.getText();
                        bedrooms = Integer.parseInt(bedroomsTF.getText());
                        squareFootage = Integer.parseInt(squareFootageTF.getText());
                        price = Integer.parseInt(priceTF.getText());
                 }
                 catch(NumberFormatException e2){
                     System.out.println("Number format exception");
                     optionPane("Error: Wrong format for one or more of the text fields");
                     return;
                 }
                 //conversion variables
                 if(processTypeCB.getSelectedItem() == processTypeList[0]) {
                        //loop through db
                        //compare transactionNum to db transactionNum
                        //if true call option pane error
                        //then return
                        //if false then continue
                        duplicateFound = false;
                        map.forEach((k,v) -> transactionNumCheck(k));
                        if (duplicateFound == true) {
                            optionPane("Property already exists");
                            return;
                        }
                        Property newProp = new Property(address, bedrooms, squareFootage, price, statusInt);
                        map.put(transactionNum, newProp);
                        printMap();
                 }
                 else if(processTypeCB.getSelectedItem() == processTypeList[1]) {
                     duplicateFound = false;
                     map.forEach((k,v) -> transactionNumCheck(k));
                     if (duplicateFound == false){
                         optionPane("transaction number not found");
                         return;
                     }
                     map.remove(transactionNum);
                     printMap();
                     
                 }
                 else if(processTypeCB.getSelectedItem() == processTypeList[2]) {
                     duplicateFound = false;
                     map.forEach((k,v) -> transactionNumCheck(k));
                     if (duplicateFound == false){
                         optionPane("transaction number not found");
                         return;
                     }
                     
                     JOptionPane.showMessageDialog(null, map.get(transactionNum));
                 }
                 process = processTypeCB.getSelectedItem().toString();
                 //status = statusTypeCB.getSelectedItem();
                 
                     
             }
         });
             //adds action listener for change status button
             changeStatusB.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 
                 transactionNum = Integer.parseInt(transactionTF.getText());
                 
                 
                 //conversion variables
                 if(statusTypeCB.getSelectedItem() == statusTypeList[0]) {
                     Property prop = map.get(transactionNum);
                     prop.changeStatus(0);
                     printMap();
                 }
                 else if(statusTypeCB.getSelectedItem() == statusTypeList[1]) {
                     Property prop = map.get(transactionNum);
                     prop.changeStatus(1);
                     printMap();
                 }
                 else if(statusTypeCB.getSelectedItem() == statusTypeList[2]) {
                     Property prop = map.get(transactionNum);
                     prop.changeStatus(2);
                     printMap();
                 }
             }
         });
           }
           
           //method that prints the hashmap
           public void printMap() {
             map.forEach((k,v) -> System.out.println(k + " - " + v));
             System.out.println("\n");

           }
           
        //reads file into hashmap
       public void readFile() {
           FileInputStream in = null;
        BufferedReader inputStream = null;
        
        Property[] propArray = new Property[7];
        String line;
         int i = 0;
         
        try {
            FileReader inputValues = new FileReader("C:\\PropertyInfo.txt"); 
            BufferedReader holdValues = new BufferedReader(inputValues);
            line = holdValues.readLine(); //1st line in the file
            //every time you say readLine, it returns the next line

            
            //while loop to iterate through file
          while (line != null) {

            String[] values = line.split(":");
            int hashNum = Integer.parseInt(values[0]); 
            
            String[] properties = values[1].split(",");
            
            
            Property prop = new Property(properties[0], Integer.parseInt(properties[1].trim()), Integer.parseInt(properties[2].trim()), Integer.parseInt(properties[3].trim()), Integer.parseInt(properties[4].trim())); //make your Property object
            propArray[i] = prop;
            i++;
            line = holdValues.readLine(); 
            
            map.put(hashNum, prop); 
          }
 
          }catch (IOException io) {
            System.out.println("File IO exception; " +io.getMessage());

        } finally {

            try {
             
                if(in!=null) {
                    in.close();
                }// end  if(in!=null)

                if(inputStream!=null) {
                    inputStream.close();
                }// end if(inputStream!=null)

            } catch(IOException io) {

                System.out.println("Issue closing the files" +io.getMessage());
            }// catch(IOException io)

        }// end finally
        System.out.println(propArray[0]);
       }
           
    //main method
    public static void main(String[] args) throws IOException{
        
        ProgramCore pc = new ProgramCore();
        
        //pc.readFile();
        //System.out.println("==");
        pc.map.toString();
        pc.frame = new JFrame("properties");
        
        pc.frame.setSize(600, 400);
        //frame.add(gui);
        
        pc.frame.setVisible(true);
        
        pc.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //adds everything to the frame
        pc.addsTextFields();
        pc.frame.add(pc.transactionL);
        pc.frame.add(pc.addressL);
        pc.frame.add(pc.bedroomsL);
        pc.frame.add(pc.priceL);
        pc.frame.add(pc.squareFootageL);
        pc.frame.add(new JLabel(""));
        pc.setAllBounds();
        pc.addActionListener();
        pc.printMap();
        
        
    }
}
    
    
