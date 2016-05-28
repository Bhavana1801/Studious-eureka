//import java.util.*;
//import java.text.*;
public class Task {
  private String taskName;
  private String dueDate;
  private boolean markAtask;
  public Task(String taskName) {
    this.taskName = taskName;
  }
  public void setDueDate(String date) {
    dueDate = date;
  }
  public void markAsDone() {
    markAtask = true;
  }
  public String getTaskName() {
    return taskName;
  }
  public String getDueDate() {
    return dueDate;
  }
  public boolean getMarkAsDone() {
    return markAtask;
  }
  public String toString() {
    String display = "";
    display = display + taskName;
    if(dueDate!= null)
     display = display + " " + dueDate; 
    return display;
  }
}