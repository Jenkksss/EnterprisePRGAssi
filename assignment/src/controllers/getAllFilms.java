package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class getAllFilms
 */
public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getAllFilms() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String format = request.getParameter("format");
		System.out.println(format);

		// Connect to database and get all films
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		request.setAttribute("films", allFilms);

		String outputPage;

		if ("xml".equals(format)) {

			response.setContentType("text/xml");

			outputPage = "/jsp/films-xml.jsp";

		} else if ("json".equals(format)) {

			response.setContentType("application/json");

			outputPage = "/jsp/films-json.jsp";

		} else {

			response.setContentType("text/plain");

			outputPage = "/jsp/films-plain.jsp";

		}

		RequestDispatcher dispatcher =

				request.getRequestDispatcher(outputPage);

		dispatcher.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
