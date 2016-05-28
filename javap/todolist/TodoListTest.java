public class TodoListTest {
 public static void main(String[] args) {
   /* The following code creates an object of TodoList and adds tasks */
   
   /* Constructor takes a user's name as input in order to display */
   TodoList list = new TodoList("Praveen");
   /* creates an object of task with the task name */
   Task t1 = new Task("Buy groceries");
   /* adds the task to todo list */
   list.addTask(t1);
   //t1.setDueDate("23/08/15 11:00:00");
   Task t2 = new Task("Recharge mobile phone");
   list.addTask(t2);
   //t2.setDueDate("19/08/15 11:00:00");
   Task t3 = new Task("Send the document on todo list");
   list.addTask(t3);
   //t3.setDueDate("20/08/15 11:00:00");
   Task t4 = new Task("Clarify the questions posted on slack");
   list.addTask(t4);
   Task t5 = new Task("Send a thank you note to Ram");
   list.addTask(t5);
   Task t6 = new Task("Register for the Airtel 5K run");
   list.addTask(t6);
   //t6.setDueDate("21/08/15 11:00:00");
   Task t7 = new Task("Book movie tickets for Saturday");
   list.addTask(t7);
   //t7.setDueDate("19/08/15 11:00:00");
   
   Task t8 = new Task("Install digital proctor for sparks and codeninjas");
   list.addTask(t8);
   //t8.setDueDate("10/08/15 11:00:00");
   Task t9 = new Task("Create the specification for dashboard project");
   list.addTask(t9);
   //t9.setDueDate("8/08/15 11:00:00");
   Task t10 = new Task("Prepare question paper for assessment 3");
   list.addTask(t10);
   //t10.setDueDate("20/08/15 11:00:00");
   Task t11 = new Task("Get some sleep!");
   list.addTask(t11);
   /* print all the tasks with task name */
   System.out.println("-----Print all the tasks that are in the list-----");
   list.printAllTasks();
   /* find all the tasks that match the keyword digital proctor */
   System.out.println("-----search results for digital proctor-----");
   Task[] tasks = list.findTask("digital proctor");
   for (Task t : tasks) {
     System.out.println(t);
   }
   /* find all the tasks that match the keyword digital proctor */
   System.out.println("-----Overdue task list-----");
   tasks = list.getOverdueTasks();
   for (Task t : tasks) {
     System.out.println(t);
   }
   /* find all the tasks that match the given date */
   System.out.println("-----Tasks list that match a date-----");
   tasks = list.getTask("20/08/15 11:00:00");
   for (Task t : tasks) {
     System.out.println(t);
   }
   /* find all the tasks that are pending */
   System.out.println("-----Tasks list that are not done-----");
   tasks = list.getPendingTasks();
   for (Task t : tasks) {
     System.out.println(t);
   }
 }
}