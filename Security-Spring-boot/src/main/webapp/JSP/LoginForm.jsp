<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/api/login" method="post" name="myForm" id="myForm">
  <label for="fname">First name:</label><br>
  <input type="text" id="username" name="username" value="bejay"><br>
  <label for="lname">Password:</label><br>
  <input type="text" id="password" name="password" value="bejay"><br><br>
  <input type="submit" value="Submit" onclick="submitform()">
</form>

<script type="text/javascript">

var form = document.getElementById('myForm');
form.onsubmit = function(event){
        var xhr = new XMLHttpRequest();
        var formData = new FormData(form);
        //open the request
        xhr.open('POST','http://localhost:8080/api/login')
        xhr.setRequestHeader("Content-Type", "application/json");

        //send the form data
        xhr.send(JSON.stringify(Object.fromEntries(formData)));

        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                form.reset(); //reset form after AJAX success or do something else
            }
        }
        //Fail the onsubmit to avoid page refresh.
        return false; 
    }

</script> 
</body>
</html>