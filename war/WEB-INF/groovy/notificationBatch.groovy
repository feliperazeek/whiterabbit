import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import com.google.appengine.api.datastore.*
import java.util.List
import java.util.Date

debug = true

def query = new Query("Alert")
query.addFilter("notificationDate", Query.FilterOperator.LESS_THAN, new Date())
query.addFilter("notificationSent", Query.FilterOperator.EQUAL, false)
PreparedQuery preparedQuery = datastore.prepare(query)
def entities = preparedQuery.asList(withLimit(999999))

if (entities) {		
	for ( alert in entities ) {
	
	log.info("Entity: " + alert)

	if (alert.count <= 5) {
		// Send email to user
		mail.send sender: "whiterabbit@opengraph.co",
		to: alert.emailFrom,
		subject: "[ACTION REQUIRED] White Rabbit - Notification ${alert.count} of 5",
		textBody: "Wake up - <a href='http://whiterabbit-io.appspot.com/alert/answer'>Notify us</a> you are awake otherwise we are going to send the alert out!"
		
		// Update Count
		alert.count = alert.count + 1
		alert.save()
		
		// Log Debug
		if (debug) {
			print "<p>Email to user was sent to " + alert.emailFrom + ".</p>"
			print "<p>Count Updated: " + alert.count + "</p>"
		}
		log.info("Email to user was sent to " + alert.emailFrom + ", count: " + alert.count)
	
	} else {
		// Send Email to Boss 
		mail.send sender: alert.emailFrom,
		to: alert.emailTo,
		subject: alert.emailSubject,
		textBody: alert.emailBody
		
		// Update Notification Sent Flag
		alert.notificationSent = true
		alert.save()
		
		// Log Debug
		if (debug) {
			print "<p>Notification to boss was sent to " + alert.emailTo + ".</p>"
			print "<p>Notification Sent Updated: " + alert.notificationSent + "</p>"
		}
		log.info("Notification to boss was sent to " + alert.emailTo)
	}
	
	print "<hr>"

	}
} else {
	if (debug) {
		print "No entities available"
	}
	log.info("Batch - No entities available")

}