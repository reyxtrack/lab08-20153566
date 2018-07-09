<%@ page import="model.entity.Role" %>
<%@ page import="model.entity.User" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("user"); %>
<%
List<Role> roles=null;
try{
roles = (List<Role>) request.getAttribute("roles");}
catch(Exception e){
}
Integer error = Integer.parseInt(request.getAttribute("ERROR").toString());
%>
<html lang="es">
<head>
    <title>Roles</title>

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
         <li ><a class="whiteLink" href="/users">Users</a></li>
            <li class="active"><a  href="/roles">Roles</a></li>
            <li><a  href="/access">Access</a></li>
            <li><a href="/resources">Resources</a></li>
        
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
        <li><a href="/roles">Lista de Roles</a></li>
    </ul>
</nav>

<div class="container">
 
    <br />
    
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif">Roles</span>
    <br />
    <br />

   
<%if(error==1){ %>
<h2>No ha iniciado sesion</h2>
<%}else if(error==2){ %>
<h2>El usuario no se encuentra registrado </h2>
   <% }else if(error==3){%>
   <h2>No existe el recurso</h2>
   <%}else if(error==4){ %>
   <h2>No existe el acceso</h2>
   <%}else{ %>
	 <a class="waves-effect waves-light btn whiteLink" href="/roles/add?action=formulario">Nuevo Rol</a>
    <br />
    <br />
    <table class="striped responsive-table">
        <thead>
        <tr>
            <td>Name</td>
            <td>Status</td>
            <td>Date created</td>
            <td>Actions</td>
        </tr>
        </thead>

        <tbody>

        <% for (int i = 0; i < roles.size(); i++) {%>
        <% Role role = roles.get(i); %>
        <% String key = role.getId();
        %>
        <tr>
            <td><%= role.getName()%></td>
            <td><%= role.getStatus()%></td>
            <td><%= role.getCreateDate()%></td>
            <td>
                <a class="postLink" onclick="postRedirect('roles/view',{action:'view',key:'<%=key%>'})">View</a>
                | <a class="postLink" onclick="postRedirect('roles/view',{action:'edit',key:'<%=key%>'})">Edit</a>
                | <a class="postLink" onclick="postRedirect('roles/delete',{key:'<%=key%>'})">Delete</a></td>
        </tr>
        <% } %>

        </tbody>



    </table>
<%} %>
</div>

<script>
    var userOptions = document.getElementById("userOptions");

    var isUserOptionsEnable = true;

    document.getElementById("cerrar").addEventListener("click", changeUserOptions());

    function changeUserOptions() {
        if (isUserOptionsEnable){
            userOptions.style.display = "none";
        } else {
            userOptions.style.display = "block";
        }

        isUserOptionsEnable = !isUserOptionsEnable;
    }
</script>
<script>
    function postRedirect(url, postData){

        var postForm = document.createElement("form");
        postForm.action = url;
        postForm.method = "POST";

        postForm.style.display = "none";

        for (var key in postData){
            if (postData.hasOwnProperty(key)){
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = key;
                input.value = postData[key];
                postForm.appendChild(input);
            }
        }

        document.body.appendChild(postForm);

        postForm.submit();

    }
</script>
</body>
</html>
