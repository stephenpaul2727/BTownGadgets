<%@ page language="java" import="com.group2.bean.Employee" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Employee user = (Employee)session.getAttribute("User"); %>
<p>Welcome <%= user.getFname()%></p>

<ul>
<li><form name="myform" action="manager.jsp"><a href="#" onclick="document.forms.myform.submit()" style="color:black">Home</a></form></li>
<li><form name="salesForm" action="salesReport.jsp"><a href="#" onclick="document.forms.salesForm.submit()">Sales Report</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="DeleteEmployee">Delete Employee</a></form></li></ul>
</body>
</html>