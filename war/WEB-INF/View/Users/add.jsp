<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Role" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) request.getAttribute("User"); %>
<html lang="es">
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
         <li class="active"><a class="whiteLink" href="">Users</a></li>
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

    <form method="post" action="./add">
        <input name="action" value="create" type="hidden">

        Name of the User:<br />
        <input name="userName" placeholder="Name of the User" required><br />
        Email of the User:<br />
        <input name="userEmail" placeholder="Email" type="email" required>

        <div class="row">
            <div class="col l10 m10">
                Profile Image link<br />
                <input name="userImg" value="https://i.stack.imgur.com/34AD2.jpg" placeholder="Link" required oninput="cambiarImg(this)"><br />
                <br />
            </div>
            <div class="col l2 m2">
                <img id="sourceImg" src="https://i.stack.imgur.com/34AD2.jpg" alt="" width="70px">
            </div>
        </div>

        Role of the User:<br />
          <select name="rol" required>
          <% 
          List<Role> roleList = (List<Role>) request.getAttribute("Roles");
          for(Role e: roleList){%>
        <option name="<%=e.getName()%>" ><%=e.getName() %></option>
        
	<% }%></select>

        <button class="btn waves-effect waves-light" type="submit" name="action">Send
        </button>

    </form>
    <hr />
    <br />
    <a href="../users" class="waves-effect waves-light btn whiteLink"><i class="material-icons left">arrow_back</i>Go Back</a>


</div>


<script>
    var sourceImg = document.getElementById("sourceImg");

    function cambiarImg(input) {
        sourceImg.src = input.value;
    }
</script>
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
