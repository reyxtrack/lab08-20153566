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
      <ul class="title-area large-2.5 medium-3.5 columns">
		<li class="name">
            <h1><a href="/">System</a></h1>
        </li>
    </ul>
    <div class="top-bar-section">
         <ul class="right">
         <li  class="active"><a class="whiteLink" href="/users">Users</a></li>
            <li ><a  href="/roles">Roles</a></li>
            <li><a  href="/access">Access</a></li>
            <li><a href="/resources">Resources</a></li>
        
            <li><a href="/products">Informes</a></li><li>       
            
            <li> <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%= user.getName()%>
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
 <span>Add a User</span>
    <br />
    <br />

    <form method="post" action="./add">
        <input name="action" value="create" type="hidden">

        Name of the User:<br />
        <input name="name" placeholder="Name of the User" required><br />
        Email of the User:<br />
        <input name="email" placeholder="Email" type="email" required>

            

        Role of the User:<br />
          <select name="role" required>
          <% 
          List<Role> roleList = (List<Role>) request.getAttribute("Roles");
          for(Role e: roleList){%>
        <option value="<%=e.getName()%>" ><%=e.getName() %></option>
        
	<% }%></select>

        <button class="btn waves-effect waves-light" type="submit" name="action">Send
        </button>

    </form>
    <hr />
    <br />


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
