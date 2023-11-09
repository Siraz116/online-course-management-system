<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %> 
<%@page import="java.util.ArrayList"%>
<%@page import="cse.web.Student"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import= "java.sql.PreparedStatement"%>
<%@page import="java.sql.*"%>
<%Student std = (Student)request.getAttribute("data"); %>
<%@page import="cse.web.Course"%>
<%@page import="cse.web.User"%>
<%ArrayList<Course> courseList=(ArrayList<Course>)request.getAttribute("courseList");%>
<%ArrayList<User> teacherList=(ArrayList<User>)request.getAttribute("teacherList");%>

<!DOCTYPE html>
<html>
<head>
	<title>Online Course Registration</title>
	<link rel="stylesheet" type="text/css" href="./public/admin.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>" >
	<header>
		<nav>
			
			<div class="dropdown">
				
				<span><h1>Admin</h1></span>
				<div class="dropdown-content">
					<h4>
				<p><%= session.getAttribute("name")%></p>
				<p><%= session.getAttribute("email")%></p>
				<!-- <p>Registration: 2018331116</p> -->
			</h4>
				</div>
			  </div>
		
      <h1> <center>Online Course Registration</center></h1>
			<!-- <a href="logout">Logout</a> -->
			<button  type="submit" name="add-now" onclick="window.location.href='logout';"><h3> Logout</h3></button>
		</nav>
	</header>
	<main>
		<form method="post" action="./addcourse">
			
			<h2>Add Course</h2>
			<table class="css-serial">
				<thead>
					<tr>
						<th>Sl </th>
						<th>Course ID</th>
						<th>Course Name</th>
						<th>Credit</th>
						<th>Teacher's Name</th>
					</tr>
				</thead>
        
				<tbody>
				<% for(int k=0; k<courseList.size(); k++){ %>
					<tr>

						<td></td>
						<td><%= courseList.get(k).courseId %> </td>
						<td><%= courseList.get(k).getCourseName() %> </td>
						<td><%= courseList.get(k).getCredit() %> </td>
						<td><%= courseList.get(k).getTeacherName() %></td>
					</tr>
				<%} %>
					
					<tr >
						<td> </td>
						<td><input type="text" name="course-id" required></td>
						<td><input type="text" name="course-name" " required></td>
						<td><input type="number" min="1.0" max="5.0" step="0.5" name="course-credit"required></td>
				 <!-- 		<td><input type="text" name="teacher-name"></td>  -->
						<td>
					<!-- 	    <label for="classes">Assign a Course Teacher:</label>  -->
						<!-- 	<div class="custom-select" > -->
				                    <select required name="course-teacher" id="language" style="width:200px; height:30px; font-size:17px;">
									  <option value="" disabled selected hidden>Choose a Teacher</option>
									  <option value="Moksedur_Rahman" disabled>Moksedur_Rahman</option>
									  <% for(int l=0; l<teacherList.size(); l++){ %>
									  	<option value=<%=teacherList.get(l).username %>> <%=teacherList.get(l).username %></option>
									  <%} %>
									</select>
						<!-- 	</div> -->

                    	</td>
					</tr>
          
          
				</tbody>
			</table>
			<button type="submit" name="add-now">Add Now</button>
		</form>
	</main>
	<script src="vendor/jquery/jquery.min.css"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script type="text/javascript">
    	var status=document.getElementById("status").value;
    	if(status=="success"){
    		swal("Congrats","Course Added Successfully", "success");
    	}
    	else if(status=="failed"){
    		swal("Sorry","An Error Occured", "error");
    	}
    
    </script>
</body>
</html>