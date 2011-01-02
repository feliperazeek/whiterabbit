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


if (user) {
	def query = new Query("Alert")
	query.addSort("created", Query.SortDirection.DESCENDING)
	query.addFilter("author", Query.FilterOperator.EQUAL, user)
	query.addFilter("notificationSent", Query.FilterOperator.EQUAL, false)
	PreparedQuery preparedQuery = datastore.prepare(query)
	def entities = preparedQuery.asList( withLimit(10) ) 
	
	if (entities) {
			def count = 0
			for (alert in entities) {
				if (count == 0) {
					alert.notificationSent = false
					alert.count = 0
				
					def calendar = Calendar.getInstance()
					calendar.setTime(alert.notificationDate)
					calendar.add(Calendar.DATE, 1)
					alert.notificationDate = calendar.getTime()
				
					alert.save()
					
%>
					<p>Next alarm set for <b>${alert.notificationDate}</b></p>
			
<%		
				} else {
					alert.notificationSent = true
					alert.save()	
				}
				count = count + 1
			}
	
	} else {
%>
		No alert found to be answered. Please <a href="/">create</a> an alert first.
<%
	}

} else {
%>
	You need to be logged-in to complete this operation.
<%

}

%>

%>

<% include '/WEB-INF/includes/footer.gtpl' %>