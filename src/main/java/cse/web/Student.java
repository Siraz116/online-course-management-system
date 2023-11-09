package cse.web;

import java.util.ArrayList;

public class Student{
	public String name;
	public String registration;
	public ArrayList<String> courses; // new ArrayList<String>();
	
	Student(String nam,String reg,ArrayList<String> crs){
		this.name=nam;
		this.registration=reg;
		this.courses=crs;
	}
	public void addCourse(String crs) {
		this.courses.add(crs);
	}
	public ArrayList<String> getCourses(){
		return this.courses;
	}
//    std.add("C");
//    std.add("python");
//    std.add("java");
//    std.add("ML");
	
}