class Enrollment {
	String studentName;
	String courseName;
	int gradePoints;
	Enrollment() {

	}
	Enrollment(Student studentName,Course courseName, int gradePoints) {
		this.studentName = studentName.studentName;
		this.courseName = courseName.courseName;
		this.gradePoints = gradePoints;
	}
	public String toString() {
		return "see"+studentName + ","+courseName+","+gradePoints;
	}
	

}