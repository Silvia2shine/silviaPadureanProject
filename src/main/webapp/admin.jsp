<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.repository.TracksRepository" %>

<%@ page import="com.siit.studentRepoExample.model.Student" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<html>

 <head>

    <!-- This will make the table look nicer -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Very nice student list</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.4/dist/bootstrap-table.min.css">
 <link rel = "stylesheet" href = "C:\\Users\\Silvia\\duplicate\\javaEEexamplePtProiectulFinalJdbc2\\src\\main\\java\\com\\siit\\studentRepoExample\\styling\\tabel.css">
  </head>
<body style="background-color:powderblue;">

<h1> Admin account <h1>
<h2>Users list</h2>
    <table style="position:relative; margin: 15px;"border="1" class="table table-striped table-hover w-50 p-3 myTable">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
             <th>Tracks</th>
             <th>Password</th>
             <th>Rol</th>


        </tr>
        <%


         JpaStudentRepository repository = new JpaStudentRepository();


            List<Student> students = repository.getAllStudents();
            for (Student student : students) {
        %>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getEmail() %></td>
                 <td><%= student.getTracksAsCsv() %></td>
                 <td><%= student.getPassword() %></td>
                 <td><%= student.getRol() %></td>

            </tr>
        <% } %>
    </table>

<br/>
<br/>
 <h2>Delete a student by ID </h2>

        <form action="deleteStudentById.jsp">
            <div class="form-outline mb-4">
                <input type="text" name="id" value="Id..." onclick="this.value=''"/><br/>
            </div>

        <br/>
        <input type="submit" value="Delete student" class="btn btn-primary btn-block"/>
        </form>

<br>
<br>
<h2>Tracks list </h2>
    <table border="1" class="table table-striped table-hover w-50 p-3">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>audio_link</th>
            <th>Students</th>

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
                  <td><%= track.getStudentsAsCsv() %></td>


            </tr>
        <% } %>
    </table>

    <br/>
    <br/>


    <h2>Add a track</h2>

        <form action="addTrack.jsp">
            <div class="form-outline mb-4">
                <input type="text" name="name" value="Name..." onclick="this.value=''"/><br/>
            </div>
            <div class="form-outline mb-4">
                <input type="text" name="audio_link"  value="Audio_link..." onclick="this.value=''"/><br/>
            </div>

        <br/>
        <input type="submit" value="Add track" class="btn btn-primary btn-block"/>
        </form>


<br/>
<br/>
 <h2>Delete track by ID </h2>

        <form action="deleteTrackById.jsp">
            <div class="form-outline mb-4">
                <input type="text" name="id" value="Id..." onclick="this.value=''"/><br/>
            </div>

        <br/>
        <input type="submit" value="Delete track" class="btn btn-primary btn-block"/>
        </form>
