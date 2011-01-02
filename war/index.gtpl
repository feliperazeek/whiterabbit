<% include '/WEB-INF/includes/header.gtpl' %>

<p>
    I seem to wake up late to work very often.<br> 
    It's such a horrible feeling to wake up a couple of hours after you are supposed to be at work, and your phone is full of missed calls from your boss.

</p>
<p>
    I always joked it would be awesome to have an application that would automatically 
    email my boss in case I didn't wake in time to do so.</p>
    It turns out there's an app for that - and it is awesome!
</p>

<p>
   <% if (user) { %>
		<p><a href="/alert/${user.userId}">Set your alert.</a></p>
	<% } else { %>
		<p>You're not logged in. Please <a class="button" target="_self" href="<%= users.createLoginURL(request.requestURI) %>">login</a> to get started.</p>
	<% } %>
</p>

<% include '/WEB-INF/includes/footer.gtpl' %>

