<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>BTown Gadgets</title>
<script type="text/javascript">
function validate()
{
	var x= document.getElementById('user');
	var y = document.getElementById('password');
	var message = document.getElementById("confirmMessage");
	var goodColor = "#66cc66";
    var badColor = "#ff6666";
	if(x.value==''||y.value=='')
	{
		message.style.color=badColor;
		message.innerHTML= "info entered is wrong";
		return false;
	}
	else
	{
		message.style.color=goodColor;
		message.innerHTML="Success";
		return true;
	}
}
</script>
</head>
<body style="background-image:url("Images/back.jpg");">
<img style="align:center;width:400px;height:50px" src="Images/btowngadgetslogo.png"/>


<br/>
<br/>
<br/>
<div >
      <legend class="" style="color:#440591;font-weight:bold;font-size:25px">LOGIN</legend>
    </div>
<div class="modal-dialog">
				<div class="loginmodal-container">
				  <form name="login" onSubmit ="return validate(this)" method="get" action ="ServletController">
				  
				  <input type="hidden" name="what" value="login"/>
					<input type="text" name="username" id ="user" placeholder="Username" required">
					<br/><br/>
					<input type="password" name="password" id="password" placeholder="Password"  required">
					<br/><br/>
					<input type="submit" name="login" class="login loginmodal-submit" value="Login">
				  </form>
					
				  <div class="login-help">
					<a href="SignUp.jsp">Register</a> - <a href="#">Forgot Password</a>
				  </div>
				</div>
				
			</div>
			<br/>
			<br/>
			<div style="color: #FF0000;">${errorMessage}</div><br>
			<span id="confirmMessage" class="confirmMessage"></span>

</body>
</html>