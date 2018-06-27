<%@ page import="model.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("User"); %>
<% List<User> userList = (List<User>) request.getAttribute("UsersList");%>
<html lang="es">
<head>
    <title>Users</title>

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
            <%= usuario.getName()%>
            <img src="<%=usuario.getImgUrl()%>" alt="" class="circle responsive-img" style="padding: 5px" width="50px">
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
    <br />
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif">Users</span>
    <br />
    <br />

    <a class="waves-effect waves-light btn whiteLink" onclick="postRedirect('/users/add',{action:'redirect'})"><i class="material-icons left">Añadir</i>Usuario</a>
    <br />
    <br />

    <table class="striped responsive-table">
        <thead>
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Img</td>
                <td>Actions</td>
            </tr>
        </thead>

        <tbody>

        <% for (int i = 0; i < userList.size(); i++) {%>
        <% User user = userList.get(i); %>
            <tr>
                <td><%= user.getName()%></td>
                <td><%= user.getEmail()%></td>
                <td><img src="<%= user.getImgUrl()%>" width="55px"/></td>
                <td>
                    <a class="postLink" onclick="postRedirect('users/view',{action:'viewRedirect',userID:'<%=user.getId()%>'})">View</a>
                    | <a class="postLink" onclick="postRedirect('users/view',{action:'editRedirect',userID:'<%=user.getId()%>'})">Edit</a>
                    | <a class="postLink" onclick="postRedirect('users/delete',{userID:'<%=user.getId()%>'})">Delete</a></td>
            </tr>
        <% } %>

        </tbody>



    </table>

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
