<%@ page language="java" import="com.group2.bean.Employee,com.group2.data.access.OrdersDAO,java.util.List" contentType="text/html; charset=UTF-8"
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
<title>Insert title here</title>
<script type="text/javascript">
function checkDeliveries(status) {
	if(status == true) {
		var r = confirm("Employee has Pending Deliveries/Returns. Do you want these orders to be re-allocated ?");
		if(r == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
</script>
</head>
<body style="background-color:#d7dfed">

<h1>
<table class="table" style="border:null;cellspacing=0" >
<thead>
<th><img src="Images/btowngadgetslogo.png" class="img-responsive" width="250dp"/></th>
<th><form name="myform" action="login.jsp"><button style="float:right;margin-right:5px" type="submit" class="btn btn-danger">Logout</button></form>
</th>
</thead>
</table>
</h1>
<span></span>
<div class="container-fluid" id="navbar">

<div style="margin-top:2%" class="row">
<div class="col-md-3" style="background-color:#FF9999"><form name="myform7" action="manager.jsp"><a href="#" onclick="document.forms.myform7.submit()" style="color:black">Home</a></form></div>

<div class="col-md-3" style="background-color:#b0cc86">
<form name="salesForm" action="salesReport.jsp"><a href="#" style="color:black" onclick="document.forms.salesForm.submit()">Sales Report</a></form></div>

<div class="col-md-3" style="background-color:#6f96d6">


<form name="myform1" action="ServletController" method="get"><a style="color:black" href="#" onclick="document.forms.myform1.submit()"><input type="hidden" name="what" value="DeleteEmployee">Delete Employee</a></form>


</div>

<div class="col-md-3" style="background-color:#86ccb4">

<div class="dropdown">
<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color:black">About us</a>
<ul class="dropdown-menu" style="background-color:#86ccb4;width:184px">
<li><a href="#">Contact Details</a></li>
<li><a href="#">Location</a></li>

</ul>
</div>

</div>

<br/><br/>

</div>
</div>

<%List<Employee> employeeList = (List<Employee>)session.getAttribute("EmployeesStaff"); %>
<% OrdersDAO ordersDao = new OrdersDAO(); %>
<table class="table table-striped">
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