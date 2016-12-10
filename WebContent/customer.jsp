<%@ page language="java" import="com.group2.bean.Customer" contentType="text/html; charset=UTF-8"
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
<title>BTownGadgets</title>
</head>
<body>
<body style="background-color:#d7dfed"; overflow:scroll>

<h1>
<table class="table" style="border:null;cellspacing=0" >
<thead>
<th><img src="Images/btowngadgetslogo.png" class="img-responsive" width="250dp"/></th>
<th><form name="myform" action="login.jsp"><button style="float:right;margin-right:5px" type="submit" class="btn btn-danger">Logout</button></form><form name="myform3" action="shoppingCart.jsp"><a href="#" onclick="document.forms.myform3.submit()"><button style="float:right;margin-right:5px" type="button" class="btn btn-warning">Shopping Cart</button></a></form>
</th>
</thead>
</table>
</h1>
<span></span>
<div class="container-fluid" id="navbar">

<div style="margin-top:2%" class="row">
<div class="col-md-3" style="background-color:#FF9999"><form name="myform9" action="customer.jsp"><a href="#" onclick="document.forms.myform9.submit()" style="color:black">Home</a></form></div>

<div class="col-md-3" style="background-color:#b0cc86">
<form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="ViewProducts">View Products</a></form>
</div>

<div class="col-md-3" style="background-color:#6f96d6">


<form name="myform2" action="ServletController" method="get">
<a href="#" onclick="document.forms.myform2.submit()" style="color:black"><input type="hidden" name="what" value="ViewPurchaseHistory"/>Purchase History</a></form>


</div>

<div class="col-md-3" style="background-color:#86ccb4">

<div class="dropdown">
<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color:black">About us</a>
<ul class="dropdown-menu" style="background-color:#86ccb4;width:184px">
<li><a href="#">Contact Details</a></li>
<li><a href="#">Location</a></li>

</ul>
</div>

</div>



</div>
</div>
<div style="overflow:scroll;height:550px;margin:0px;padding:0px" >
<a href="#"><img src="Images/add13.jpg" style="width:100%; height:435px" /></a>
<a href="#"><img src="Images/apple2.jpg" style="width:100%; height:435px" /></a>
<a href="#"><img src="Images/add11.jpg" style="width:100%; height:435px" /></a>


</div>

<p align="center">&copy; Copyrights Reserved 2016.BTownGadgets Inc.</p>


</div>
	
</body>
<%-- <p>It is Customer</p>
<% Customer user = (Customer)session.getAttribute("User"); %>
<p>Welcome <%= user.getFname()%></p>
<ul><li><form name="myform" action="customer.jsp"><a href="#" onclick="document.forms.myform.submit()">Home</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="ViewProducts">View Products</a></form></li>
<li><form name="myform2" action="ServletController" method="get"><a href="#" onclick="document.forms.myform2.submit()"><input type="hidden" name="what" value="ViewPurchaseHistory"/>Purchase History</a></form></li>
<li><form name="myform3" action="shoppingCart.jsp"><a href="#" onclick="document.forms.myform3.submit()">Shopping Cart</a></form></li>
<li><form name="myform4" action="ServletController" method="post"><a href="#" onclick="document.forms.myform4.submit()"><input type="hidden" name="action" value="Logout"/>Logout</a></form></li>
</ul> --%>

</body>
</html>