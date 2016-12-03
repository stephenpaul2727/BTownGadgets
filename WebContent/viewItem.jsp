<%@ page language="java" import="com.group2.bean.Product,java.util.Map,java.util.List,java.util.ArrayList" contentType="text/html; charset=UTF-8"
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
<% Product product = (Product)request.getAttribute("ProductItem"); %>

<table style="allign:center">
<col width="130">
<tr>
<th>PIC</th>
<th></th>
<th></th></tr>
<tr><td><img src="<%=product.getImagePath() %>" class="img-responsive" width="250dp"/></td>
<td><u>Details</u><br><%=product.getBrand() %><br><%=product.getModel_name() %><br>
Price: <i><%=product.getPrice() %>$</i><br>Available: <%=product.getQuantity() %><br><br><br>
<u>Specifications</u><br>
<% Map<String, String> specifications = product.getSpecifications(); %>
<% List<String> keys = new ArrayList<String>(specifications.keySet()); %>
<%for(String spec_value : keys) { %>
<%=spec_value %>: <%=specifications.get(spec_value) %><br>
<%} %>
<%int quantity = product.getQuantity(); int i = 0; %>
<%if(quantity>0) { %>
<select name="selectedQuantity" form="cart">
<%for(i=1;i<=quantity;i++) { %>
	<option value="<%= i%>"><%=i %></option>
<%} %>
</select>
<form action="ServletController" method="get" id="cart">
  	<input type="hidden" name="what" value="AddToCart">
  	<%session.setAttribute("CartProduct", product); %>
	<input type="submit" value="Add To Cart" >
</form>
<%} %>
</td>
</tr>
</table>
</div>

</body>
</html>