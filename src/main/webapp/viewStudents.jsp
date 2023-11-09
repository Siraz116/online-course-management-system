<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="cse.web.CourseReg"%>
<%ArrayList<CourseReg> regList=(ArrayList<CourseReg>)request.getAttribute("regList");%>
<% String courseName= (String)request.getAttribute("courseName"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Online Course Registration</title>
	<link rel="stylesheet" type="text/css" href="./public/view.css">
</head>
<body>
	<header>
		<nav>
			<h1>Subject</h1>
      <h1> <center>Online Course Registration</center></h1>
			<a href="logout">Logout</a>
		</nav>
	</header>

	<main>
		<form>
            <h1>Course : <%= request.getAttribute("courseId") %></h1>
			<h2>Student List</h2>
			<table class="css-serial">
				<thead>
					<tr>
						<th>SL</th>
						<th>Registration no</th>
						<th>Student Name</th>
						
					</tr>
				</thead>
        
				<tbody>
          			<% for(int k=0; k<regList.size(); k++){ %>
	                    <tr>
							<td> </td>
							<td><%= regList.get(k).studentReg %></td>
							<td><%= regList.get(k).studentName %></td>
							
						</tr>            
						
					<% }%>
					
				
				</tbody>
			</table>
			
		</form>
	</main>
</body>
</html>