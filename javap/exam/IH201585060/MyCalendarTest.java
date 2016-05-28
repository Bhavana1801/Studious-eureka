public class MyCalendarTest {
  public static void main(String[] args) {

    /* Create an object of my calendar with a title */
    MyCalendar calendar = new MyCalendar("Praveen");

    /* Slot creation test cases */
    Appointment slot1 = new Appointment("28/08/15 09:00:00", 30);
    boolean flag = calendar.createAppointmentSlot(slot1);

    /* Expected output: 28/08/15 09:00:00 - 30 mins successfully created */
    if(flag)
      System.out.println(slot1 + " successfully created");
    else
      System.out.println(slot1 + " already exists");

    Appointment slot2 = new Appointment("28/08/15 09:15:00", 45);
    flag = calendar.createAppointmentSlot(slot2);

    /* Expected output: 28/08/15 09:15:00 - 45 mins cannot be created */
    if(flag)
      System.out.println(slot2 + "successfully created");
    else
      System.out.println(slot2 + " cannot be created");

    Appointment slot3 = new Appointment("28/08/15 11:00:00", 45);
    flag = calendar.createAppointmentSlot(slot3);

    /* Expected output: 28/08/15 11:00:00 - 45 mins successfully created */
    if(flag)
      System.out.println(slot3 + " successfully created");
    else
      System.out.println(slot3 + " cannot be created");

    /* Appointment booking test cases */

    /* Ram wants to book an appointment */
    flag = calendar.bookAppointment("Ram", "28/08/15 09:00:00", 30, "IIIT-H");
    calendar.print();
    /* Expected output: Appointment successfully booked for Ram */
    if(flag)
      System.out.println("Appointment successfully booked for Ram");
    else
      System.out.println("Appointment cannot be booked for Ram");

    /* Shyam wants to book an appointment */
    flag = calendar.bookAppointment("Shyam", "28/08/15 09:20:00", 30, "IIIT-H");

    /* Expected output: Appointment cannot be booked for Shyam */
    if(flag)
      System.out.println("Appointment successfully booked for Shyam");
    else
      System.out.println("Appointment cannot be booked for Shyam");

    /* Ram wants to cancel an appointment */
    flag = calendar.cancelAppointment("28/08/15 09:00:00");

    /* Expected output: Appointment successfully canceled */
    if(flag)
      System.out.println("Appointment successfully canceled");
    else
      System.out.println("Appointment cannot be canceled");

    /* Ram wants to rebook an appointment */
    flag = calendar.bookAppointment("Ram", "28/08/15 11:00:00", 30, "IIIT-H");

    /* Expected output: Appointment successfully booked for Ram */
    if(flag)
      System.out.println("Appointment successfully booked for Ram");
    else
      System.out.println("Appointment cannot be booked for Ram");

    /* Shyam wants to book an appointment */
    flag = calendar.bookAppointment("Shyam", "28/08/15 09:00:00", 20, "IIIT-H");

    /* Expected output: Appointment successfully booked for Shyam */
    if(flag)
      System.out.println("Appointment successfully booked for Shyam");
    else
      System.out.println("Appointment cannot be booked for Shyam");
  }
}