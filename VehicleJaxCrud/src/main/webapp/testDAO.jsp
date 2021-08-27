<%@page import="model.Vozilo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	DAO dao = new DAO();
	ArrayList<Vozilo> lv = dao.selecSvihVozila();
	for(Vozilo v : lv){
		out.println(v);
	}
	
	Vozilo v1 = dao.selectVoziloByID(6);
	%>
	<br>	
	<br>
	<br>
	<% 
	out.println(v1);
	%>
	
	<br>	
	<br>
	<br>
	<% 
	// dao.deleteVozilaBrID(12);
	
	%>
	
	
	<br>	
	<br>
	<br>
	<% 
	String proizvodjac = "Mercedes";
	String kategorija = "B";
	int godiste = 1997;
	int kubikaza = 2000;
	int cena = 500;
	Vozilo vInsert = new Vozilo(0, proizvodjac, kategorija, godiste, kubikaza, cena);
	dao.insertVozilaUBazu(vInsert);
	
	%>
	


</body>
</html>

