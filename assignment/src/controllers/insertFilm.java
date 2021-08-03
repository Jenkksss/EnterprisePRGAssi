package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class insertFilm
 */
public class insertFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertFilm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Film newFilm = new Film();
		int id = Integer.parseInt(request.getParameter("id"));
		newFilm.setId(id);
		newFilm.setTitle(request.getParameter("title"));
		int year = Integer.parseInt(request.getParameter("year"));
		newFilm.setYear(year);
		newFilm.setDirector(request.getParameter("director"));
		newFilm.setStars(request.getParameter("stars"));
		newFilm.setReview(request.getParameter("review"));
		FilmDAO dao = new FilmDAO();
		dao.insertFilm(newFilm);
		System.out.println("Film Inserted");
	}

			
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
