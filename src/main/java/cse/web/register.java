package cse.web;
import cse.web.Student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registerCourse
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
        RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");  
        rd.include(request, response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userType = request.getParameter("role");
		RequestDispatcher rd;
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
		    
		if(userType.charAt(0)=='S'){		       
	        rd=request.getRequestDispatcher("student.jsp");  
	        rd.include(request, response);  
	        System.out.println(userType);
	        
	       // System.out.println(l[1]);
	        
			
		}else if(userType.charAt(0)=='T'){
	        rd=request.getRequestDispatcher("teacher.jsp");  
	        rd.include(request, response);  
	        System.out.println(userType);
			
		}else {  //admin-login
	        rd=request.getRequestDispatcher("admin.jsp");  
	        rd.include(request, response);  
	        System.out.println(userType);
			
		}
		//rd.forward(request, response);

		//doGet(request, response);
	}

}	


