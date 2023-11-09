package cse.web;

public class Course {
  public String courseId;
  public String courseName;
  public double credit;
  public String teacherName;
  
public Course(String courseId, String courseName, double credit, String teacherName) {
	this.courseId = courseId;
	this.courseName = courseName;
	this.credit=credit;
	this.teacherName=teacherName;
}
  public String getCourseId() {
    return this.courseId;
  }
  public String getCourseName() {
    return this.courseName;
  }
  public double getCredit() {
	    return this.credit;
	  }
  public String getTeacherName() {
	    return this.teacherName;
	  }
  
  

}
