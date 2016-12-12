<%@ page language="java" 
import="java.util.List,com.group2.bean.*,com.group2.data.access.LoginCheckDAO" 
contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
<li><form name="myform" action="manager.jsp"><a href="#" onclick="document.forms.myform.submit()" style="color:black">Home</a></form></li>
<li><form name="salesForm" action="salesReport.jsp"><a href="#" onclick="document.forms.salesForm.submit()">Sales Report</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="DeleteEmployee">Delete Employee</a></form></li></ul>

	<form action="ServletController" method="get">
		<label>Please select the date range to view particular sales in that range:</label>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<p>
			Start Date: <input type="text" id="datepicker" name="startDate">
		</p>
		<script>
			$(function() {
				$("#datepicker").datepicker({ dateFormat: "yy-mm-dd"})
			});
		</script>
		<p>
			End Date: <input type="text" id="datepicker1" name="endDate">
		</p>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
			$(function() {
				$("#datepicker1").datepicker({ dateFormat: "yy-mm-dd"});
			});
		</script>
		<% LoginCheckDAO loginCheckDao = new LoginCheckDAO();%>
		<% List<Customer> customerList = loginCheckDao.getAllCustomersNames(); %>
		<select name="customerName">
				<option value="All_All">--All--</option>
			<%for(Customer customer : customerList) { %>
				<option value="<%= customer.getFname()+"_"+customer.getLname()%>"><%=customer.getFname()+" "+customer.getLname() %></option>
			<%} %>
		</select>
		<input type="submit" value="Generate Sales Report" name="what"
			id="dropdown">
</form>
<div>
<% List<OrderDetails> report = (List<OrderDetails>)request.getAttribute("SalesReport"); %>
<%if(report != null) { %>
<%int i=0;int size=report.size(); %>
<%if(size==0){ %>
<center><h3>----No Orders in these dates----</h3></center>
<%} %>

<%if(size!=0){ %>

<table style="allign:center">
<tr>
<th>Order Date</th>
<th>Product</th>
<th>Quantity</th>
<th>Price</th>
<th>Customer</th>
</tr>
<% while (i<size) {%>
<tr>
<td><%=report.get(i).getOrder_date()%></td>
<td><%=report.get(i).getModel_name()%></td>
<td><%=report.get(i).getQuantity()%></td>
<td><%=report.get(i).getTotalUnitPrice()%></td>
<td><%=report.get(i).getCus_fname()%></td>
</tr>
<%i++;  }  %>
</table>
<%}%>
<%}%>
</div>

</body>
</html>