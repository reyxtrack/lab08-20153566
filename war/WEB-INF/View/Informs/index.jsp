<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="informs.*"%>
<%@ page import="model.entity.Inform"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%

List<Inform> informs = (List<Inform>)request.getAttribute("informs");

%>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title> laboratorio 07</title>
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
            <li><a href="/index">Informs</a></li>
        </ul>
    </div>
</nav>
<div class="container clearfix">



<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading">Actions</li>
        <li><a href="/add?action=xx">Nuevo Informe</a></li>
    	<li><a href="/index">Actualizar</a></li>
    </ul>
</nav>

<div class="users index large-9 medium-8 columns content">
 <legend>Informes Disponibles: <%= informs.size() %></legend><br>
    <% if (informs.size() > 0) { %>


 <table border="0" cellspacing="1" cellpadding="5"
bgcolor="#CCCCCC" width="50%">
 <tr bgcolor="#407BA8">
  <td style="color: #ffffff; font-weight: bold;">ID</td>
 <td style="color: #ffffff; font-weight: bold;">Informante</td>
 <td style="color: #ffffff; font-weight: bold;">Tipo de Informe</td>
 <td style="color: #ffffff; font-weight: bold;">Informe</td>
 </tr>
 <% for (int i = 0;i<informs.size();i++) { %>
 <% Inform a = (Inform)informs.get(i); %>
 <tr style="background:#ffffff"
onMouseOver="this.style.background='#eeeeee';"
onMouseOut="this.style.background='#ffffff';">
 <td><a href="view?action=<%= a.getId() %>"><%= i+1%></a></td>
 <td><%= a.getName() %></td>
 <td><%= a.getType() %></td>
 <td class="actions">
  <a href="/view?action=<%= a.getId()%>">View</a>
  <a href="/update?action=<%= a.getId()%>">Edit</a>
  <a href="/delete?action=<%= a.getId()%>">Delete</a>               
                </td>
 </tr>
 <% }} %>
 </table>

<div class="paginator">
    
</div>
</div>
</div>
<footer>
</footer>
	<script src="js/jquery-3.3.1.slim.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	</body>

</html>