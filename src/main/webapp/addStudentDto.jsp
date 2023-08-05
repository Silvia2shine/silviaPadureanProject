
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>
<%@ page import="com.siit.studentRepoExample.service.StudentService" %>
<%@ page import="com.siit.studentRepoExample.model.dto.CreateStudentDto" %>


<%
  String name = request.getParameter("name");
  String email = request.getParameter("email");

  System.out.println(name +" "+ email);


  CreateStudentDto  student = new CreateStudentDto(name, email);

StudentService studService = new StudentService();
 boolean isAdded = studService.addStudent(student);
 if (isAdded == false){
 %>
 <h2> Name or email is not valid!</h2>
 <form action="index.jsp">
         <br/>
         <input type="submit" value="Back" class="btn btn-primary btn-block"/>
         </form>
 <%
 } else {
 %>
 <meta http-equiv="Refresh" content="0; url='/world" />
<%
 }
 %>




