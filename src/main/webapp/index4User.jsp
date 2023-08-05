<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.repository.TracksRepository" %>

<%@ page import="com.siit.studentRepoExample.model.Student" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<!DOCTYPE html>
<html>
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
<body>



<h2> Tracks list: </h2>
    <table border="1" class="table table-striped table-hover w-50 p-3">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>audio_link</th>

        </tr>
        <%
         TracksRepository repositoryTrack = new TracksRepository();


            List<Track> trackList = repositoryTrack.getAllTracks();
            for (Track track : trackList ) {
        %>
            <tr>
                <td><%= track.getId() %></td>
                <td><%= track.getName() %></td>
                <td><%= track.getAudio_link() %></td>
            </tr>

        <% } %>
    </table>
<br>
<br>




<h2>My list: </h2>
    <table border="1" class="table table-striped table-hover w-50 p-3">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>audio_link</th>
         </tr>
             <%


                 Student student = (Student) session.getAttribute("student");




 JpaStudentRepository repository = new JpaStudentRepository();

        List<Track> userTracksList = student.tracksList;
         for (Track track : userTracksList) {
                %>
                    <tr>
                        <td><%= track.getId() %></td>
                        <td><%= track.getName() %></td>
                        <td><%= track.getAudioLink() %></td>


                    </tr>
                <% } %>
    </table>




<h1 style="color:red; font-size:400%;"><center>Music Player</center></h1>
 <a href="index3LogIn.jsp">
<button style="color:red; font-size:300%;">Log In </button>
<br></br>
 <a href="index1SignIn.jsp">
<button  style="color:red; font-size:300%;">Sign Up</button>
</a>
<br></br>










<form action="index2CopyIndex.jsp">
        <br/>
        <input type="submit" value="Index Initial" class="btn btn-primary btn-block"/>
        </form>


</body>
</html>