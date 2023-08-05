
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>
<head>
<style>
body {
  background-image: url('pictures/I-Love-Music-1920x1200.jpg');
  background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
}
</style>
</head>

<%
  String name = request.getParameter("name");
  String email = request.getParameter("email");
  String password = request.getParameter("password");



  Student student = new Student(name,email,password);

JpaStudentRepository repo = new JpaStudentRepository();
if (repo.existInDb(student) == false){
%>
 <h1> The e-mail already exist </h1>
 <%
 } else {

  boolean isAdded = repo.addStudentWithPassword(student);
  if (isAdded == false){
 %>
 <h1> The name, e-mail or password is not correct</h1>

<%
} else {
%>
<h1>The account has been created. Please log-in! </h1>
<%
}
}
%>

 <form action="index.jsp">
         <br/>
         <input type="submit" value="Back" />
         </form>



