<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %> 
<%@page import="java.util.ArrayList"%>
<%@page import="cse.web.Student"%>
<%Student std = (Student)request.getAttribute("data"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome my dear student <%=std.name %></h1>
	<h3>your registered courses are</h3>
	<table border ="1" width="500" align="center">
         <tr bgcolor="00FF7F">
          <th><b>No.</b></th>
          <th><b>Course Name</b></th>
          <th><b>Status</b></th>
         </tr>
     <%! int i=0; %>  
     <%ArrayList<String> courses=std.getCourses();
    for(int j=0; j<courses.size(); j++){%>
            <tr>
              <td><%=j+1 %></td> 
			  <td><%=courses.get(j) %></td>            
              <td>registered</td>
            </tr>
            

	<%}%>
	</table>
	
<button>Add new course</button>
</body>
</html>