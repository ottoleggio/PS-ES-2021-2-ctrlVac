<%@page import="java.util.ArrayList"%>
<%@page import="model.Vacina"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Vacina> vacina = (ArrayList<Vacina>) request.getAttribute("vacina");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Listagem da Vacinas</title>
</head>
<body>
<h2>Listagem de Vacinas</h2>



	<%if(request.getParameter("mensagem") != null){ %>
		
		<div class="alert alert-danger" role="alert">
		
		<% out.println(request.getParameter("mensagem"));%>
		
		</div>
		
	<%}%>


<table  class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Título</th>
      <th scope="col">Descrição</th>
      <th scope="col">Doses</th>
      <th scope="col">Periodicidade</th>
      <th scope="col">Intervalo</th>
    </tr>
  </thead>
  <tbody>
  <%
  	for(Vacina va : vacina){
  %>
    <tr>
    <td><%=va.getTitulo() %></td>
    <td><%=va.getDescricao() %></td>
    <td><%=va.getDoses() %></td>
    <th scope="row"><%
      switch(va.getPeriodicidade()){
      case 1:
          out.println("Diário");
          break;
       case 2:
          out.println("Semanal");
          break;
       case 3:
           out.println("Mensal");
           break;
       case 4:
           out.println("Anual");
           break;
       default:
          out.println("null");
    }
      %></th>
    <td><%=va.getIntervalo() %></td>

<td>
      		<a href="excluirVacina?codigo=<%=va.getId() %>">
				<img width="20.px" alt="Excluir" src="icons/iconfinder_error_6586152.png">
			</a>
      </td>

    </tr>
    <%} %>
    
  </tbody>
</table>


<br><br>
<a href="index.jsp"><img width="20.px" src="icons/iconfinder_home_6586106.png"> Página Inicial</a>

</body>
</html>