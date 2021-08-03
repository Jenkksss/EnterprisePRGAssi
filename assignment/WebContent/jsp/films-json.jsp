<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="javax.servlet.http.*" %>

<% 

//parse the json and send back the result
Gson gson = new Gson();
String requestFilms = gson.toJson(request.getAttribute("films"));



%>
<%= requestFilms %>



