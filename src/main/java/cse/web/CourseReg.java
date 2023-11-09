package cse.web;

public class CourseReg{
	public String courseId;
	public int studentReg;
	public String studentName;
	
	public CourseReg(String courseId,int studentReg,String studentName) {
	
		this.courseId = courseId;
		this.studentName = studentName;
		this.studentReg=studentReg;
	}
}
