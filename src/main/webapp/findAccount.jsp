<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>
<%@ page import="com.siit.studentRepoExample.repository.TracksRepository" %>
<%@ page import="java.util.List" %>
<head>


 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.4/dist/bootstrap-table.min.css">
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
  String email = request.getParameter("email");
  String password = request.getParameter("password");



  Student student = new Student("",email,password);



JpaStudentRepository repo = new JpaStudentRepository();
Student studentDB = null;
studentDB = repo.existInDbWithPass(student);

if ( studentDB != null ){
%>
 <h1> Here is your account </h1>
 <br>
 <h2> Tracks list: </h2>
 <div style="display: flex">

     <table style="margin: 20px" border="1" class="table table-striped table-hover w-50 p-3">
         <tr>
             <th>ID</th>
             <th>Name</th>
             <th>audio_link</th>
             <th> Action </th>

         </tr>
         <%
          TracksRepository repositoryTrack = new TracksRepository();


             List<Track> trackList = repositoryTrack.getAllTracks();
             for (Track track : trackList ) {
         %>
             <tr>
                 <td id="trackId"  ><%= track.getId() %></td>
                 <td><%= track.getName() %></td>
                 <td><%= track.getAudio_link() %></td>
                 <td id="button"> <button type="submit" > Add </button>   </td>
             </tr>

         <% } %>
     </table>
     <div style="position:relative; display:flex; align-items:center; margin:20px; flex-direction: column">
         <h2>My list: </h2>
         <h2><%=studentDB.getTracksAsCsv()%></h2>

         <select id="audio-select" style="margin: 10px" onClick="onClick()">
         <%
            for (Track track: studentDB.getTracksList()){

          %>
          <option value= <%=track.getAudio_link() %> > <%=track.getName() %>   </option>
         <%
            }
         %>
          </select>

          <audio controls audioplay
                <source id="source"  type = "audio/mpeg"> </source>
            </audio>

           <script>
                const onClick = () => {
                  var dropdown = document.getElementById("audio-select");
                  var link = dropdown.value;

                  var source = document.getElementById("source");
                  source.src = "music/" + link;
                }

                const rows = document.getElementsByTagName('tr');
                console.log(rows);
                Array.from(rows).forEach((row , index) =>{
                    const data = row.getElementsByTagName('td');
                    if (data[0] !== undefined)
                        console.log(data[0].innerHTML);

                    if (data[3] !== undefined){
                        const button = data[3].getElementsByTagName('button');
                        button[0].addEventListener('click', () =>{

                            const trackID = Number( data[0].innerHTML.toString());
                            console.log(trackID);

                            var xhr = new XMLHttpRequest();
                            var url = "findAccount.jsp?trackID=" + encodeURIComponent(trackID)+"?studentID=" +encodeURIComponent(<%=studentDB.getId()%>);

                             xhr.onreadystatechange = function() {
                             if (xhr.readyState === 4 && xhr.status === 200) {
                                   console.log(xhr.responseText); // Output the response from Java method
                             }
                             };

                             xhr.open("GET", url, true);
                             xhr.send();

                             <%
                             String trackID = request.getParameter("trackID");


                             repo.addTrackToStudent(trackID, studentDB.getId());
                             %>

                        });
                    }


                }

                )



           </script>
    </div>
    </div>
 <br>
 <br>

 <h2> Add track to My list </h2>
    <form action="addTrackAtStudentTrackList.jsp?studentId="+studentDB.getId()>

      <input type="text" id="id" name="id"><br><br>
      <input type="submit" value="Add track">
    </form>

   <br>
   <br>

     <h2> Admin</h2>
        <form action="admin.jsp">


          <input type="submit" value="Admin">
        </form>




 <%
 } else {
 %>
 <h1> The e-mail or password is not correct</h1>

<%
}
%>



 <form action="index3LogIn.jsp">
         <br/>
         <input type="submit" value="Back" />
         </form>

