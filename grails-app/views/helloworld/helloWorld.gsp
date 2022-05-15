<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Hello World Page</title>
</head>
<body>
    <div class="container">
     	<h1>${greeting}</h1>
        <section class="row">           
            <p>
                Hello, this is our "${greeting}" gsp-page.
            </p>           
        </section>
        
       <section class="row"> 
       		<p>
       			<g:message code="not.within.messagesXy.property" default="OhohFallbackIsNotWorking!"/>
       		</p> 
       </section>
    </div>
</body>
</html>