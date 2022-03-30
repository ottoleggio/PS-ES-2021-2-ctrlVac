<%@page import="java.util.ArrayList"%>
<%@page import="model.Funcionario"%>
<%@page import="model.Comorbidade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Funcionario> funcionario = (ArrayList<Funcionario>) request.getAttribute("funcionario");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Listagem da Funcionários</title>
</head>
<body>
<h2>Listagem de Funcionários</h2>



	<%if(request.getParameter("mensagem") != null){ %>
		
		<div class="alert alert-danger" role="alert">
		
		<% out.println(request.getParameter("mensagem"));%>
		
		</div>
		
	<%}%>


<table  class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Data de Nascimento</th>
      <th scope="col">Sexo</th>
      <th scope="col">Logradouro</th>
      <th scope="col">Número</th>
      <th scope="col">Setor</th>
      <th scope="col">Cidade</th>
      <th scope="col">UF</th>
      <th scope="col">Comorbidade</th>
    </tr>
  </thead>
  <tbody>
  <%
  	for(Funcionario us : funcionario){
  %>
    <tr>
    <td><%=us.getNome() %></td>
    <td><%=us.getData_nascimento() %></td>
    <th scope="row"><%
      switch(us.getSexo()){
      case 'M':
          out.println("Masculino");
          break;
       case 'F':
          out.println("Feminino");
          break;
       default:
          out.println("null");
    }
      %></th>
    <td><%=us.getLogradouro() %></td>
    <td><%=us.getNumero() %></td>
    <td><%=us.getSetor() %></td>
    <td><%=us.getCidade() %></td>
    <td><%=us.getUf() %></td>
    <td><%
      if(us.obterComorbidades() == null){
    	  out.println("Sem comorbidades");
      }else{
    	  List<Comorbidade> comorbidades = us.obterComorbidades();
    	  for(Comorbidade al : comorbidades){
				out.println(al.getNome());
    	  }
      }
      %></td>
      
      <td>
      		<a href="excluirFuncionario?codigo=<%=us.getId() %>">
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