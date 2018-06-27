<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Role" %>
<%@ page import="model.entity.Resource" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  User user = (User)request.getAttribute("User"); %>
<%  List<Role> roles = (List<Role>)  request.getAttribute("Roles"); %>
<%  List<Resource> resources  = (List<Resource>)  request.getAttribute("Resources"); %>
<html>
<head>
    <title>Title</title>
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
         <li class="active"><a class="whiteLink" href="">Users</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/roles')">Roles</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/access')">Access</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/resources')">Resources</a></li>
        
            <li><a href="/Views/index.jsp">Informes</a></li><li>       
            
            <li> <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%= user.getName()%>
            <img src="<%=user.getImgUrl()%>" alt="" class="circle responsive-img" style="padding: 5px" width="50px">
            <i class="material-icons right"></i>

            <div id="userOptions" style="background-color: white; border:solid 2px #67c9b3; position: absolute; width: auto; display: none;">
                <ul style="color: black">

                    <li style="padding: 0 5px;">
                        <a style="color: black" onclick="postRedirect('./users/view',{action:'closeSession'})">Cerrar Sesion</a>
                    </li></ul></div></div></li>
 			       
        </ul>
    </div>
</nav>
</nav>
<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading">Actions</li>
        <li><a href="/usuarios">Lista de Usuarios</a></li>
    </ul>
</nav>




<div class="container">
 <span>Add a Access</span>
    <br />
    <br />
    <form method="post" action="./add">
        <input name="action" value="create" type="hidden">

        <font face= "times new roman" size= "3.5" color="#2895A6 " >Rol </font>
         
          <select name="rol" required>
          <% 
          for(Role e: roles){%>
        <option name="<%=e.getName()%>" ><%=e.getName() %></option>
        
	<% }%></select>
        
      
              <font face= "times new roman" size= "3.5" color="#2895A6 " >Recurso </font>
          <select name="rol" required>
          <%         
          for(Resource e: resources){%>
        <option name="<%=e.getUrl()%>" ><%=e.getUrl() %></option>
        
	<% }%></select>
              
        <button class="btn waves-effect waves-light" type="submit" name="ac">Send
        </button>

    </form>
    

</body>
<nav class="top-bar expanded" data-topbar role="navigation">
      <ul class="title-area large-3 medium-4 columns">
		<li class="name">
            <h1><a href="/">System</a></h1>
        </li>
    </ul>
</nav>
<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading">Actions</li>
        <li><a href="/usuarios">Lista de Usuarios</a></li>
    </ul>
</nav>



<div class="container">
    <br />

</html>
