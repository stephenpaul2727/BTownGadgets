<%@ page language="java" import="com.group2.bean.Employee" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>It is Staff</p>
<% Employee user = (Employee)session.getAttribute("User"); %>
<p>Welcome <%= user.getFname()%></p>
<p>Designation <%= user.getDesignation()%></p>

<ul><li><form name="myform" action="staff.jsp"><a href="#" onclick="document.forms.myform.submit()">Home</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="PendingDeliveries">Pending Deliveries</a></form></li>
<li><form name="myform2" action="ServletController" method="get"><a href="#" onclick="document.forms.myform2.submit()"><input type="hidden" name="what" value="PendingReturns">Pending Returns</a></form></li>
</ul>

</body>
</html>