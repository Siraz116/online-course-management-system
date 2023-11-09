package cse.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName= request.getParameter("userName");
		String registraion= request.getParameter("registraion");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String role= request.getParameter("role");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_app","root","pass1234");
			PreparedStatement pst=con.
					prepareStatement("insert into users (username,registration,email,password,role) VALUES(?,?,?,?,?)");
					pst.setString(1,userName);
					pst.setString(2,registraion);
					pst.setString(3,email);
					pst.setString(4,password);
					pst.setString(5,role);
					
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("index.jsp");
			if(rowCount > 0) {
				request.setAttribute("status","success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request,response);
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

//		PrintWriter out=response.getWriter();		
//		out.print("<h1>"+firstName+"</h1>");
//		out.print("<h1>"+lastName+"</h1>");
//		out.print("<h1>"+email+"</h1>");
//		out.print("<h1>"+password+"</h1>");
//		doGet(request, response);
		
		
	}

}
