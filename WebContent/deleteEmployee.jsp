<%@ page language="java" import="com.group2.bean.Employee,com.group2.data.access.OrdersDAO,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function checkDeliveries(status) {
	if(status == true) {
		var r = confirm("Employee has Pending Deliveries/Returns. Do you want to re-allocate those orders ?");
		if(r == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
</script>
</head>
<body>
<ul>
<li><form name="myform" action="manager.jsp"><a href="#" onclick="document.forms.myform.submit()" style="color:black">Home</a></form></li>
<li><form name="salesForm" action="salesReport.jsp"><a href="#" onclick="document.forms.salesForm.submit()">Sales Report</a></form></li>
<li><form name="myform1" action="ServletController" method="get"><a href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="DeleteEmployee">Delete Employee</a></form></li></ul>

<%List<Employee> employeeList = (List<Employee>)session.getAttribute("EmployeesStaff"); %>
<% OrdersDAO ordersDao = new OrdersDAO(); %>
<table>
<tr><th>Employee ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
<th></th></tr>
<%int size=employeeList.size(); %>
<%if(size==0){ %>
<center><h3>No Employees With Designation:Staff</h3></center>
<%} %>
<%for(Employee employee : employeeList) { %>
<tr><td><%=employee.getEmp_id() %></td>
<td><%=employee.getFname() %></td>
<td><%=employee.getLname() %></td>
<td><%=employee.getEmail() %></td>
<td>
<form name="myform" action="ServletController" method="get">
<input type="hidden" name="what" value="DeleteEmp">
<input type="submit" value="Delete" onclick="return checkDeliveries(<%=ordersDao.checkPendingDeliveries(employee.getEmp_id()) %>);">
<input type="hidden" name="employeID" value="<%=employee.getEmp_id()%>">
</form></td></tr>
<%} %>
</table>
</body>
</html>