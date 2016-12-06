<%@ page language="java" import="com.group2.bean.OrderDetails,java.util.List" contentType="text/html; charset=UTF-8"
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pending Deliveries</title>
</head>
<body style="background-color:#d7dfed"; overflow:scroll>
<h1>
<table class="table" style="border:null;cellspacing=0" >
<thead>
<th><img src="Images/btowngadgetslogo.png" class="img-responsive" width="250dp"/></th>
<th><form name="myform" action="login.jsp"><button style="float:right;margin-right:5px" type="submit" class="btn btn-danger">Logout</button></form>
</th>
</thead>
</table>
</h1>

<div class="container-fluid" id="navbar">

<div style="margin-top:0%" class="row">
<div class="col-md-3" style="background-color:#FF9999"><form name="myform9" action="staff.jsp"><a href="#" onclick="document.forms.myform9.submit()" style="color:black">Home</a></form></div>

<div class="col-md-3" style="background-color:#b0cc86">
<form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="PendingDeliveries">PendingDeliveries</a></form>
</div>

<div class="col-md-3" style="background-color:#6f96d6">


<form name="myform2" action="ServletController" method="get">
<a href="#" onclick="document.forms.myform2.submit()" style="color:black"><input type="hidden" name="what" value="PendingReturns"/>PendingReturns</a></form>


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



<% List<OrderDetails> orderItems = (List<OrderDetails>)session.getAttribute("PendingItems"); %>
<%int i=0;int size=orderItems.size(); %>
<%if(size==0){ %>
<center><h3>No Deliveries Pending</h3></center>
<%} %>

<form action="ServletController" method="post">
  	
<table class="table table-striped">
<thead>
<tr><th></th>
<th>Model</th>
<th>Brand</th>
<th>Total Price</th>
<th>Quantity</th>
<th>Order Date</th>
<th>Ordered By</th>
<th></th></tr>
</thead>

<%for(OrderDetails orderItem : orderItems) { %>
<tr>
<td><input type="checkbox" name="selectedItems" value=<%=i %>></td>
<td><%=orderItem.getModel_name()%></td>
<td><%=orderItem.getBrand()%></td>
<td><%=orderItem.getTotalUnitPrice()%></td>
<td><%=orderItem.getSelectedQuantity()%></td>
<td><%=orderItem.getOrder_date()%></td>
<td><%=orderItem.getCus_fname()%></td>
</tr>
<%i++; %>
<%} %>
</table>

	<input type="hidden" name="action" value="ItemsDelivered">
	<input type="submit" value="Items Delivered" >

</form>
</body>
</html>