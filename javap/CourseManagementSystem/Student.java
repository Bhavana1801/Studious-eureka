class Student {
	public String studentName;
	String course1;
	String course2;
	double gpa;
	public Student(String studentName) {
		this.studentName = studentName;
		//System.out.println(studentName);
	}
	public String getStudentName() {
		return studentName;
	}
	public double getGPA() {
		//for(int ind = 0;)

		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	// public String[] setDetails() {
	// 	CourseManagementSystem cs = new CourseManagementSystem();
	// 	String list[];
	// 	list = cs.setAllDetails();
	// 	return list;

	// }
	//CourseManagementSystem cs = CourseManagementSystem("IIIT-MSIT");
	public String generateTranscript() {
		/* generate transcript and print 
     * Expected Output for s1:
     * Alice has enrolled in 2 courses and obtained the following grades.
     * IT Workshop - 10
     * Intro Programming - 8
     * GPA is 8.67
     */
		//String list = cs.setAllDetails();
		return studentName+"has enrolled in "+course1+"and obtained the following grades."+"GPA is"+getGPA();
	}
}