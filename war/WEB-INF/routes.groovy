// example routes
/*
get "/blog/@year/@month/@day/@title", forward: "/WEB-INF/groovy/blog.groovy?year=@year&month=@month&day=@day&title=@title"
get "/something", redirect: "/blog/2008/10/20/something", cache: 2.hours
get "/book/isbn/@isbn", forward: "/WEB-INF/groovy/book.groovy?isbn=@isbn", validate: { isbn ==~ /\d{9}(\d|X)/ }
*/

// Alert Answer
get "/alert/answer/@user", forward: "/WEB-INF/groovy/alertAnswer.groovy"
get "/alert/answer", forward: "/WEB-INF/groovy/alertAnswer.groovy"

// Alert Form
get "/", forward: "/WEB-INF/groovy/alert.groovy"
get "/alert/@user", forward: "/WEB-INF/groovy/alert.groovy?user=@user"
post "/alert/set", forward: "/WEB-INF/groovy/alert.groovy"
post "/alert/set/@user", forward: "/WEB-INF/groovy/alert.groovy"

// Batch Process
get "/batch", forward: "/WEB-INF/groovy/notificationBatch.groovy"

// Debug
get "/debug", forward: "/debug.gtpl"


// routes for the blobstore service example
get "/upload",  forward: "/upload.gtpl"
get "/success", forward: "/success.gtpl"
get "/failure", forward: "/failure.gtpl"
