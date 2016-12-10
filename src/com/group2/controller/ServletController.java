package com.group2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group2.bean.Cart;
import com.group2.bean.CartItem;
import com.group2.bean.Customer;
import com.group2.bean.Employee;
import com.group2.bean.OrderDetails;
import com.group2.bean.Product;
import com.group2.data.access.LoginCheckDAO;
import com.group2.data.access.OrdersDAO;
import com.group2.data.access.ProductsDAO;

/**
 * Servlet implementation class ServletController
 */
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN THE SERVLET ......");
		Customer customer = new Customer();
		Employee employee = new Employee();
		Product product = new Product();
		CartItem cartItem = new CartItem();
		Cart cart = new Cart();
		LoginCheckDAO loginDao = new LoginCheckDAO();
		ProductsDAO proDao = new ProductsDAO();
		OrdersDAO ordersDao = new OrdersDAO();
		List<Product> proList = new ArrayList<Product>();
		List<OrderDetails> orderItems = new ArrayList<OrderDetails>();
		String here = request.getParameter("what");
		try {
			if(here.equals("login")) {
				String username = request.getParameter("username");
				System.out.println("Username::"+username);
				String password = request.getParameter("password");
				System.out.println("Password::"+password);
				String designation = loginDao.verifyLoginDetails(username, password);
				HttpSession session = request.getSession();
				if(designation != null) {
					System.out.println("Designation====>"+designation);
					if(designation.equals("Customer")) {
						customer = loginDao.getCustomerDetails(username);
						session.setAttribute("User", customer);
						response.sendRedirect("customer.jsp");
					} else {
						employee = loginDao.getEmployeeDetails(username);
						session.setAttribute("User", employee);
						if(designation.equals("Staff")) {
							response.sendRedirect("staff.jsp");
						} else if(designation.equals("Supervisor")) {
							response.sendRedirect("supervisor.jsp");
						} else if(designation.equals("SalesManager")) {
							response.sendRedirect("salesmanager.jsp");
						}
					}
					
				} else {
					request.setAttribute("errorMessage", "Invalid Credentials");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else if(here.equals("ViewProducts")) {
				System.out.println("In View Products Controller");
				proList = proDao.getAllProducts();
				request.setAttribute("ProductsList", proList);
				String redirectUrl = "viewProducts.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(here.equals("ViewPurchaseHistory")) {
				HttpSession session = request.getSession();
				customer = (Customer)session.getAttribute("User");
				orderItems = ordersDao.getPastOrders(customer.getCus_id());
				request.setAttribute("OrderItems", orderItems);
				String redirectUrl = "viewPurchaseHistory.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(here.equals("ViewItem")) {
				int productId = Integer.parseInt(request.getParameter("productId"));
				product = proDao.getProductItem(productId);
				request.setAttribute("ProductItem", product);
				String redirectUrl = "viewItem.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(here.equals("AddToCart")) {
				product = (Product)request.getSession().getAttribute("CartProduct");
				int selectedQuantity = Integer.parseInt(request.getParameter("selectedQuantity"));
				System.out.println("=======>SQ::"+selectedQuantity);
				System.out.println("=======>Pro::"+product.getModel_name());
				float totalUnitPrice = selectedQuantity*product.getPrice();
				cartItem = setCartItemValues(product);
				cartItem.setSelectedQuantity(selectedQuantity);
				cartItem.setTotalUnitPrice(totalUnitPrice);
				HttpSession session = request.getSession();
				Object objCart = session.getAttribute("Cart");
				if(objCart != null) {
					cart = (Cart)objCart;
				} else {
					session.setAttribute("Cart", cart);
				}
				cart.addToCart(cartItem);
				session.setAttribute("Cart", cart);
				String redirectUrl = "shoppingCart.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(here.equals("PendingDeliveries")) {
				HttpSession session = request.getSession();
				employee = (Employee)session.getAttribute("User");
				orderItems = ordersDao.getAssignedDeliveriesByStatus("Pending", employee.getEmp_id());
				session.setAttribute("PendingItems", orderItems);
				String redirectUrl = "pendingDeliveries.jsp";
				response.sendRedirect(redirectUrl);
			} else if(here.equals("PendingReturns")) {
				HttpSession session = request.getSession();
				employee = (Employee)session.getAttribute("User");
				orderItems = ordersDao.getAssignedDeliveriesByStatus("Delivered", employee.getEmp_id());
				session.setAttribute("PendingReturns", orderItems);
				String redirectUrl = "pendingReturns.jsp";
				response.sendRedirect(redirectUrl);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected CartItem setCartItemValues(Product product) {
		CartItem ci = new CartItem();
		ci.setPro_id(product.getPro_id());
		ci.setModel_name(product.getModel_name());
		ci.setBrand(product.getBrand());
		ci.setPrice(product.getPrice());
		ci.setQuantity(product.getQuantity());
		return ci;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN THE Shopping Cart Controller ......");
		Customer customer = new Customer();
		Employee employee = new Employee();
		OrdersDAO orderDao = new OrdersDAO();
		LoginCheckDAO loginDao = new LoginCheckDAO();
		Cart cart = new Cart();
		List<OrderDetails> orderItems = new ArrayList<OrderDetails>();
		String action = request.getParameter("action");
		try {
			if(action.equals("SignUp")) {
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String email = request.getParameter("email");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String address = request.getParameter("address");
				customer.setFname(firstname);
				customer.setLname(lastname);
				customer.setEmail(email);
				customer.setAddress(address);
				customer.setUname(username);
				customer.setPassword(password);
				loginDao.addCustomer(customer);
				String redirectUrl = "login.jsp";
				response.sendRedirect(redirectUrl);
			} else if(action.equals("Update")) {
				int itemIndex = Integer.parseInt(request.getParameter("itemIndex"));
				int updatedQuantity = Integer.parseInt(request.getParameter("updatedQuantity"));
				HttpSession session = request.getSession();
				Object objCart = session.getAttribute("Cart");
				if(objCart != null) {
					cart = (Cart) objCart;
				}
				cart.updateCart(itemIndex, updatedQuantity);
				session.setAttribute("Cart", cart);
				String redirectUrl = "shoppingCart.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(action.equals("Delete")) {
				int itemIndex = Integer.parseInt(request.getParameter("itemIndex"));
				HttpSession session = request.getSession();
				Object objCart = session.getAttribute("Cart");
				if(objCart != null) {
					cart = (Cart) objCart;
				}
				cart.deleteCart(itemIndex);
				session.setAttribute("Cart", cart);
				String redirectUrl = "shoppingCart.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(action.equals("PlaceOrder")) {
				HttpSession session = request.getSession();
				cart = (Cart)session.getAttribute("Cart");
				customer = (Customer)session.getAttribute("User");
				List<CartItem> cartItems = cart.getCartItems();
				orderDao.insertOrderItems(cartItems, customer.getCus_id());
				session.removeAttribute("Cart");
				String redirectUrl = "customer.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			} else if(action.equals("ItemsDelivered")) {
				HttpSession session = request.getSession();
				List<OrderDetails> selectedRecords = new ArrayList<OrderDetails>();
				orderItems = (List<OrderDetails>)session.getAttribute("PendingItems");
				String[] itemIndexes = request.getParameterValues("selectedItems");
				List<Integer> selectedIndexes = new ArrayList<Integer>();
				for(String s : itemIndexes) {
					selectedIndexes.add(Integer.valueOf(s));
				}
				
				for(int i = 0; i<orderItems.size(); i++) {
					if(selectedIndexes.contains(i)) {
						selectedRecords.add(orderItems.get(i));
					}
				}
				orderItems.removeAll(selectedRecords);
				orderDao.updateDeliveryStatus(selectedRecords);
				session.setAttribute("PendingItems", orderItems);
				employee = (Employee) session.getAttribute("User");
				List<OrderDetails> pendingReturns = orderDao.getAssignedDeliveriesByStatus("Delivered", employee.getEmp_id());
				session.setAttribute("PendingReturns", pendingReturns);
				String redirectUrl = "pendingDeliveries.jsp";
				response.sendRedirect(redirectUrl);	
			} else if(action.equals("ItemsReturned")) {
				HttpSession session = request.getSession();
				List<OrderDetails> selectedRecords = new ArrayList<OrderDetails>();
				orderItems = (List<OrderDetails>)session.getAttribute("PendingReturns");
				String[] itemIndexes = request.getParameterValues("selectedItems");
				List<Integer> selectedIndexes = new ArrayList<Integer>();
				for(String s : itemIndexes) {
					selectedIndexes.add(Integer.valueOf(s));
				}
				
				for(int i = 0; i<orderItems.size(); i++) {
					if(selectedIndexes.contains(i)) {
						selectedRecords.add(orderItems.get(i));
					}
				}
				orderItems.removeAll(selectedRecords);
				orderDao.updateReturnStatus(selectedRecords);
				session.setAttribute("PendingReturns", orderItems);
				String redirectUrl = "pendingReturns.jsp";
				response.sendRedirect(redirectUrl);	
			} else if(action.equals("Logout")) {
					request.getSession().invalidate();
					String redirectUrl = "login.jsp";
					response.sendRedirect(redirectUrl);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
