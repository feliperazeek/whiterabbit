<% include '/WEB-INF/includes/header.gtpl' %>

<%
import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import com.google.appengine.api.datastore.*
import java.util.List
import java.util.Date
import java.util.Calendar
%>

<table border="0" width="100%">
<tr>
	<td>User</td>
	<td>Created</td>
	<td>Count</td>
	<td>Notification Date</td>
	<td>Notification Sent</td>
	<td>Email From</td>
	<td>Email Subject</td>
</tr>
<%

if ( user ) {
	def query = new Query("Alert")
	query.addSort("created", Query.SortDirection.DESCENDING)
	PreparedQuery preparedQuery = datastore.prepare(query)
	def entities = preparedQuery.asList( withLimit(500) )
		
	for (o in entities) {
	
%>

	<tr <% if (o.notificationSent == false) { %>bgcolor="yellow"<% } else { %>bgcolor="#eeeeee"<% } %>>
		<td>${o.author}</td>
		<td>${o.created}</td>
		<td>${o.count}</td>
		<td>${o.notificationDate}</td>
		<td>${o.notificationSent}</td>
		<td>${o.emailFrom}</td>
		<td>${o.emalSubject}</td>
	</td>	

<% } %>

<% } else { %>
	<tr><td colspan="7">You're not logged in. Please <a class="button" target="_self" href="<%= users.createLoginURL(request.requestURI) %>">login</a> to get started.</td></tr>
<% } %>
</table>

<% include '/WEB-INF/includes/footer.gtpl' %>

