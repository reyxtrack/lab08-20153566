<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Informes</title>
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
            <li><a href="/Views/index.jsp">Informes</a></li>
        </ul>
    </div>
</nav>
<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading">Actions</li>
        <li><a href="/index">Lista de Informes</a></li>
    </ul>
</nav>

<div class="users form large-9 medium-8 columns content">
	<form method="get" accept-charset="utf-8" action="/add">
	<div style="display:none;">
	<input type="hidden" name="action" value="acc"/>
	</div>    
	<fieldset>
        <legend>Añadir Informe</legend> 
        <div class="input date required">
      	<font face= "times new roman" size= "3.5" color="#2895A6 " >Nombre del Informante</font>
      	<input type="text" name="name" autofocus="on" required >
      	<font face= "times new roman" size= "3.5" color="#2895A6 " >Genero</font>
      	<select name="gender" required>
        <option value="masculino">Masculino</option>
        <option value="femenino">Femenino </option>
        </select><br>
        <font  face= "times new roma	n" size= "3.5" color="#2895A6 " >Rol</font>
        <select name="rol" required>
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
        <font face= "times new roman" size= "3.5" color="#2895A6 " >    Tipo de Informe</font><br>
        <select name="tipo" required>
        <option value="Academico">Académico</option>
        <option value="Extra Academico">Extra Académico </option>
        </select>
        </div>
        <div>
        <TEXTAREA  cols="35" rows="5" maxlength="200" minlength="1" name="texto" autofocus="true" autocomplete="on" placeholder="¿que desea informar?" required></TEXTAREA><BR>
        </div>
       
        
        </fieldset>
    <button type="submit">Enviar</button>
    </form> 

<div class="paginator">
    
</div>
</div>
</div>
<footer>
</footer>
</body>
</html>