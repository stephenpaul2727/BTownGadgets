<%@ page language="java" import="com.group2.bean.Customer" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>It is Customer</p>
<% Customer user = (Customer)session.getAttribute("User"); %>
<p>Welcome <%= user.getFname()%></p>
<ul><li><form name="myform" action="customer.jsp"><a href="#" onclick="document.forms.myform.submit()">Home</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="ViewProducts">View Products</a></form></li>
<li><form name="myform2" action="ServletController" method="get"><a href="#" onclick="document.forms.myform2.submit()"><input type="hidden" name="what" value="ViewPurchaseHistory"/>Purchase History</a></form></li>
<li><form name="myform3" action="shoppingCart.jsp"><a href="#" onclick="document.forms.myform3.submit()">Shopping Cart</a></form></li>
<li><form name="myform4" action="ServletController" method="post"><a href="#" onclick="document.forms.myform4.submit()"><input type="hidden" name="action" value="Logout"/>Logout</a></form></li>
</ul>

</body>
</html>