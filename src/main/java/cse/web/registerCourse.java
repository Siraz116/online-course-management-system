package cse.web;
import cse.web.Student;
import cse.web.Course;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registerCourse
 */
@WebServlet("/registerCourse")
public class registerCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> crs = new ArrayList<String>();
		ArrayList<Course> courseList = new ArrayList<Course>();
	       crs.add("C");
	       crs.add("python");
	       crs.add("java");
	       crs.add("ML");
	       crs.add("Security");
	       String[] l={"C","python","java","ML"};
			Student st=new Student("abir","108",crs);

		    request.setAttribute("data", st); 
		    request.setAttribute("subjectList",l);
		    RequestDispatcher rd;
		    Connection con=null;
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		    try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
				PreparedStatement pst3=con.prepareStatement("SELECT * FROM courses");
			//	pst3.setString(1,rs.getString("username"));
				ResultSet rs3= pst3.executeQuery();
				
				while (rs3.next()) {
					  String courseId = rs3.getString("courseId");
					  String courseName = rs3.getString("courseName");
					  Double credit =rs3.getDouble("credit");
					  String courseTeacher=rs3.getString("teacherName");
					  
					  Course c=new Course(courseId,courseName,credit,courseTeacher);
					  courseList.add(c);
					}
				
				request.setAttribute("courseList", courseList);
				
			//	rd.forward(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        rd=request.getRequestDispatcher("/registerCourse.jsp");  
        rd.include(request, response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> crs = new ArrayList<String>();
		ArrayList<Course> courseList = new ArrayList<Course>();
		String[] para=request.getParameterValues("courseId");
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("name");
		String registration=(String) session.getAttribute("reg");
		Connection con=null;
		RequestDispatcher rd=null;
	       crs.add("C");
	       crs.add("python");
	       crs.add("java");
	       crs.add("ML");
	       crs.add("Security");
	       String[] l={"C","python","java","ML"};
			Student st=new Student("abir","108",crs);

		    request.setAttribute("data", st); 
		    request.setAttribute("subjectList",l);
		    int rowCount=0,rowCount2=-1,flag=0;
		//    RequestDispatcher rd1;
		    
//		PrintWriter out=response.getWriter();
//		out.print(para[0]+" "+para[1]+" "+para[2]+" "+username);
		    
		    try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
				for(int i=0; i<para.length; i++) {
					PreparedStatement pst=con.
							prepareStatement("insert into course_reg (courseId,student_name,student_reg) VALUES(?,?,?)");
							pst.setString(1,para[i]);
							pst.setString(2,username);
							pst.setString(3,registration);
							
					rowCount = pst.executeUpdate();
					
//					PreparedStatement pst2=con.
//							prepareStatement("SELECT * FROM course_reg 	WHERE courseId=? and student_name=?");
//							pst2.setString(1,para[i]);
//							pst2.setString(2,username);
//							
//					rowCount2 = pst2.executeUpdate();
//					if(flag==0 && rowCount2==0) {
//						
//							flag=1;
//						
//					}
				}
				
				
			//	rd = request.getRequestDispatcher("student.jsp");
				if(rowCount > 0) {
					if(flag==0) {
						request.setAttribute("status","success");
					}
					else {
						request.setAttribute("status","failed");
					}
					
					PreparedStatement pst5=con.prepareStatement("SELECT DISTINCT courses.courseId,courseName,credit,teacherName "
							+ " FROM "
							+ " courses INNER JOIN course_reg "
							+ " ON courses.courseId=course_reg.courseId "
							+ " WHERE student_name=?;");
					pst5.setString(1,username);
					ResultSet rs5= pst5.executeQuery();
					
					while (rs5.next()) {
					String courseId = rs5.getString("courses.courseId");
					String courseName = rs5.getString("courseName");
					Double credit =rs5.getDouble("credit");
					String courseTeacher=rs5.getString("teacherName");
					
					Course c1=new Course(courseId,courseName,credit,courseTeacher);
					courseList.add(c1);
					}
					
					request.setAttribute("courseList", courseList);
					
					
					rd=request.getRequestDispatcher("student.jsp");  
					rd.include(request, response);  
				//	System.out.println(userType);
				}
				else {
					request.setAttribute("status", "failed");
				}
				
			//	rd.forward(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
		  //  doGet(request,response);
		  //  rd.forward(request,response);
		    
		    
		    
		    
	//	rd=request.getRequestDispatcher("student.jsp"); 
	//	rd.forward(request, response);
	}

}
