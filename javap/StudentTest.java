class Person {
  Person(String name) { 
    this.name = name; 
  }
  void setAge(int age) { 
    this.age = age; 
  }
  void setGender(Gender gender) { 
    this.gender = gender; 
  }
  void setName(String name) {
   this.name = name; 
 }
  int getAge() { 
    return age; 
  }
  Gender getGender() { 
    return gender; 
  }
  String getName() { 
    return name;
     }
  public String toString() {
    return ("Name: "+ name + ", Age: "+ age + ", Gender: " + gender);
  }
  static enum Gender {M, F}
  private String name;
  private int age;
  private Gender gender;
}
class Student extends Person {
  Student(String name) { 
    super(name); 
  }
  void setCollege(String college) { 
    this.college = college;
    }
  void setGpa(double gpa) { 
    this.gpa = gpa; 
  }
  void setYear(YearInSchool year) {
   this.year = year; 
 }
  String getCollege() { 
    return college;
  }
  double getGpa() {
   return gpa;
 }
  YearInSchool getYear() {
   return year; 
 }
  public String toString() {
  return(super.toString() + "\n " +"College: " + college +", GPA: " + gpa +", Year: " + year);
  }
  static enum YearInSchool { frosh, sophmore, junior, senior}
  private String college = "Unknown";
  private YearInSchool year; // frosh, sophomore, ...
  private double gpa; //0.0 to 4.0
}
class GradStudent extends Student{
  String dept;
  String thesis;
  GradStudent(String dept,String thesis) {
    this.dept = dept;
    this.thesis = thesis;
  }
}
class StudentTest {
  public static void main(String[] args) {
  Student student = new Student("Jane Programmer");
  student.setAge(21);
  student.setGender(Person.Gender.F);
  student.setCollege("UCSC");
  student.setYear(Student.YearInSchool.frosh);
  student.setGpa(3.75f);
  System.out.println(student.toString());
  }
}