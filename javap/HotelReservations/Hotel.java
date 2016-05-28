/**
  *the Hotel is an ArrayList of Reservations
  *it allows a person to request a room and cancel their reservation
  *also we can add more rooms
*/

import java.util.ArrayList;

public class Hotel{
    //instance variable, ArrayList tracks current reservations
    private ArrayList<Reservation> rooms;

    //constructors, can specify how many rooms to start with
    //default is 5 rooms
    public Hotel(){
  rooms = new ArrayList<Reservation>();
  rooms.ensureCapacity(5);
  for (int i = 0; i < 5; i++)
       rooms.add(null);
    }
    /*
    * @param numRooms- specifies room numbs.
    */
    public Hotel(int numRooms){
  rooms = new ArrayList<Reservation>();
  rooms.ensureCapacity(numRooms);
  for(int i = 0; i< numRooms; i++)
       rooms.add(null);
    }

    //adds more rooms to the hotel, returns true on success
    public boolean buildRooms(int num){
  //make sure the parameter is valid
  if(num<=0)return false;

  //increase the capacity of the Vector
  rooms.ensureCapacity(rooms.size() + num);
  for(int i = 0; i < num; i++)
       rooms.add(null);
  //report succes
  return true;
    }

    //reserves and returns an available room
    //or returns -1 if the hotel is full
    public int reserveRoom(String person){
      boolean res = false;
      int i;
      for(i = 0; i<rooms.size(); i++) {
        if(rooms.get(i) == null) {
          //if the room is empty then a room is reserved with person name and room num.
          Reservation rsvtn = new Reservation(person,i);
          rooms.set(i,rsvtn);
          res = true;
          break;
        }
      }
      //if room is not empty returns -1 
      if(res == false) {
        return -1;
      }
      //else it returns the room number.
      else
        return i;
    }

    //reserves a particular room for this person
    //returns false on failure (eg. room is already reserved)
    public boolean reserveRoom(String person, int roomNum){
      //int i = getRoom();
      int i = roomNum;
      Reservation rsvtn = new Reservation(person,roomNum);
      if(rooms.get(i) == null) {
        rooms.set(i,rsvtn);
        return true;
      }
      else
        return false;
    }

    //cancels all reservations by this person
    public void cancelReservations(String person){
      int i;
      for(i = 0; i<rooms.size();i++) {
        if(rooms.get(i).getName().equals(person)) {
          rooms.set(i,null);
          break;
        }
      }
    }

    //prints out all the current reservations to the screen
    //also should display the total number of reservations and vacancies
    public void printReservations(){
      int totalRooms = 0;
      int i;
      System.out.println("rooms are: "+rooms);
      for(i = 0; i<rooms.size(); i++) {
        if(rooms.get(i) != null) {
          totalRooms++;
        }
        int roomsAvailable;
        roomsAvailable = rooms.size() - totalRooms;
        System.out.println("available rooms: "+roomsAvailable);
        System.out.println("totalRooms filled: "+totalRooms);
      }
    }
}