package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class updateFilm
 */
@WebServlet("/updateFilm")
public class updateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Film updateFilm = new Film();
		int id = Integer.parseInt(request.getParameter("id"));
		updateFilm.setId(id);
		updateFilm.setTitle(request.getParameter("title"));
		int year = Integer.parseInt(request.getParameter("year"));
		updateFilm.setYear(year);
		updateFilm.setDirector(request.getParameter("director"));
		updateFilm.setStars(request.getParameter("stars"));
		updateFilm.setReview(request.getParameter("review"));
		FilmDAO dao = new FilmDAO();
		dao.updateFilm(updateFilm);
		System.out.println("Film Updated");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
