<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
 function checkAndSubmit(){
 
 var question1=document.frm1.question.value;
 var len= question1.length;
  
 if(len==0)
 {
 	alert("Enter Question fields should not be empty. Please reenter!");
 	return false;
 }
 // or you may use if(myName=="") for a condition if u prefer to use text
 frm1.submit();
 }
 </script>

<meta charset="ISO-8859-1">

	<title>Add Questions</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-question-style.css">	


</head>
<body>
		<p>
			<a href="QuestionControllerServlet">Back to List</a>
		</p>
	<div id="wrapper">
		<div id="header">
			<h2>Maths Question Bank</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Question</h3>
		
		<form name=frm1 action="QuestionControllerServlet"  method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			
			
			<table align="center">
				<tbody>
					<tr>
						<td><label>Question:</label></td>
						<td><input type="text" name="question" style="height:100px;width:500px"/></td>
					</tr>


					<tr>
						<td><label></label></td>
						<td><input type="button" value="Save"  onclick="checkAndSubmit()"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>

	</div>
</body>

</html>

