<%@ page import="model.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<% User usuario=(User)request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
   <title>Resources </title>

    <meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Informes</title>
<link rel="stylesheet" href="/css/base.css"/>
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<nav class="top-bar expanded" data-topbar role="navigation">
      <ul class="title-area large-2.5 medium-3.5 columns">
		<li class="name">
            <h1><a href="/">System</a></h1>
        </li>
    </ul>
    <div class="top-bar-section">
         <ul class="right">
         <li ><a class="whiteLink" href="/users">Users</a></li>
            <li ><a  href="/roles">Roles</a></li>
            <li><a  href="/access">Access</a></li>
            <li class="active"><a href="/resources">Resources</a></li>
        
            <li><a href="/products">Informes</a></li><li>       
            
            <li> <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%= usuario.getName()%>
                        <img src="https://image.flaticon.com/icons/png/512/17/17004.png" alt="Error"  style="padding: 5px" width="50px">
            <i class="material-icons right"></i>

            <div id="userOptions" style="background-color: white; border:solid 2px #67c9b3; position: absolute; width: auto; display: none;">
                <ul style="color: black">

                    <li style="padding: 0 5px;">
                        <a style="color: black" href="/logout">Logout</a>
                    </li></ul></div></div></li>
 			       
 			       
        </ul>
    </div>
</nav>
</nav>
<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
       
        <li class="heading">Actions</li>
        <li><a href="/index">Lista de Informes</a></li>
    </ul>
</nav>

<div class="users form large-9 medium-8 columns content">
	<form method="post" accept-charset="utf-8" action="/products/add">
	<div style="display:none;">
	<input type="hidden" name="action" value="create"/>
	</div>    
	<fieldset>
        <legend>Añadir Informe</legend> 
        <div class="input date required">
      	
      	
       <br>
        <font face= "times new roman" size= "3.5" color="#2895A6 " >    Tipo de Informe</font><br>
        <select name="tipo" required>
        <option value="Academico">Académico</option>
        <option value="Extra Academico">Extra Académico </option>
        <option value="Logistico">Logistico</option>
        <option value="Problematica">Problematica</option>
        <option value="Administracion">Administracion</option>
        <option value="Actividad">Actividad</option>
        
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