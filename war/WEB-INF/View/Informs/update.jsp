<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Inform"%>
<%@ page import="informs.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Inform inform = (Inform)request.getAttribute("inform");%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Informes</title>
<link href="/favicon.ico" type="image/x-icon" rel="icon"/>
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon"/>
<link rel="stylesheet" href="/css/base.css"/>
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<nav class="top-bar expanded" data-topbar role="navigation">
	<ul class="title-area large-3 medium-4 columns">
		<li class="name">
            <h1><a href="/">System</a></h1>
        </li>
    </ul>
    <div class="top-bar-section">
        <ul class="right">
            <li><a href="/index">Informes</a></li>
          
        </ul>
    </div>
</nav>
<div class="container clearfix">



<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading">Actions</li>
        <li><a href="/delete?action=<%= inform.getId()%>">Borrar</a></li>
        <li><a href="/index">Lista de Informes</a> </li>
        <li><a href="/add?action=xx">Nuevo Informe</a> </li>
    </ul>
</nav>

<div class="users view large-9 medium-8 columns content">
    <h3><%= inform.getId() %></h3>
	<form method="get" accept-charset="utf-8" action="/update">
	<div style="display:none;">
	<input type="hidden" name="action" value="acc"/>
	<input type="hidden" name="id" value="<%= inform.getId()%>" >
	</div>    
	<fieldset>
        <legend>Actualizar Informe</legend> 
        <div class="input date required">
      	<font face= "times new roman" size= "3.5" color="#2895A6 " >Nombre del Informante</font>
      	<input type="text" autocomplete="on" name="nombre" required value="<%= inform.getName() %>" onfocus="this.selectionStart = this.selectionEnd = this.value.length;"  
  autofocus="true"/>>
      	<font face= "times new roman" size= "3.5" color="#2895A6 " >Genero</font>
      	<select name="genero" required value=<%= inform.getGender() %>>
        <option value="masculino">Masculino</option>
        <option value="femenino">Femenino </option>
        </select><br>
        <font  face= "times new roman" size= "3.5" color="#2895A6 " >Rol</font>
        <select name="rol" value=<%= inform.getRol() %> required>
        <option value="director">Director</option>
        <option value="subdirector">Subdirector </option>
       	<option value="coordinador">Coordinador</option>
       	<option value="profesor">Profesor</option>
       <option value="auxiliar">Auxiliar</option>
        <option value="seguridad">Seguridad</option>
        <option value="limpieza">Limpieza</option>
        <option value="padre">Padre de familia</option>
        <option value="estudiante">Estudiante</option>
        <option value="otros">Otros </option>
        </select><br><br>
        <font face= "times new roman" size= "3.5" color="#2895A6 " >Tipo de Informe</font><br>
        <select name="tipo" value=<%= inform.getType() %> required>
        <option value="Academico">Académico</option>
        <option value="Extra Academico">Extra Académico </option>
        </select>
        </div>
        <div>
        <TEXTAREA  cols="35" rows="5" maxlength="200" minlength="1" name="texto"  autocomplete="on" placeholder="¿que desea informar?"  required><%=inform.getInform() %></TEXTAREA><BR>
        </div>
       
        
        </fieldset>
    <button type="submit">EDITAR</button>
    </form> 
    
    
</div>
</div>
<footer>
</footer>
</body>
</html>