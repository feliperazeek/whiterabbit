
        </div>
  <div id="footer_nav_subpage"> 
    <ul class="navigation"> 
	    <li><a href="http://www.socialestates.com">&#169; 2010 SocialEstates.com</a></li> 
		                      		   <li><a href="/">Home</a></li> 
		                      		   <% if (user) { %>
		                               <li><a href="mailto:${user.nickname}">Logged In As: ${user.nickname}</a></li> 
		                               <li><a class="button" id="login" target="_self" href="<%= users.createLogoutURL(request.requestURI) %>">Logout</a></li> 
		                               <% } else { %>
		                               <li><a class="button" id="login" target="_self" href="<%= users.createLoginURL(request.requestURI) %>">Login</a></li> 
		                               <% } %>
		                               <li><a href="mailto:whiterabbit@opengraph.co">Contact Us</a></li> 
    
    </ul> 


    </body>
</html>