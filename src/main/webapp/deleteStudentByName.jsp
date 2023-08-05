
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<%
 String name = request.getParameter("name");


  JpaStudentRepository studentRepo = new JpaStudentRepository();

  boolean isDeleted = studentRepo.deleteStudentByName(name);
  if (isDeleted == false){
  %>
   <h2> The name is not in the list </h2>
    <br>
    <br>
    <form action="admin.jsp">
             <br/>
             <input type="submit" value="Back" class="btn btn-primary btn-block"/>
             </form>
   <%
   } else{
   %>

    <form action="admin.jsp">
            <br/>
            <h2> The user was deleted </h2>
            <input type="submit" value="Back" class="btn btn-primary btn-block"/>
            </form>

<% } %>

