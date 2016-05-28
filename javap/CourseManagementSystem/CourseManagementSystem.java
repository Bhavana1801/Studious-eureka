import java.math.*;
class CourseManagementSystem {
	String pgmName;
	public Course[] courseList = new Course[5];
	public Student[] studentList = new Student[8];
	public Enrollment[] enrollmentList = new Enrollment[15];
	int courseCount;
	int studentCount;
	int enrollCount;
	int flag;
	double points;
	public CourseManagementSystem(String pgmName) {
		this.pgmName = pgmName;
		//courseList = new Course[10];
		courseCount = 0;
		studentCount = 0;
		enrollCount = 0;
		flag = 0;
		points = 0.0;
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
		for(int ind = 0; ind < courseCount; ind++) {
			//System.out.println(ind+"crsc"+courseCount);
			Enrollment temp = new Enrollment(stdName,allCourses[ind],points);
			studentList[0].course1 = allCourses[0].courseName;
			studentList[1].course1 = allCourses[1].courseName;
			enrollmentList[enrollCount] = temp;
		enrollCount++;
		//System.out.println(enrollCount+" count");
		}
	}
	public void awardGrade(Student stdName, Course crscName, int points) {
		//System.out.println(enrollmentList[0]);
		enrollmentList[flag].gradePoints = points;
		//System.out.println(enrollmentList[flag]);
		flag++;
		
	}
	
	public void computeGPA() {
		Student temp[]=new Student[10];
		float gpa;
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
			sum=0;
			temp[ind] = studentList[ind];
			//System.out.println(temp);
			for(int ind1 = 0; ind1 < courseCount; ind1++) {
				cPoints = courseList[ind1].creditPoints;
				gPoints = enrollmentList[ind1].gradePoints;
				var = var+(cPoints *gPoints);
				points1 = points1 +cPoints;
			}
			//gpa = Math.round(var/points1);
			gpa = (float)var/points1;
			temp[ind].setGpa(gpa);
			total = total +gpa;
			//gpa = gpa/10;
		}
	 	//return total;
	 }

	public Enrollment[] getEnrollments() {
		
		return enrollmentList;
	}



	 // public String setAllDetails() {
	 // 	String list="";
	 // 	//int count = courseCount;
	 // 	for(int i = 0; i < courseCount ; i++) {
	 // 		list = courseList[i].courseName+"\n";
	 // 		//list[i] = courseList[i].courseName;
	 // 		System.out.println(".............."+list);
	 // 	}
	 	
	 // 	return list;
	 // }

	// public Enrollment[] getEnrollments() {
		
	// 	return enrollmentList;
	// }

}
