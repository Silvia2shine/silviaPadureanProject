
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.TracksRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Track" %>

<%
  int id = Integer.parseInt(request.getParameter("id"));


 TracksRepository repo = new TracksRepository();

 boolean isdeleted =  repo.deleteTrackById(id);
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

 <form action="admin.jsp" >
         <br/>
         <h2>The track was deleted <h2>
         <input type="submit" value="Back" class="btn btn-primary btn-block"/>
         </form>

  <% } %>