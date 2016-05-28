import java.math.*;
class CourseManagementSystem {
	String pgmName;
	public Course[] courseList = new Course[5];
	public Student[] studentList = new Student[8];
	public Enrollment[] enrollmentList = new Enrollment[15];
	int courseCount;
	int studentCount;
	int enrollCount;
	int flag,count = 0;
	float points;
	public CourseManagementSystem(String pgmName) {
		this.pgmName = pgmName;
		//courseList = new Course[10];
		courseCount = 0;
		studentCount = 0;
		enrollCount = 0;
		flag = 0;
		points = 0.0f;
		//System.out.println(this.pgmName);
	}
	public void addCourse(Course courseName) {
		courseList[courseCount] = courseName;
		//System.out.println(courseName);
		//System.out.println(count);
		//System.out.println("see"+courseList[count]);
		courseCount++;
		

	}
	public void addStudent(Student studentName) {
		studentList[studentCount] = studentName;
		//System.out.println("see"+studentList[studentCount].getStudentName());
		studentCount++;
	}
	public void enroll(Student stdName, Course allCourses[]) {
		//enrollmentList = new Enrollment[studentCount*courseCount];
		int points = 0;
		for(int ind = 0; ind < studentCount;ind++) {
			studentList[ind].course1 = allCourses[0].courseName;
			studentList[ind].course2 = allCourses[1].courseName;
			studentList[ind].gdPoints = points;
			studentList[ind].gdPoints2 = points;

		}
		for(int ind = 0; ind < courseCount; ind++) {
			Enrollment temp = new Enrollment(stdName,allCourses[ind],points);
			enrollmentList[enrollCount] = temp;
		enrollCount++;
		}
	}
	public void awardGrade(Student stdName, Course crscName, int points) {
		enrollmentList[flag].gradePoints = points;
		// for(int ind = 0; ind < studentCount ;ind ++) {
		// 	studentList[ind].gdPoints = enrollmentList[ind].gradePoints;
		// 	studentList[ind].gdPoints2 = enrollmentList[ind+1].gradePoints;
		// }
		flag++;
	}
	public void computeGPA() {
		Student temp[]=new Student[10];
		float gpa=0.0f;
		float total=0.0f;
		int var=0;
		float sum = 0;
		int points1=0,points2=0;
		int gPoints = 0, cPoints = 0;
		for(int ind = 0; ind < studentCount; ind++) {
			cPoints=0;
			gPoints =0;
			total =0.0f;
			var=0;
			sum=0.0f;
			temp[ind] = studentList[ind];
			for(int ind1 = 0; ind1 < courseCount; ind1++) {
				cPoints = courseList[ind1].creditPoints;
				gPoints = enrollmentList[ind1].gradePoints;
				var = var+(cPoints *gPoints);
				points1 = points1 +cPoints;
			}
			gpa = (float)var/points1;
			temp[ind].setGpa(gpa);
			total = total +gpa;
			//  gpa = (double)var/points1;
			//  //System.out.println(gpa+".........");
			//  int temp1 = (int)(gpa*100);
			//  gpa = temp1/100;
			// temp[ind].setGpa(gpa);
			// total = total +gpa;
		}
	 }

	public Enrollment[] getEnrollments() {
		
		return enrollmentList;
	}


}