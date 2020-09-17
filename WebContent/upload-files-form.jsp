<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>
   <a href="QuestionControllerServlet">Back to List</a>
    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>

			
            <form action="QuestionControllerServlet" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="command" value="LOAD" />
                <input type="file" name="file"  multiple/>
       			   <input type="submit" value="upload" />
            </form>          
        </div>
       
    </body>
</html>



			