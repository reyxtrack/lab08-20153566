<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Inform"%>
<%@ page import="informs.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Inform inform = (Inform)request.getAttribute("inform");%>   
<% SimpleDateFormat format= new SimpleDateFormat("hh-mm-ss_dd/MM/yyyy"); %>
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
        <li><a href="/add?action=<%= inform.getId()%>">Nuevo Informe</a> </li>
        <li><a href="/update?action=<%= inform.getId()%>">Editar Informe</a> </li>
    </ul>
</nav>

<div class="users view large-9 medium-8 columns content">
    <h3><%= inform.getId() %></h3>
    <table class="vertical-table">
        <tr>
            <th scope="row">Id</th>
            <td><%= inform.getId() %></td>
        </tr>
        <tr>
            <th scope="row">Nombre del informante</th>
            <td><%= inform.getName() %></td>
        </tr>
        <tr>
            <th scope="row">Genero</th>
            <td><%= inform.getGender() %></td>
        </tr>
        <tr>
        <tr>
            <th scope="row">Rol</th>
            <td><%= inform.getRol() %></td>
        </tr>
            <th scope="row">Tipo de Informe</th>
            <td><%= inform.getType() %></td>
        </tr>
        
        <tr>
            <th scope="row">Fecha del informe</th>
            <td><%= inform.getFecha() %></td>
        </tr>
        <tr>
            <th scope="row">Informe</th>
            <td><%= inform.getInform() %></td>
        </tr>
        
    </table>
</div>
</div>
<footer>
</footer>
</body>
</html>