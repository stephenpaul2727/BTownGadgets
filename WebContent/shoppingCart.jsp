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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});
</script>
</head>
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

<div class="container-fluid" id="navbar">

<div style="margin-top:0%" class="row">
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
<li><a href="#" data-toggle="popover" title ="Phone Numbers" data-trigger="hover" data-placement="top" data-content="(Karthik) &nbsp   8123603159 (Stephen)   &nbsp 8129551395 (Vinita) &nbsp   7658941023">Contact Details</a></li>
<li><a href="#" data-toggle="popover" title="Address" data-trigger="hover" data-placement="bottom" data-content="Apt. B304, Fountain park Apts, Bloomington, IN 47408">Location</a></li>


</ul>
</div>

</div>



</div>
</div>


<div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-hover table-inverse" >
<thead>
<tr><th>Model</th>
<th>Brand</th>
<th>Price</th>
<th>Quantity</th>
<th>Total</th>
<th></th></tr></thead>
<jsp:useBean id="Cart" scope="session" class="com.group2.bean.Cart" />
<c:if test="${Cart.itemCount==0}">
  <tr>
  <td colspan="4"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">- Cart is currently empty -<br/></font>
  </tr>
</c:if>

<c:forEach var="cartItem" items="${Cart.cartItems}" varStatus="counter">
  <form name="item" method="post" action="ServletController">
  <tr>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.model_name}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.brand}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>$<c:out value="${cartItem.price}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	<input type='hidden' name='itemIndex' value='<c:out value="${counter.count}"/>'>
    	<input type='number' name="updatedQuantity" min="1" max="${cartItem.quantity}" value='<c:out value="${cartItem.selectedQuantity}"/>' size='2'>
    	<input type="submit" name="action" value="Update"></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">$<c:out value="${cartItem.totalUnitPrice}"/></font></td>
    <td><input type="submit" name="action" value="Delete"></td>
  </tr>
  </form> 
</c:forEach>
</table><br/><br/>
<b>Total Cart Price: </b>$<c:out value="${Cart.totalCartPrice}"></c:out>
<c:if test="${Cart.itemCount!=0}">
  <form action="ServletController" method="post">
  	<input type="hidden" name="action" value="PlaceOrder">
	<button type="submit" value="Place Order" class="btn btn-success">Place Order</button>
  </form>
</c:if>

</body>
</html>