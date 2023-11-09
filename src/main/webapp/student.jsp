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
	<link rel="stylesheet" type="text/css" href="./public/student.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>" >
   <script src="vendor/jquery/jquery.min.css"></script>
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <link rel="stylesheet" href="alert/dist/sweetalert.css">
 
   <script type="text/javascript">
		var status=document.getElementById("status").value;
		if(status=="success"){
			swal("Congrats","Course Registerd Successfully", "success");
		}
		else if(status=="failed"){
			swal("Sorry","Course Already Registered", "error");
		}

	</script>
<!-- 	<header>
		<nav>
			<h1>Student</h1>
			<h1>Sirazul_Islam</h1>
      <h1> <center>Online Course Registration System</center></h1>
      
			<a href="">Home</a>
			<a href="logout">Logout</a>
		</nav>
	</header>
 -->
 <header>
		<nav>
			
			<div class="dropdown">
				
				<span><h1>Student</h1></span>
				<div class="dropdown-content">
					<h4>
				<p><%= session.getAttribute("name")%></p>
				<p><%= session.getAttribute("reg")%></p>
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
<!--  		<form>  -->
			<h2>Your Courses</h2>
			<table class="css-serial">
				<thead>
					<tr>
						<th>SL</th>
						<th>Course ID</th>
						<th>Course Name</th>
						<th>Credit</th>
						<th>Course Teacher</th>
					</tr>
				</thead>
        
				<tbody>
				 
			     <%ArrayList<String> courses=std.getCourses();
			    for(int j=0; j<courseList.size(); j++){%>
			            <tr>
			              <td> </td>
			              <td><%=courseList.get(j).courseId %></td> 
						  <td><%=courseList.get(j).courseName %></td>            
			              <td><%=courseList.get(j).credit %></td> 
			              <td><%=courseList.get(j).teacherName %></td> 
			            </tr>
			            
			
				<%}%>
			          
<!-- 					<tr>
						<td> </td>
						<td><input type="text" name="course-id"></td>
						<td><input type="text" name="course-name"></td>
						<td><input type="text" name="credit"></td>
					</tr>   -->
                
				</tbody>
			</table>
			<button  type="submit" name="add-now" onclick="window.location.href='./registerCourse';"> <h3>Register New Course</h3></button>
		<!--  		</form>  -->
</main>

</body>
</html>