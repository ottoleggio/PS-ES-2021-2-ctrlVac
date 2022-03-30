<%@page import="java.util.ArrayList"%>
<%@page import="model.Agenda"%>
<%@page import="model.Vacina"%>
<%@page import="model.Funcionario"%>
<%@page import="persistence.VacinaDAO"%>
<%@page import="persistence.FuncionarioDAO"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vacinacao");
	EntityManager em = fabrica.createEntityManager();
	VacinaDAO vadao = new VacinaDAO(em);
	List<Vacina> vacinas = vadao.listarTodos();
	FuncionarioDAO udao = new FuncionarioDAO(em);
	List<Funcionario> funcionarios = udao.listarTodos();
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

<form method="post" class="form-horizontal" action="incluirAgenda">
<fieldset>

<!-- Form Name -->
<legend>Incluir agendamento</legend>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="vacina">Vacina</label>
  <div class="col-md-4">
    <select id="vacina" name="vacina" class="form-control">
 		<%for(Vacina va : vacinas){
 			%><option value="<%=va.getId()%>"><%=va.getTitulo()%></option><%
 			
 		}%>
    </select>
  </div>
  
</div>
<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="funcionario">Funcionario</label>
  <div class="col-md-4">
    <select id="funcionario" name="funcionario" class="form-control">
 		<%for(Funcionario us : funcionarios){
 			%><option value="<%=us.getId()%>"><%=us.getNome()%></option><%
 			
 		}%>
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="data">Data</label>  
  <div class="col-md-4">
  <input id="data" name="data" type="text" placeholder="01/01/2021" class="form-control input-md">
  <span class="help-block">Data do agendamento</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="hora">Hora</label>  
  <div class="col-md-4">
  <input id="hora" name="hora" type="text" placeholder="12:00" class="form-control input-md">
  <span class="help-block">Hora do agendamento</span>  
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="observacoes">Observacoes</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="observacoes" name="observacoes"></textarea>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Enviar</button>
  </div>
</div>


</fieldset>
</form>


<br><br>
<a href="index.jsp"><img width="20.px" src="icons/iconfinder_home_6586106.png"> Página Inicial</a>

</body>
</html>