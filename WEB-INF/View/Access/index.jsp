<%@ page import="model.entity.Resource" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Access" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("user"); %>
<% List<Access> accesss=null;
try{
 accesss = (List<Access>)request.getAttribute("accesss");
}
catch(Exception e){
	System.out.print("error: "+e);
}
Integer error = Integer.parseInt(request.getAttribute("ERROR").toString());%>
<html lang="es">
<head>
    <title>Access </title>

    <meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Access</title>
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
            <li class="active"><a  href="/access">Access</a></li>
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
        <li><a class="whiteLink" onclick="postRedirect('/access')">Lista de accesos</a></li>
    </ul>
</nav>




<div class="container">
    <br />
    <span style="font-size: xx-large">Accesos</span>
    <br />
    <br />

    <a class="waves-effect waves-light btn whiteLink" onclick="postRedirect('/access/add',{action:'formulario'})">Create</a>
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
    <table class="striped responsive-table">
        <thead>
        <tr>
            <td>Rol</td>
            <td>Recurso</td>
            
        </tr>
        </thead>

        <tbody>
		
        <% 
        	for (int i = 0; i < accesss.size(); i++) {%>
        
        <%  Access access = accesss.get(i); %>
        <% String key = access.getKey();
            String[] arr = key.split("");
            key = "";
            for (String a : arr) {
                if (a.equals("\"")){
                    key += "&quot;";
                } else{
                    key += a;
                }
            } 
            
        	
            
        %>
        <tr>
            <td><%= access.getRol()%></td>
            <td><%= access.getResource()%></td>
            <td><%= access.getDate()%></td>
            <td>
                <a class="postLink" onclick="postRedirect('access/view',{action:'view',key:'<%=key%>'})">Ver</a>
                | <a class="postLink" onclick="postRedirect('access/view',{action:'edit',key:'<%=key%>'})">Editar</a>
                | <a class="postLink" onclick="postRedirect('access/delete',{key:'<%=key%>'})">Borrar</a></td>
        </tr>
        <% } 
        
        %>

        </tbody>



    </table>
<% }%>
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
