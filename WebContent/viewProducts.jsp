<%@ page language="java" import="com.group2.bean.Product,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
<ul><li><form name="myform" action="customer.jsp"><a href="#" onclick="document.forms.myform.submit()">Home</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="ViewProducts">View Products</a></form></li>
<li><form name="myform2" action="ServletController" method="get"><a href="#" onclick="document.forms.myform2.submit()"><input type="hidden" name="what" value="ViewPurchaseHistory"/>Purchase History</a></form></li>
<li><form name="myform3" action="shoppingCart.jsp"><a href="#" onclick="document.forms.myform3.submit()">Shopping Cart</a></form></li>
</ul>
</div>

<div>
<% List<Product> proList = (List<Product>)request.getAttribute("ProductsList"); %>
<%int i=0;int size=proList.size(); %>
<%if(size==0){ %>
<center><h3>No Products</h3></center>
<%} %>

<%if(size!=0){ %>

<table style="allign:center">
<tr>
<th>PIC</th>
<th>Details</th>
<th></th></tr>
<% while (i<size) {%>
<tr><td><img src="<%=proList.get(i).getImagePath() %>" class="img-responsive" width="250dp"/></td>
<td><ul>
<li><%=proList.get(i).getModel_name()%></li>
<li><%=proList.get(i).getBrand()%></li>
<li>Price: $<%=proList.get(i).getPrice()%></li>
<li>Available: <%=proList.get(i).getQuantity()%></li>
</ul></td>
<td>
<form name="myform" action="ServletController" method="get">
<input type="hidden" name="what" value="ViewItem">
<input type="submit" value="View" ><input type="hidden" name="productId" value="<%=proList.get(i).getPro_id()%>">
</form>
</td></tr>
<%i++;  }  %>
</table>

<%}%>
</div>

</body>
</html>