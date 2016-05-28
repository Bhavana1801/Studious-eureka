class TodoList {
  String name;
  Task[] taskarray;
  int i;
  TodoList(String name) {
    this.name = name;
    i = 0;
  }
  public void addTask(Task temp) {
    taskarray[i] = temp;
  }
  public void printAlltasks() {
    
  }
}