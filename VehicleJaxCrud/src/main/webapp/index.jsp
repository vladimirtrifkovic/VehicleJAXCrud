<%@page import="model.DAO"%>
<%@page import="model.Vozilo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PREGLED VOZILA</title>
</head>
<%
	DAO dao = new DAO();
	ArrayList<Vozilo> lv = dao.selecSvihVozila(); 

%>

<body>
	${requestScope.msg}<br>
	<h1>ONE PAGE CRUD</h1>
	<form action="Vozila" method="post">
		PROIZVODJAC<br>
		<input type="text" name="proizvodjac" value="${requestScope.voziloEdit.proizvodjac}" ><br>
		KATEGORIJA<br>
		<input type="text" name="kategorija" value="${requestScope.voziloEdit.kategorija}" ><br>
		GODISTE<br>
		<input type="text" name="godiste" value="${requestScope.voziloEdit.godiste}"><br>
		KUBIKAZA<br>
		<input type="text" name="kubikaza" value="${requestScope.voziloEdit.kubikaza}"><br>
		CENA<br>
		<input type="text" name="cena" value="${requestScope.voziloEdit.cena}"><br>
		<input type="hidden" name="id" value="${requestScope.voziloEdit.id_vozila}"><br>
		<input type="submit" name="action" value="${requestScope.voziloEdit !=null ? "Edit" :"Insert" }"> 
	</form>
	<br>
	
	<table border="1" width="80%" align="center">
		<thead>
			<tr>
				<th>ID</th><th>PROIZVODJAC</th><th>KATEGORIJA</th><th>GODISTE</th><th>KUBIKAZA</th><th>CENA</th><th colspan="2">AKCIJE</th>
			</tr>
		</thead>
			<tbody>
			
			<% for(Vozilo v : lv){ %>
			<tr>
				<td><%=v.getId_vozila()%></td>
				<td><%=v.getProizvodjac() %></td>
				<td><%=v.getKategorija() %></td>
				<td><%=v.getGodiste() %></td>
				<td><%=v.getKubikaza() %></td>
				<td><%=v.getCena() %></td>
				<td><a href="Vozila?action=Delete&id=<%=v.getId_vozila() %>">DELETE</a></td>
				<td><a href="Vozila?action=Edit&id=<%=v.getId_vozila()%>">EDIT</a></td>
				</tr>
				<%} %>
			</tbody>
	</table>
</body>
</html>