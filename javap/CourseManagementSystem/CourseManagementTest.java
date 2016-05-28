public class CourseManagementTest {

  public static void main(String[] args) {

    CourseManagementSystem cms = new CourseManagementSystem("IIIT-MSIT");

    /* create courses */
    Course c1 = new Course("IT Workshop", 2);
    Course c2 = new Course("Intro Programming", 4);
    //System.out.println(c1.getCourseName()+" "+c2.getCourseName());
    cms.addCourse(c1);
    cms.addCourse(c2);

    /* create students */
    Student s1 = new Student("Alice");
    Student s2 = new Student("Bob");
    Student s3 = new Student("Charlie");
    Student s4 = new Student("Dexter");
    Student s5 = new Student("Emma");
    cms.addStudent(s1);
    cms.addStudent(s2);
    cms.addStudent(s3);
    cms.addStudent(s4);
    cms.addStudent(s5);

    /* enroll students */
    Course[] c = new Course[2];
    c[0] = c1;
    c[1] = c2;

    /* pass an array of course objects
     * enroll the student into every course */
    cms.enroll(s1, c);
    cms.enroll(s2, c);
    cms.enroll(s3, c);
    cms.enroll(s4, c);
    cms.enroll(s5, c);

    /*
     * Get the enrolled courses in the same order of enrollement
     */
    Enrollment[] courses = cms.getEnrollments();

    /* award grade points */
    cms.awardGrade(s1, c[0], 10);
    cms.awardGrade(s1, c[1], 8);
    cms.awardGrade(s2, c[0], 0);
    cms.awardGrade(s2, c[1], 6);
    cms.awardGrade(s3, c[0], 8);
    cms.awardGrade(s3, c[1], 8);
    cms.awardGrade(s4, c[0], 7);
    cms.awardGrade(s4, c[1], 9);
    cms.awardGrade(s5, c[0], 10);
    cms.awardGrade(s5, c[1], 10);

    /* compute GPA should compute the GPA for all the students 
     * Set the calculated GPA to the private instance variable of Student
     */
    cms.computeGPA();

    /* get GPA and print 
     * Expected Output for s1:
     * 8.67
     */
    System.out.println(s1.getGPA());
    System.out.println(s2.getGPA());
    System.out.println(s3.getGPA());
    System.out.println(s4.getGPA());
    System.out.println(s5.getGPA());

    /* generate transcript and print 
     * Expected Output for s1:
     * Alice has enrolled in 2 courses and obtained the following grades.
     * IT Workshop - 10
     * Intro Programming - 8
     * GPA is 8.67
     */
    System.out.println(s1.generateTranscript());
    System.out.println(s2.generateTranscript());
    System.out.println(s3.generateTranscript());
    System.out.println(s4.generateTranscript());
    System.out.println(s5.generateTranscript());
  }
}