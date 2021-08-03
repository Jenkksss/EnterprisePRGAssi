package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class getFilm
 */
public class getFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFilm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String format = request.getParameter("format");
		FilmDAO dao = new FilmDAO();
		String searchFilmName = request.getParameter("filmname");
		ArrayList<Film> fList = dao.getFilmByTitle(searchFilmName);
		Iterator<Film> it = fList.iterator();
		while (it.hasNext()) {
			request.setAttribute("films", fList);

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
