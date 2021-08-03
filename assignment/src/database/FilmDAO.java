package database;

import java.util.ArrayList;

import models.Film;
import models.FilmList;

import java.sql.*;

public class FilmDAO {

	FilmList oneFilmList = null;
	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "jenkinsk";
	String password = "triLjenb6";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

	public FilmDAO() {
	}

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		System.out.println(allFilms);
		return allFilms;
	}

	public Film getFilmByID(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}

	public ArrayList<Film> getFilmByTitle(String Film) {
		ArrayList<Film> fList = new ArrayList<Film>();
		openConnection();

		try {
			String selectSQL = "select * from films where title like '%" + Film + "%';";
			System.out.println(selectSQL);
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			System.out.println(rs1.getFetchSize());
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				fList.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return fList;
	}

	public int insertFilm(Film f){
		   int rs1 = 0;
			openConnection();
		    // Create insert statement and execute it
			try{
			    String insertSQL = "INSERT INTO films (title, year, director, stars, review) VALUES ('"+f.getTitle()+"','"+f.getYear()+"','"+f.getDirector()+"','"+f.getStars()+"','"+f.getReview()+"');";
			    rs1 = stmt.executeUpdate(insertSQL);

		    // Retrieve the results
			    System.out.println("Inserted "+rs1+" records.");
			    stmt.close();
			    closeConnection();
			} catch(SQLException se) { System.out.println(se); }

		return rs1;

	}
	public int deleteFilm(int id){
		   int rs1 = 0;
			openConnection();
		    // Create delete statement and execute it
			try{
			    String updateSQL = "DELETE FROM films WHERE id = '"+id+"';";
			    rs1 = stmt.executeUpdate(updateSQL);

		    // Retrieve the results
			    System.out.println("Deleted "+rs1+" records.");
			    stmt.close();
			    closeConnection();
			} catch(SQLException se) { System.out.println(se); }

		return rs1;
	   }

	  public int updateFilm(Film f){
		   int rs1 = 0;
			openConnection();
		    // Create update statement and execute it
			try{
				String updateSQL = "UPDATE films SET title ="+ f.getTitle()+ ", year ="+ f.getYear()+ ", director ="+ f.getDirector()+
						", stars ="+f.getStars()+", review ="+f.getReview()+" where id ="+f.getId()+";";
				  rs1 = stmt.executeUpdate(updateSQL);
		    // Retrieve the results
			    System.out.println("Updated "+rs1+" records.");
			    stmt.close();
			    closeConnection();
			} catch(SQLException se) { System.out.println(se); }

		return rs1;
	   }
}