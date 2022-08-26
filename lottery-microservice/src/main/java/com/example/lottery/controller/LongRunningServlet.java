package com.example.lottery.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LongRunningServlet
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/long")
public class LongRunningServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var asyncContext = request.startAsync(request, response);
		try { TimeUnit.SECONDS.sleep(3);} catch(Exception e) {}
		asyncContext.getResponse().getWriter()
		            .append("Served at: ")
		            .append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
