import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class TodoList {
  ArrayList<Task> task = new ArrayList<Task>();
  private String userName; 
  private Date dateTemp;
  private Date dateTemp1;
  private int size = 20;

  public TodoList(String userName) {
    this.userName = userName;
  }
  public void addTask(Task t) {
    task.add(t);
  }
  public void printAllTasks() {
    for(Task t : task)
      System.out.println(t);
  }
  public Task[] findTask(String findTname) {
    boolean found = false;
    Task[] foundName = new Task[size];
    int j = 0;
    for(int i = 0; i < task.size(); i++) {
      if(task.get(i).getTaskName().contains(findTname)) {
        foundName[j] = task.get(i);
        j++;
        found = true;
      } 
    }
    if(!found)
      System.out.println("No  Matches found");
    return foundName;
  }
  public Task[] getTask(String findTDate) {
    boolean found = false;
    Task[] foundName = new Task[size];
    int j=0;
    String s = "";
    for (int i = 0; i < task.size(); i++) {
      s = task.get(i).getDueDate();
      if(task.get(i).getDueDate() != null) {
        if(s==findTDate) {
          foundName[j] = task.get(i);
          j++;
          found = true;
        }
      }
    }
    if(!found)
      System.out.println("No Matches found");
    return foundName;
  }
  public Task[] getOverdueTasks() {
    boolean found = false;
    Task[] foundName = new Task[size];
    SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    String s = GetCurrentDateTime();
    String s1 = "",s2 = "";
    int j=0,index=0;
    for (int i = 0;i<task.size();i++) {
      s1 = task.get(i).getDueDate();
      if(task.get(i).getDueDate() != null) {
        if(!task.get(i).getMarkAsDone()) {
        try {
          dateTemp = date1.parse(s);
          dateTemp1 = date1.parse(s1);

          if(dateTemp1.before(dateTemp)) {
            long diff = dateTemp.getTime() - dateTemp1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            found = true;
            s2=diffDays + " days: " + diffHours + " hours: " + diffMinutes + " minutes: " + diffSeconds + " seconds: ";
            Task obj=new Task(task.get(i).getTaskName());
            obj.setDueDate(s2);
            foundName[index]=obj;
            index++;
          }
        }
        catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }
  return foundName;
  }
  public Task[] getPendingTasks() {
    Task[] foundName = new Task[size];
    int j = 0;
    for (int i = 0; i < task.size(); i++) {
      if (!task.get(i).getMarkAsDone()) {
        foundName[j] = task.get(i);
        j++;
      }
    }
    return foundName;
  }

  public String GetCurrentDateTime() {
    SimpleDateFormat dateTemp = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date date = new Date();
    return(dateTemp.format(date));
  }
}