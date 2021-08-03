<%@ page contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ page import="models.Film"%>
<%@ page import="java.util.ArrayList"%>
<%
	String plainTxt = "";
//parse the plain text and send back the result
ArrayList<Film> allFilms = (ArrayList<Film>) request.getAttribute("films");
for (Film f : allFilms) {
	plainTxt += "Film: " + f.getId() + "\nTitle: " + f.getTitle() + "\nYear: " + f.getYear() + "\nDirector: "
	+ f.getDirector() + "\nStars: " + f.getStars() + "\nReview: " + f.getReview() + "\n\n";
	
}
out.print(allFilms);
%>
