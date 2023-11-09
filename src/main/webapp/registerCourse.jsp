<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %> 
<%@page import="java.util.ArrayList"%>
<%@page import="cse.web.Student"%>
<%@page import="cse.web.Course"%>
<%ArrayList<Course> courseList=(ArrayList<Course>)request.getAttribute("courseList");%>
<%Student std = (Student)request.getAttribute("data"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Online Course Registration</title>
	<link rel="stylesheet" type="text/css" href="./public/course.css">
</head>
<body>
	<header>
		<nav>
			<h1>Available Courses</h1>
      
			<a href="logout">Logout</a>
		</nav>
	</header>

	<main>
		<form method='post' action='registerCourse'>
			<h2>Register For New Courses</h2>
			<table class="css-serial">
				<thead>
					<tr>
						<th>SL</th>
                        <th>Select </th>
						<th>Course ID</th>
						<th>Course Name</th>
						<th>Credit</th>
					</tr>
				</thead>
        
				<tbody>
				
				<%! int i=0; %>  
			     <%ArrayList<String> courses=std.getCourses();
			    for(int j=0; j<courseList.size(); j++){%>
			            <tr>
			              <td> </td>
			              <td><input type='checkbox'class='check-box' name="courseId" value=<%=courseList.get(j).courseId %> > </td>
			              <td><%=courseList.get(j).courseId %></td> 
						  <td><%=courseList.get(j).courseName %></td>            
			              <td><%=courseList.get(j).credit %></td> 
			            </tr>
			            
			
				<%}%>
          
				</tbody>
			</table>
			<button type="submit" name="submit-add">Submit</button>
		</form>
	</main>
</body>
</html>