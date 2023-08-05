
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.TracksRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<%
  String name = request.getParameter("name");
  String audio_link = request.getParameter("audio_link");

  Track track = new Track(name,audio_link);

  TracksRepository tracksRepo = new TracksRepository();

  boolean isAdded = tracksRepo.addTrack(track);
  if (isAdded == false){
 %>
 <h1> The index is not in the list </h1>

<%
}
%>

 <form action="admin.jsp">
         <br/>
         <h2> The track was added to the list! </h2>
         <input type="submit" value="Back" class="btn btn-primary btn-block"/>
         </form>