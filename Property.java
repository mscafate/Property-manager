
/**
 * Author: Michael Scafate
 * Date: 3/8/2020
 * Purpose: Create a GUI that can create and populate a HashMap with different properties that are
 * identifiable by their hash key. 
 */

    
    enum Status {
    FOR_SALE,   //final variables
    UNDER_CONTRACT,
    SOLD
}


//property class
public class Property implements StatusChangeable {
    
    //property variables
    private String propertyAddress;
    private int numRooms;
    private int squareFootage;
    private int price;
    private Status status;
    
    //property constructor
    public Property(String propertyAddress, int numRooms, int squareFootage, int price, int statusInt){
        this.propertyAddress = propertyAddress;
        this.numRooms = numRooms;
        this.squareFootage = squareFootage;
        this.price = price;
        
        if(statusInt == 0){
            
        status = Status.FOR_SALE;
        }
        else if(statusInt == 1) {
            status = Status.UNDER_CONTRACT;
        }
        else if(statusInt == 2) {
            status = Status.SOLD;
        }
    }
    
    //change status method
    public void changeStatus(int statusInt){
      if(statusInt == 0){
            
        status = Status.FOR_SALE;
        }
        else if(statusInt == 1) {
            status = Status.UNDER_CONTRACT;
        }
        else if(statusInt == 2) {
            status = Status.SOLD;
        }
    }
    
    public String toString(){
        //TO DO
        return "Address: " + propertyAddress + "  Number of Rooms: " + numRooms + "  Square Footage: " + squareFootage + "  Price: " + price + "  Status: " + status;
    }
    
    
    
}
    
