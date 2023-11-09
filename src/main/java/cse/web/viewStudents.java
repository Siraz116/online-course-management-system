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

import cse.web.CourseReg;
/**
 * Servlet implementation class viewStudents
 */
@WebServlet("/viewStudents")
public class viewStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //static RequestDispatcher rd1=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd;
        rd=request.getRequestDispatcher("/viewStudents.jsp");  
        rd.include(request, response); 
       // rd.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<CourseReg> regList = new ArrayList<CourseReg>();
		String courseId = request.getParameter("courseId");
		RequestDispatcher rd= null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
			
			
			
			PreparedStatement pst=con.prepareStatement("SELECT DISTINCT courseId,student_name,student_reg FROM course_reg where courseId = ?");
			pst.setString(1,courseId);
			ResultSet rs= pst.executeQuery();
			while (rs.next()) {
				//  String courseId = rs.getString("courseId");
				  String studentName = rs.getString("student_name");
				  int  studentReg=rs.getInt("student_reg");
				  
				  
				  CourseReg cr=new CourseReg(courseId,studentReg,studentName);
				  regList.add(cr);
				}
			request.setAttribute("regList", regList);
			request.setAttribute("courseId", courseId);
			
			
//			PreparedStatement pst5=con.prepareStatement("SELECT courseName FROM courses where courseId = ?");
//			pst5.setString(1,courseId);
//			ResultSet rs5= pst5.executeQuery();
//			request.setAttribute("courseName", rs5.getString("courseName"));
//			System.out.println("the courseName is : "+rs5.getString("courseName"));
			
			
			rd = request.getRequestDispatcher("viewStudents.jsp");

			
		//	rd.include(request,response);
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
		doGet(request, response);
	}

}
