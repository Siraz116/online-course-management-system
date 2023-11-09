package cse.web;
import cse.web.Student;
import cse.web.Course;
import cse.web.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class onlineCourse
 */
@WebServlet("/onlineCourse")
public class onlineCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public onlineCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.setContentType("text/html");
		//PrintWriter printWriter =response.getWriter();
		//printWriter.println("<h1>Welcome to online course registraion</h1>");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
        RequestDispatcher rd= null;
        this.doPost(request, response);
        return;
       // rd=request.getRequestDispatcher("/login.jsp");  
       // rd.include(request, response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<User> teacherList = new ArrayList<User>();
		String userType = request.getParameter("role");
		RequestDispatcher rd=null;
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		ArrayList<String> crs = new ArrayList<String>();
	       crs.add("C");
	       crs.add("python");
	       crs.add("java");
	       crs.add("ML");
	       crs.add("Security");
	       String[] l={"C","python","java","ML"};
			Student st=new Student("abir","108",crs);

		    request.setAttribute("data", st); 
		    request.setAttribute("subjectList",l);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
			PreparedStatement pst=con.
					prepareStatement("SELECT * FROM users where email = ? and password = ? and role=?");
			pst.setString(1,email);
			pst.setString(2,password);
			pst.setString(3,userType);
			
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
			//	System.out.println(rs);
				session.setAttribute("name", rs.getString("username"));
				session.setAttribute("reg",rs.getString("registration"));
				session.setAttribute("email",rs.getString("email"));
//				rd=request.getRequestDispatcher("student.jsp");
				if(userType.charAt(0)=='S'){       //student login
					PreparedStatement pst5=con.prepareStatement("SELECT DISTINCT courses.courseId,courseName,credit,teacherName "
														+ " FROM "
														+ " courses INNER JOIN course_reg "
														+ " ON courses.courseId=course_reg.courseId "
														+ " WHERE student_name=?;");
					pst5.setString(1,rs.getString("username"));
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
			        System.out.println(userType);
			        
			       // System.out.println(l[1]);
			        
					
				}else if(userType.charAt(0)=='T'){     //teacher login
					PreparedStatement pst3=con.prepareStatement("SELECT * FROM courses WHERE teacherName= ?");
					pst3.setString(1,rs.getString("username"));
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
					
					
			        rd=request.getRequestDispatcher("teacher.jsp");  
			        rd.include(request, response);  
			        System.out.println(userType);
					
				}else {  //admin-login
					PreparedStatement pst1=con.prepareStatement("SELECT * FROM courses");
					ResultSet rs1= pst1.executeQuery();
					
					while (rs1.next()) {
						  String courseId = rs1.getString("courseId");
						  String courseName = rs1.getString("courseName");
						  Double credit =rs1.getDouble("credit");
						  String courseTeacher=rs1.getString("teacherName");
						  
						  Course c=new Course(courseId,courseName,credit,courseTeacher);
						  courseList.add(c);
						}
					
					request.setAttribute("courseList", courseList);
					
					
					PreparedStatement pst2=con.prepareStatement("SELECT * FROM users WHERE role = 'Teacher' ");
//					pst2.setString(1,"Teacher");
					ResultSet rs2= pst2.executeQuery();
					while (rs2.next()) {
						  String teacherEmail=rs2.getString("email");
						  String teacherName=rs2.getString("username");
						  int teacherRegistraion=rs2.getInt("registration");
						  String passwordT=rs2.getString("password");
						  String role="Teacher";
						  
						  User t=new User(teacherEmail,teacherName,teacherRegistraion,passwordT,role);
						  teacherList.add(t);
						}
					request.setAttribute("teacherList", teacherList);
					
			        rd=request.getRequestDispatcher("admin.jsp");			        
			        rd.include(request, response);  
			        System.out.println(userType);
			        con.close();
					
				}
			}else {
				request.setAttribute("status", "failed");
				rd=request.getRequestDispatcher("index.jsp");
			}
			rd.forward(request,response);
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//rd.forward(request, response);

		//doGet(request, response);
	}

}
