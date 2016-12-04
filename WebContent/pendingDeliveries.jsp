<%@ page language="java" import="com.group2.bean.OrderDetails,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul><li><form name="myform" action="staff.jsp"><a href="#" onclick="document.forms.myform.submit()">Home</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="PendingDeliveries">Pending Deliveries</a></form></li>
<li><form name="myform2" action="ServletController" method="get"><a href="#" onclick="document.forms.myform2.submit()"><input type="hidden" name="what" value="PendingReturns">Pending Returns</a></form></li>
</ul>

<% List<OrderDetails> orderItems = (List<OrderDetails>)session.getAttribute("PendingItems"); %>
<%int i=0;int size=orderItems.size(); %>
<%if(size==0){ %>
<center><h3>No Deliveries Pending</h3></center>
<%} %>

<form action="ServletController" method="post">
  	
<table>
<tr><th></th>
<th>Model</th>
<th>Brand</th>
<th>Total Price</th>
<th>Quantity</th>
<th>Order Date</th>
<th>Ordered By</th>
<th></th></tr>

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