<html>
    <head>
        <title>White Rabbit | Late to work often? Me too but I got a little dirty secret</title>
        
        <link rel="shortcut icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        <link rel="icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>

    </head>
    <body>
    
	<div id="main-wrapper"> 
		<div id="wrapper1"> 
		<div id="wrapper3"> 
		    <div id="maincol"> 
		
				<div id="centercol"> 
		            
					<div id="logo_hdr"> 
							<form action="/" method="get"><input type="submit" value="WHITE RABBIT"></form> 
		            </div> 
		            
		            <div id="navigation"> 
		               <ul class="navigation"> 
		                      		   <li><a href="/">Home</a></li> 
		                      		   <% if (user) { %>
		                               <li><a href="mailto:${user.nickname}">Logged In As: ${user.nickname}</a></li> 
		                               <li><a class="button" id="login" target="_self" href="<%= users.createLogoutURL(request.requestURI) %>">Logout</a></li> 
		                               <% } else { %>
		                               <li><a class="button" id="login" target="_self" href="<%= users.createLoginURL(request.requestURI) %>">Login</a></li> 
		                               <% } %>
		                               <li><a href="mailto:whiterabbit@opengraph.co">Contact Us</a></li> 
		               </ul>		            
		            </div> 
		            
		            <div class="clear"></div> 
		            
		            <div id="content-bg"> 