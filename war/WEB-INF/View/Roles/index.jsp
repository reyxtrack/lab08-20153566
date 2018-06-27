<%@ page import="model.entity.Role" %>
<%@ page import="model.entity.User" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("User"); %>
<% List<Role> roleList = (List<Role>) request.getAttribute("RoleList");%>
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

<nav style="background-color: #67c9b3">
    <div class="nav-wrapper">
        <a class="whiteLink hide-on-med-and-down" href="../" style="padding: 0 0 0 20px; font-family: 'Product Sans', Roboto, serif; font-size: xx-large">Escuela de Musica</a>

        <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%= usuario.getName()%>
            <img src="<%=usuario.getImgUrl()%>" alt="" class="circle responsive-img" style="padding: 5px" width="50px">

            <div id="userOptions" style="background-color: white; border:solid 2px #67c9b3; position: absolute; width: auto; display: none;">
                <ul style="color: black">

                    <li style="padding: 0 5px;">
                        <a style="color: black" onclick="postRedirect('./users/view',{action:'closeSession'})">Cerrar Sesion</a>
                    </li>

                    <li id="cerrar" style="padding: 0 5px; cursor: pointer">
                        <i class="small material-icons">arrow_drop_up</i>
                    </li>
                </ul>
            </div>
        </div>

          <nav class="top-bar expanded" data-topbar role="navigation">
	<ul class="title-area large-3 medium-4 columns">
		<li class="name">
            <h1><a href="/">System</a></h1>
        </li>
    </ul>
    <div class="top-bar-section">
         <ul class="right">
         <li class="active"><a class="whiteLink" href="/users">Users</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/roles')">Roles</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/access')">Access</a></li>
            <li><a class="whiteLink" onclick="postRedirect('/resources')">Resources</a></li>
        
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


<div class="container">
 <span>Add a User</span>
    <br />
    <br />
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif">Roles</span>
    <br />
    <br />

    <a class="waves-effect waves-light btn whiteLink" onclick="postRedirect('/roles/add',{action:'redirect'})"><i class="material-icons left">add</i>Create</a>
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

        <% for (int i = 0; i < roleList.size(); i++) {%>
        <% Role role = roleList.get(i); %>
        <% String key = role.getKey();

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
            <td><%= role.getName()%></td>
            <td><%= role.getStatus()%></td>
            <td><%= role.getCreateDate()%></td>
            <td>
                <a class="postLink" onclick="postRedirect('roles/view',{action:'viewRedirect',key:'<%=key%>'})">View</a>
                | <a class="postLink" onclick="postRedirect('roles/view',{action:'editRedirect',key:'<%=key%>'})">Edit</a>
                | <a class="postLink" onclick="postRedirect('roles/delete',{key:'<%=key%>'})">Delete</a></td>
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
