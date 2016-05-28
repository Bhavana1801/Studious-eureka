/*
  a driver/test program for the Hotel class
  it instantiates a Hotel and uses the member functions
*/

import java.io.*;

public class Simulation{
    private static String readLine(){
  //because I want to use readLine...
  BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

  try{
      return stdin.readLine();
  }catch(Exception e){}

  return null;
    }

    public static void main(String[] args){
  //instantiate a Hotel
  Hotel h = new Hotel();

  //temp variables we'll need later
  String name, num;
  int roomnum;

  //main loop
  while(true){
      //display prompt
      System.out.print("Command: ");
      System.out.flush();

      //get input
      String command = readLine();

      //user want to quit...
      if((command == null) || command.equalsIgnoreCase("quit")){
    System.out.println("Good bye");
    break;
      }

      //reserve some room
      if(command.equalsIgnoreCase("reserve")){
    //prompt for name
    System.out.print("Name: ");
    System.out.flush();
    name = readLine();

    roomnum = -1;
    //if we got a good name, try to reserve a room
    if(!((name==null) || (name.equals(""))))
        roomnum = h.reserveRoom(name);

    //give feedback
    if(roomnum==-1)
        System.out.println("No reservation for you!");
    else
        System.out.println(name+" reserved room "+roomnum);
      }


      //reserve a particular room
      if(command.equalsIgnoreCase("reserveN")){
    //prompt for name and room
    System.out.print("Name: ");
    System.out.flush();
    name = readLine();
    System.out.print("Room number: ");
    System.out.flush();
    num = readLine();

    //if we got a bad input, report failure
    if((name==null) || (name.equals("")) || (num==null))
        roomnum = -1;
    else{
        //convert the String to an int (and catch any failures)
        try{
      roomnum = Integer.parseInt(num);
        }catch(Exception e){
      roomnum = -1;
        }
    }

    //give feedback
    if(!h.reserveRoom(name, roomnum))
        System.out.println("No reservation for you!");
    else
        System.out.println(name+" reserved room "+roomnum);
      }

      //cancel someone's reservation(s)
      else if(command.equalsIgnoreCase("cancel")){
    //prompt for name
    System.out.print("Name: ");
    System.out.flush();
    name = readLine();

    //if we got a good name, cancel their reservations
    if(!((name==null) || (name.equals("")))){
        h.cancelReservations(name);
        System.out.println(name+" now has no reservations.");
    }else{
        System.out.println("Who?");
    }
      }

      //print out all reservations
      else if(command.equalsIgnoreCase("print")){
    h.printReservations();
      }

      //build more rooms
      else if(command.equalsIgnoreCase("build")){
    //prompt for the number
    System.out.print("How many: ");
    System.out.flush();
    num = readLine();

    //convert to an int
    roomnum = -1;
    if(num!=null){
        try{
      roomnum = Integer.parseInt(num);
        }catch(Exception e){
      roomnum = -1;
        }
    }

    //add the rooms
    if(h.buildRooms(roomnum))
        System.out.println("Added "+roomnum+" more rooms");
    else
        System.out.println("No more rooms for you");
      }


      //invalid command... display available commands
      else{
    System.out.println("Available commands:");
    System.out.println("print - show all reservations");
    System.out.println("reserve - reserve a room for someone");
    System.out.println("reserveN - reserve a particular room");
    System.out.println("cancel - cancel someone's reservations");
    System.out.println("build - build some more rooms for the hotel");
      }
  }
    }
}