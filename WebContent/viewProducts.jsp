<%@ page language="java" import="com.group2.bean.Product,java.util.List" contentType="text/html; charset=UTF-8"
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
<title>Products</title>
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

<br/><br/>
<% List<Product> proList = (List<Product>)request.getAttribute("ProductsList"); %>
<%if(proList == null) { %>
<p> proList is NULL</p>
<%} %>
<%if(proList != null) { %>
<%int i=0;int size=proList.size(); %>
<%if(size==0){ %>
<center><h3>No Products</h3></center>
<%} %>

<%if(size!=0){ %>

<div class="list-group">
<p style="font-weight:bold;font-size:20px;color:#e56f64"> Currently available products:</p><br/><br/>
<% while (i<size) {%>
<table align="center" cellspacing=0 cellpadding=0 class="table table-sm table-hover">
<tr><td><img src="<%=proList.get(i).getImagePath() %>" class="img-responsive" width="150dp"/></td>
<td><ul >
<li style="font-weight:bold;font-size:15px"><%=proList.get(i).getModel_name()%></li>
<li style="font-weight:bold;font-size:15px"><%=proList.get(i).getBrand()%></li>
<li style="font-weight:bold;font-size:15px">Price: $<%=proList.get(i).getPrice()%></li>
<li style="font-weight:bold;font-size:15px">Available: <%=proList.get(i).getQuantity()%></li>
</ul></td>
<td style="text-align:center;vertical-align:middle;">
<form name="myform" action="ServletController" method="get">
<input type="hidden" name="what" value="ViewItem">
<button style="float:right;margin-right:5px" type="submit" class="btn btn-success" value="View">View</button><input type="hidden" name="productId" value="<%=proList.get(i).getPro_id()%>">
</form>
</td></tr>
</table>
<%i++;  }  %>
</div>
<%} %>
<%}%>
</div>

</body>
</html>