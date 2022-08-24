package com.example.lottery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lottery.model.LotteryViewModel;
import com.example.lottery.service.LotteryService;

/**
 * Servlet implementation class LotteryController
 */
@WebServlet(urlPatterns = "/draw")
public class LotteryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
    private LotteryViewModel lotteryViewModel;
	@Inject
	private LotteryService lotteryService;
	
    public LotteryController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var lotteryNumbers = lotteryService.draw(60, 6);
		lotteryViewModel.addLotteryNumbers(lotteryNumbers);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
