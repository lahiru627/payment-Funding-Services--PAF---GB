package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Funding;

/**
 * Servlet implementation class FundingAPI
 */
@WebServlet("/FundingAPI")
public class FundingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Funding fundingObj = new Funding();
	
    public FundingAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String outputString = fundingObj.insertFunding(
				request.getParameter("sponserCode"),
				request.getParameter("sponserName"),  
				request.getParameter("projectCode"), 
				request.getParameter("projectName"), 
				request.getParameter("fundingAmount"),
				request.getParameter("fundingStatus"));
		response.getWriter().write(outputString);
		
		doGet(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		String outputString = fundingObj.updateFunding(
				paras.get("fundingID").toString(),
				paras.get("sponserCode").toString(),
				paras.get("sponserName").toString(),
				paras.get("projectCode").toString(),
				paras.get("projectName").toString(),
				paras.get("fundingAmount").toString(),
				paras.get("fundingStatus").toString()); 

		response.getWriter().write(outputString);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = fundingObj.deleteFunding(paras.get("fundingID").toString());
		response.getWriter().write(output); 
	}


	// Convert request parameters to a Map
	
	private Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		
		try {			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		} catch (Exception e) {
		  }
		
		return map;
	}

}
