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

<p>
	<h3>
    I seem to wake up late to work very often.
    <div class="clear"></div> 
    <div class="clear"></div> 
    It's such a horrible feeling to wake up a couple of hours after you are supposed to be at work, and your phone is full of missed calls from your boss.
    </h3>

</p>
<p>
	<h3>
    I always joked it would be awesome to have an application that would automatically 
    email my boss in case I didn't wake in time to do so.</p>
    <div class="clear"></div> 
    <div class="clear"></div> <div class="clear"></div> 
    It turns out there's an app for that - and it is awesome!
    </h3>
</p>

<div class="clear"></div> 

<p>
   <% if (user) { %>

<%

def debug = false

def saved = false

def alert = new Entity("Alert")
alert.created = new java.util.Date()
alert.emailFrom = ""
alert.emailTo = "yourbossemail@yourcompany.com"
alert.emailSubject = "Sorry for any trouble"
alert.emailBody = "Hi Mr. John Smith,\n\nI apologize for the late notification, I am not feeling well today so I am running late.\n\nI am sorry for any inconvenience and thank you for the understanding.\n\nYou Name"
alert.author = user
alert.hour = 9
alert.minute = 0
alert.count = 0
alert.notificationDate = new Date()
alert.notificationSent = false


if (params.setAlarm) {

	// Expire Current Ones
	def query = new Query("Alert")
	query.addSort("created", Query.SortDirection.DESCENDING)
	query.addFilter("author", Query.FilterOperator.EQUAL, user)
	PreparedQuery preparedQuery = datastore.prepare(query)
	def entities = preparedQuery.asList( withLimit(10) )
	for (e in entities) {
		e.notificationSent = true
		e.save()
	}

	// Set Params
	alert << params
	
	// Get Params
	def hour = params.hour as int
	def minute = params.minute as int
	
	// Set Alarm Date
	def calendar = Calendar.getInstance()
	calendar.add(Calendar.DATE, 1)
	calendar.set(Calendar.HOUR, hour)
	calendar.set(Calendar.MINUTE, minute)
	calendar.set(Calendar.SECOND, 0)
	alert.notificationDate = calendar.getTime()
	

	if ( users.isUserLoggedIn() ) {
    	alert.save()
    	saved = true
	}

	if (saved) { 

%>

		<div class="panel" id="added" title="Alert Added">
    		<h1>Alert Add Completed</h1>
    		<h2>Next alarm set for: ${alert.notificationDate}</h2>
		</div>

	<% } 

} else {

	if ( user ) {
		def query = new Query("Alert")
		query.addSort("created", Query.SortDirection.DESCENDING)
		query.addFilter("author", Query.FilterOperator.EQUAL, user)
		PreparedQuery preparedQuery = datastore.prepare(query)
		def entities = preparedQuery.asList( withLimit(10) )
		
		if (debug) {
			print entities
			print "<hr>"
			print user
			print "<hr>"
			// print entities.get(0)
			print "<hr>"
		}
		
		if ( entities.size() > 0 ) {
			alert = entities.get(0)
		}
   		
	} else {

%>

	<div class="panel" id="not-added" title="User Not Added">
    	<h2>Error or Permission Denied</h2>
    	<p>You must be logged in to add an alert</a>
	</div>

<% 

}


} 

%>

<p>
<form action="/alert/set" method="POST">

			<p> 
				Email From (Your email address, which is the email address the notification will be sent from) <br /> 
				<input type="text" name="emailFrom" size="75" maxlength="75" value="${alert.emailFrom}" /> 
			</p> 

			<p> 
				Email To (Who will be getting the notification) <br /> 
				<input type="text" name="emailTo" size="75" maxlength="75" value="${alert.emailTo}" /> 
			</p> 
			
			<p> 
				Email Subject (The subject of the notification) <br /> 
				<input type="text" name="emailSubject" size="75" maxlength="75" value="${alert.emailSubject}" /> 
			</p> 
			
			<p> 
				Email Body (The body of the email notification) <br /> 
				<textarea name="emailBody" value="${alert.emailBody}" cols="75" rows="20" >${alert.emailBody}</textarea> 
			</p> 

			<p> 
				Time (The hour and minute the notification will go out - you will get notified before that) <br /> 
				<input type="text" name="hour" size="2" maxlength="2" value="${alert.hour}" /> : <input type="text" size="2" maxlength="2" name="minute" value="${alert.minute}" />
			</p> 
			
			<p>
				<input type="hidden" name="setAlarm" value="1">
				<input type="submit" value="Set Alert">
			</p>



</form>
</p>

	<% } else { %>
		<p>You're not logged in. Please <a class="button" target="_self" href="<%= users.createLoginURL(request.requestURI) %>">login</a> to get started.</p>
	<% } %>
	</p>

<% include '/WEB-INF/includes/footer.gtpl' %>
