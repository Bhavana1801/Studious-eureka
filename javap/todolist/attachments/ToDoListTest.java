public class ToDoListTest {
  public static void main(String[] args) {
    /* The following code creates an object of TodoList and adds tasks */
    
    /* Constructor takes a user's name as input in order to display */
    TodoList list = new TodoList("Praveen");

    /* creates an object of task with the task name */
    /* adds the task to todo list */

    Task t1 = new Task("Buy groceries");
    list.addTask(t1);
    t1.setDueDate("23/10/15 11:00:00");
    t1.markAsDone();

    Task t2 = new Task("Recharge mobile phone");
    list.addTask(t2);
    t2.setDueDate("19/07/15 11:00:00");
    //t2.markAsDone();

    Task t3 = new Task("Send the document on todo list");
    list.addTask(t3);
    t3.setDueDate("20/07/15 11:00:00");
    //t3.markAsDone();

    Task t4 = new Task("Clarify the questions posted on slack");
    list.addTask(t4);
    t4.setDueDate("23/08/15 11:00:00");
    t4.markAsDone();

    Task t5 = new Task("Send a thank you note to Ram");
    list.addTask(t5);
    t5.setDueDate("27/08/15 12:00:00");
    t5.markAsDone();

    Task t6 = new Task("Register for the Airtel 5K run");
    list.addTask(t6);
    t6.setDueDate("29/08/15 01:00:00");
    t6.markAsDone();
    
    Task t7 = new Task("Book movie tickets for Saturday");
    list.addTask(t7);
    t7.setDueDate("27/09/15 12:00:00");
    t7.markAsDone();

    Task t8 = new Task("Install digital proctor for sparks and codeninjas");
    list.addTask(t8);
    t8.setDueDate("27/07/15 02:00:00");
    //t8.markAsDone();   

    Task t9 = new Task("Create the specification for dashboard project");
    list.addTask(t9);
    t9.setDueDate("27/08/15 06:00:00");
    t9.markAsDone();

    Task t10 = new Task("Prepare question paper for assessment 3");
    list.addTask(t10);
    t10.setDueDate("03/11/15 09:00:00");
    t10.markAsDone();

    Task t11 = new Task("Get some sleep!");
    list.addTask(t11);
    t11.setDueDate("07/07/15 12:00:00");
    t11.markAsDone();
    /* print all the tasks with task name */
    System.out.println("-----Print all the tasks that are in the list-----");
    list.printAllTasks();

    /* find all the tasks that match the keyword digital proctor */
    System.out.println("-----search results for digital proctor-----");
    Task[] task1 = list.findTask("digital proctor");
    for (Task ta1 : task1) {
      if(ta1 != null)
       System.out.println(ta1);
    }

    /* Find all the tasks that are over due */
    System.out.println("-----Overdue task list-----");
    Task []task3 = list.getOverdueTasks();
    for (Task ta3 : task3) {
          if(ta3 != null)
            System.out.println(ta3);
    }

  //Find all the tasks that are available on the particular date
    System.out.println("-----Tasks list that match a date-----");
    Task[] task2 = list.getTask("07/07/15 12:00:00");
    for (Task ta2 : task2) {
        if(ta2 != null)
         System.out.println(ta2);
    }

    /*Find all the tasks which are not marked as completed*/
    System.out.println("-----Tasks list that are not done-----");
    Task[] task4 = list.getPendingTasks();
    for (Task ta4 : task4) {
      if(ta4 != null)
        System.out.println(ta4);
    }
  }
}