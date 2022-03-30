<%@page import="java.util.ArrayList"%>
<%@page import="model.Agenda"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Agenda> agenda = (ArrayList<Agenda>) request.getAttribute("agenda");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Listagem da Agenda</title>
</head>
<body>
<h2>Listagem da Agenda</h2>



	<%if(request.getParameter("mensagem") != null){ %>
		
		<div class="alert alert-danger" role="alert">
		
		<% out.println(request.getParameter("mensagem"));%>
		
		</div>
		
	<%}%>

<br>
<a href="listarAgendaCancelada"><img width="20.px" src="icons/iconfinder_filter_4696684.png">Listar somente registros cancelados</a>
<br>
<a href="listarAgendaRealizada"><img width="20.px" src="icons/iconfinder_filter_4696684.png">Listar somente registros realizados</a>
<br>
<a href="listarAgenda"><img width="20.px" src="icons/iconfinder_filter_4696684.png">Listar todos os registros</a>
<br>

<table  class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Data</th>
      <th scope="col">Hora</th>
      <th scope="col">Situação</th>
      <th scope="col">Data Situação</th>
      <th scope="col">Observações</th>
      <th scope="col">Funcionário</th>
      <th scope="col">Vacina</th>
    </tr>
  </thead>
  <tbody>
  <%
  	for(Agenda ag : agenda){
  %>
    <tr>
    <td><%=ag.getData() %></td>
    <td><%=ag.getHora() %></td>
      <th scope="row"><%
      switch(ag.getSituacao()){
      case 'A':
          out.println("Agendado");
          break;
       case 'C':
          out.println("Cancelado");
          break;
       case 'R':
          out.println("Realizado");
          break;
       default:
          out.println("null");
    }
      %></th>
      <td><%=ag.getData_situacao() %></td>
      <td><%=ag.getObservacoes() %></td>
      <td><%=ag.getFuncionario().getNome() %></td>
      <td><%=ag.getVacina().getTitulo() %></td>
      
      <td>
      		<a href="excluirAgenda?codigo=<%=ag.getId() %>">
				<img width="20.px" alt="Excluir" src="icons/iconfinder_error_6586152.png">
			</a>
			<a href="cancelarAgenda?codigo=<%=ag.getId() %>">
				<img width="20.px" alt="Cancelar" src="icons/iconfinder_error_6586152.png">
			</a>
      		<a href="executarAgenda?codigo=<%=ag.getId() %>">
      			<img width="20.px" alt="Finalizar Vacinação" src="icons/iconfinder_check_6586148.png">
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