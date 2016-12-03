<%@ page language="java" import="com.group2.bean.OrderDetails,java.util.List" contentType="text/html; charset=UTF-8"
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
<% List<OrderDetails> orderItems = (List<OrderDetails>)request.getAttribute("OrderItems"); %>
<%int i=0;int size=orderItems.size(); %>
<%if(size==0){ %>
<center><h3>No Orders Placed Yet</h3></center>
<%} %>

<table style="allign:center">
<tr>
<th>Order ID</th>
<th>Model</th>
<th>Brand</th>
<th>Quantity</th>
<th>Price</th>
<th>Status</th>
<th>OrderDate</th>
<th>ReturnDate</th>
</tr>

<% while (i<size) {%>
<tr>
<td><%=orderItems.get(i).getOrder_id()%></td>
<td><%=orderItems.get(i).getModel_name()%></td>
<td><%=orderItems.get(i).getBrand()%></td>
<td><%=orderItems.get(i).getQuantity()%></td>
<td><%=orderItems.get(i).getPrice()%></td>
<td><%=orderItems.get(i).getStatus()%></td>
<td><%=orderItems.get(i).getOrder_date()%></td>
<td><%=orderItems.get(i).getEnd_date()%></td>
</tr>
<%i++;  }  %>
</table>
</div>

</body>
</html>