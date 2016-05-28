/*
  Reservation class, stores the person and room number in the Hotel
*/

public class Reservation{
    //instance variables
   //private String name;
    private String person;
    private int roomNumber;
    //constructors, must supply the name, and optionally a room
    public Reservation(String person){
      this.person = person;  
    }
    public Reservation(String person, int roomNumber){
      this.person = person;
      this.roomNumber = roomNumber;
    }

    //mutators, set the room number or name
    public void setRoom(int newroom){
       roomNumber = newroom;
    }
    public void setName(String newname){
        
    }

    //accessors, return the room number or name
    public int getRoom(){
        return roomNumber;
    }
    public String getName(){
        return person;
    }
}