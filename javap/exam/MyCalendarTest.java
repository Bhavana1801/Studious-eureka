public class MyCalendarTest {
  public static void main(String[] args) {

    /* Create an object of my calendar with a title */
    MyCalendar calendar = new MyCalendar("Raghu");
  
  // Score and Summary
  int score = 0;
  String summary = "";
  

    /* Slot creation test cases */
    Appointment slot1 = new Appointment("29/08/15 10:00:00", 45);
    boolean flag = calendar.createAppointmentSlot(slot1);

    /* Expected output: 29/08/15 10:00:00 - 45 mins successfully created */
    if(flag){
      //System.out.println(slot1 + " successfully created");
    score = score + 1;
  }
    else {
      //System.out.println(slot1 + " already exists");
    summary = summary + " TestCase #1 Failed! (29/08/15 10:00:00, 45) : True \n";
  }
  
  
  
    Appointment slot2 = new Appointment("29/08/15 10:30:00", 30);
    flag = calendar.createAppointmentSlot(slot2);

    /* Expected output: 29/08/15 10:30:00 - 30 mins cannot be created */
    if(flag){
      //System.out.println(slot2 + "successfully created");
    summary = summary + " TestCase #2 Failed! (29/08/15 10:30:00, 30) : False \n";
  }
    else{
      //System.out.println(slot2 + " cannot be created");
    score = score + 1;
  }
  
  
  
    Appointment slot3 = new Appointment("29/08/15 09:30:00", 45);
    flag = calendar.createAppointmentSlot(slot3);

    /* Expected output: 29/08/15 09:30:00 - 45 mins cannot be created */
    if(flag){
      //System.out.println(slot3 + "successfully created");
    summary = summary + " TestCase #3 Failed! (29/08/15 09:30:00, 45) : False \n";
  }
    else{
      //System.out.println(slot3 + " cannot be created");
    score = score + 1;
  }

  
  
  
    /* Appointment booking test cases */
    /* Shyam wants to book an appointment */
    flag = calendar.bookAppointment("Shyam", "29/08/15 09:45:00", 30, "IIIT-H");
    /* Expected output: Appointment cannot be booked for Shyam */
    if(flag){
      //System.out.println("Appointment successfully booked for Shyam");
    summary = summary + " TestCase #4 Failed! (29/08/15 09:45:00, 30) : False \n";
  }
    else{
      //System.out.println("Appointment cannot be booked for Shyam");
    score = score + 1;
  }

  
    /* Prasad wants to book an appointment */
    flag = calendar.bookAppointment("Prasad", "29/08/15 10:00:00", 15, "IIIT-H");

    /* Expected output: Appointment successfully booked for Prasad */
    if(flag){
      //System.out.println("Appointment successfully booked for Prasad");
    score = score + 1;
  }
    else{
      //System.out.println("Appointment cannot be booked for Prasad");
    summary = summary + " TestCase #5 Failed! (29/08/15 10:00:00, 15) : True \n";
  }
  
  /* Shekhar wants to book an appointment */
    flag = calendar.bookAppointment("Shekhar", "29/08/15 10:00:00", 15, "IIIT-H");

    /* Expected output: Appointment cannot be booked for Shekhar */
    if(flag){
      //System.out.println("Appointment successfully booked for Shekhar");
    summary = summary + " TestCase #6 Failed! (29/08/15 10:00:00, 15) : False \n";
  }
    else{
      //System.out.println("Appointment cannot be booked for Shekhar");
    score = score + 1;
  }

  
    /* Shyam wants to cancel an appointment */
    flag = calendar.cancelAppointment("29/08/15 09:45:00");
    /* Expected output: Appointment cannot be canceled */
    if(flag){
      //System.out.println("Appointment successfully canceled");
    summary = summary + " TestCase #7 Failed! (29/08/15 09:45:00) : False \n";
  }
    else {
      //System.out.println("Appointment cannot be canceled");
    score = score + 1;
  }
  
  /* Prasad wants to cancel an appointment */
    flag = calendar.cancelAppointment("29/08/15 10:00:00");
    /* Expected output: Appointment successfully canceled */
    if(flag){
      //System.out.println("Appointment successfully canceled");
    score = score + 1;   
  }
    else {
      //System.out.println("Appointment cannot be canceled");
    summary = summary + " TestCase #8 Failed! (29/08/15 10:00:00) : True \n";
  }

  System.out.println("{\"score\":"+score+", \"summary\":\""+summary+"\"}");
    
  }
}