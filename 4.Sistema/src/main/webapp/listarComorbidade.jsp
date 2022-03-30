<%@page import="java.util.ArrayList"%>
<%@page import="model.Comorbidade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Comorbidade> comorbidade = (ArrayList<Comorbidade>) request.getAttribute("comorbidade");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Listagem da Comorbidades</title>
</head>
<body>
<h2>Listagem de Comorbidades</h2>



	<%if(request.getParameter("mensagem") != null){ %>
		
		<div class="alert alert-danger" role="alert">
		
		<% out.println(request.getParameter("mensagem"));%>
		
		</div>
		
	<%}%>


<table  class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Nome</th>
    </tr>
  </thead>
  <tbody>
  <%
  	for(Comorbidade al : comorbidade){
  %>
    <tr>
    <td><%=al.getNome() %></td>

    </tr>
    <%} %>
    
  </tbody>
</table>


<br><br>
<a href="index.jsp"><img width="20.px" src="icons/iconfinder_home_6586106.png"> Página Inicial</a>

</body>
</html>