class Student {
	public String studentName;
	float gpa;
	String course1;
	String course2;
	int gdPoints;
	int gdPoints2;
	public Student(String studentName) {
		this.studentName = studentName;
		//System.out.println(studentName);
	}
	public String getStudentName() {
		return studentName;
	}
	public float getGPA() {
		//for(int ind = 0;)

		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public String generateTranscript() {
		/* generate transcript and print 
     * Expected Output for s1:
     * Alice has enrolled in 2 courses and obtained the following grades.
     * IT Workshop - 10
     * Intro Programming - 8
     * GPA is 8.67
     */
		return studentName+" has enrolled in 2 courses and obtained the following grades.\n"+course1+"- "+gdPoints+"\n"+course2+" - "+gdPoints2+"\n"+"GPA is "+getGPA();
	}
}