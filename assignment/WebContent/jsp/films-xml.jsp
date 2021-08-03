<%@ page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ page import="models.FilmList"%>
<%@ page import="javax.xml.bind.JAXBContext"%>
<%@ page import="javax.xml.bind.JAXBException"%>
<%@ page import="javax.xml.bind.Marshaller"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Film"%>



<%

//parse the xml and send back the result
	try {
	Marshaller marshaller = JAXBContext.newInstance(FilmList.class).createMarshaller();

	FilmList films = new FilmList();

	ArrayList<Film> requestFilms = new ArrayList<Film>();

	requestFilms = (ArrayList<Film>) request.getAttribute("films");

	films.setFilmList(requestFilms);

	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	marshaller.marshal(films, out);

} catch (JAXBException e) {
	throw new RuntimeException("failed to marshal XML", e);
}
%>