
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<%
  int id = Integer.parseInt(request.getParameter("id"));


  JpaStudentRepository studentRepo = new JpaStudentRepository();

 boolean isdeleted =  studentRepo.deleteStudentById(id);
 if (isdeleted == false){
 %>
 <h2> The index is not in the list </h2>
 <br>
 <br>
 <form action="admin.jsp">
          <br/>
          <input type="submit" value="Back" class="btn btn-primary btn-block"/>
          </form>


<%
} else {
%>

 <form action="admin.jsp">
         <br/>
         <h2>The user was deleted <h2>
         <input type="submit" value="Back" class="btn btn-primary btn-block"/>
         </form>

  <% } %>