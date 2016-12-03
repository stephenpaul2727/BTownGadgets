<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<li><form name="myform4" action="ServletController" method="post"><a href="#" onclick="document.forms.myform4.submit()"><input type="hidden" name="action" value="Logout"/>Logout</a></form></li>
</ul>
</div>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
<tr><th>Model</th>
<th>Brand</th>
<th>Price</th>
<th>Quantity</th>
<th>Total</th>
<th></th></tr>
<jsp:useBean id="Cart" scope="session" class="com.group2.bean.Cart" />
<c:if test="${Cart.itemCount==0}">
  <tr>
  <td colspan="4"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">- Cart is currently empty -<br/></font>
  </tr>
</c:if>

<c:forEach var="cartItem" items="${Cart.cartItems}" varStatus="counter">
  <form name="item" method="post" action="ServletController">
  <tr>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.model_name}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.brand}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.price}"/></b></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	<input type='hidden' name='itemIndex' value='<c:out value="${counter.count}"/>'>
    	<input type='number' name="updatedQuantity" min="1" max="${cartItem.quantity}" value='<c:out value="${cartItem.selectedQuantity}"/>' size='2'>
    	<input type="submit" name="action" value="Update"></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">$<c:out value="${cartItem.totalUnitPrice}"/></font></td>
    <td><input type="submit" name="action" value="Delete"></td>
  </tr>
  </form> 
</c:forEach>
</table><br/><br/>
<b>Total Cart Price: </b><c:out value="${Cart.totalCartPrice}"></c:out>
<c:if test="${Cart.itemCount!=0}">
  <form action="ServletController" method="post">
  	<input type="hidden" name="action" value="PlaceOrder">
	<input type="submit" value="Place Order" >
  </form>
</c:if>

</body>
</html>