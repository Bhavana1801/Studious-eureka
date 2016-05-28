class Course {
   String courseName;
   int creditPoints;
  public Course(String courseName,int creditPoints) {
    this.courseName = courseName;
    this.creditPoints = creditPoints;
    //System.out.println("y"+this.courseName);
  }
  public String getCourseName() {
    return courseName;
  }
  public int getCreditPoints() {
    return creditPoints;
  }
  // public String toString() {
  //   return "cName: "+courseName + "cpts: "+creditPoints;
  // }
}