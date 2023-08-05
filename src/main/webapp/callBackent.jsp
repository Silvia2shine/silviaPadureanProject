
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>
<%@ page import="com.siit.studentRepoExample.repository.TracksRepository" %>
<%@ page import="java.util.List" %>
<%
JpaStudentRepository jpa = new JpaStudentRepository();
    String trackID = request.getParameter("trackID");
    int studentID = request.getParameter("studentID");

    repo.addTrackToStudent(trackID, studentID); // Call the Java method
%>