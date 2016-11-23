package com.group2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group2.bean.Product;
import com.group2.data.access.LoginCheckDAO;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN THE SERVLET ......");
		LoginCheckDAO loginDao = new LoginCheckDAO();
		ProductsDAO proDao = new ProductsDAO();
		List<Product> proList = new ArrayList<Product>();
		String here = request.getParameter("what");
		try {
			if(here.equals("login")) {
				String username = request.getParameter("username");
				System.out.println("Username::"+username);
				String password = request.getParameter("password");
				System.out.println("Password::"+password);
				String designation = loginDao.verifyLoginDetails(username, password);
				if(designation != null) {
					System.out.println("Designation====>"+designation);
					if(designation.equals("Customer")) {
						response.sendRedirect("customer.jsp");
					} else if(designation.equals("Staff")) {
						response.sendRedirect("staff.jsp");
					} else if(designation.equals("Supervisor")) {
						response.sendRedirect("supervisor.jsp");
					} else if(designation.equals("SalesManager")) {
						response.sendRedirect("salesmanager.jsp");
					}
				} else {
					request.setAttribute("errorMessage", "Invalid Credentials");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} else if(here.equals("ViewProducts")) {
				proList = proDao.getAllProducts();
				request.setAttribute("ProductsList", proList);
				String redirectUrl = "viewProducts.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
				rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
