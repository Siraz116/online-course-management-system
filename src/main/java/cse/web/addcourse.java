package cse.web;

import java.io.IOException;
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
import cse.web.Course;

/**
 * Servlet implementation class addcourse
 */
@WebServlet("/addcourse")
public class addcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseId= request.getParameter("course-id");
		String courseName= request.getParameter("course-name");
		String credit= request.getParameter("course-credit");
		String teacherName= request.getParameter("course-teacher");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<User> teacherList = new ArrayList<User>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
			PreparedStatement pst=con.
					prepareStatement("insert into courses (courseId,courseName,credit,teacherName) VALUES(?,?,?,?)");
					pst.setString(1,courseId);
					pst.setString(2,courseName);
					pst.setString(3,credit);
					pst.setString(4,teacherName);
					
			int rowCount = pst.executeUpdate();
		//	dispatcher = request.getRequestDispatcher("admin.jsp");
			if(rowCount > 0) {
				request.setAttribute("status","success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			
		//	dispatcher.forward(request,response);
		//	response.sendRedirect("./onlineCourse");  
			
			PreparedStatement pst1=con.prepareStatement("SELECT * FROM courses");
			ResultSet rs1= pst1.executeQuery();
			
			while (rs1.next()) {
				  String courseId1 = rs1.getString("courseId");
				  String courseName1 = rs1.getString("courseName");
				  Double credit1 =rs1.getDouble("credit");
				  String courseTeacher1=rs1.getString("teacherName");
				  
				  Course c1=new Course(courseId1,courseName1,credit1,courseTeacher1);
				  courseList.add(c1);
				}
			
			request.setAttribute("courseList", courseList);
			
			
			PreparedStatement pst2=con.prepareStatement("SELECT * FROM users WHERE role = 'Teacher' ");
//			pst2.setString(1,"Teacher");
			ResultSet rs2= pst2.executeQuery();
			while (rs2.next()) {
				  String teacherEmail=rs2.getString("email");
				  String teacherName2=rs2.getString("username");
				  int teacherRegistraion=rs2.getInt("registration");
				  String passwordT=rs2.getString("password");
				  String role="Teacher";
				  
				  User t=new User(teacherEmail,teacherName2,teacherRegistraion,passwordT,role);
				  teacherList.add(t);
				}
			request.setAttribute("teacherList", teacherList);
			
	        dispatcher=request.getRequestDispatcher("admin.jsp");			        
	        dispatcher.include(request, response);  
	     
			
			
			
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
		
		
		

		//doGet(request, response);
	}


}
