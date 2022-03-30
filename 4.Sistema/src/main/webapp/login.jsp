<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ctrlVac - Controle de Vacinação - login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<h2>ctrlVac - Controle de Vacinação</h2>
<br>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<form method="get" class="form-horizontal" action="login">
<fieldset>

<!-- Form Name -->
<legend>Login</legend>

<%if(request.getParameter("mensagem") != null){ %>
		
		<div class="alert alert-danger" role="alert">
		
		<% out.println(request.getParameter("mensagem"));%>
		
		</div>
		
	<%}%>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="titulo">usuário</label>  
  <div class="col-md-4">
  <input id="usuario" name="usuario" type="text" placeholder="usuario" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="descricao">senha</label>  
  <div class="col-md-4">
  <input id="senha" name="senha" type="password" placeholder="" class="form-control input-md">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Acessar</button>
  </div>
</div>

</fieldset>
</form>



</body>
</html>
