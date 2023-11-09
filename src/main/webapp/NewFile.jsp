<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
 <anyxmlelement xmlns:c="http://java.sun.com/jsp/jstl/core" />>
 <anyxmlelement xmlns:sql="http://java.sun.com/jsp/jstl/sql" />
<html>
   <head>
      <title>JSTL sql:query Tag</title>
   </head>
   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
         url = ""jdbc:mysql://localhost:3306/web_app""
         user = "root" password = "pass1234"/>
         <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from courses;
      </sql:query>
      <table border = "1" width = "100%">
         <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Credit</th>
            <th>Teacher</th>
         </tr>
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td> <c:out value = "${row.courseId}"/> </td>
               <td> <c:out value = "${row.courseName}"/> </td>
               <td> <c:out value = "${row.credit}"/> </td>
               <td> <c:out value = "${row}"/> </td>
            </tr>
         </c:forEach>
      </table>
   </body>
</html>