<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Role" %>

<%@ page import="java.util.List" %>

<%  User user = (User) request.getAttribute("user");
    User user1 = (User) request.getAttribute("login");
    boolean edit = (Boolean) request.getAttribute("edit");
    String action = (String) request.getAttribute("action");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=action%> a Role - Escuela de Musica</title>

   <meta charset="utf-8"/>   
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
         <li  class="active"><a class="whiteLink" href="/users">Users</a></li>
            <li ><a  href="/roles">Roles</a></li>
            <li><a  href="/access">Access</a></li>
            <li><a href="/resources">Resources</a></li>
        
            <li><a href="/products">Informes</a></li><li>       
            
            <li> <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%=user1.getName()%>
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
        <li><a href="/users">Lista de Usuarios</a></li>
    </ul>
</nav>


<div class="container">
    <br />
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif"><%=action%>  Usuario</span>
    <br />
    <br />

    <% if (edit) {%>

    <form action="./add" method="post">

        <input name="id" value="<%=user.getId()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Name:<br />
        <input name="name" value="<%=user.getName()%>" placeholder="Name" required><br/>
        <br/>
        Email:<br />
        <input name="email" value="<%=user.getEmail()%>" placeholder="Email" type="email" required><br />
        <br />

        <div class="row">
           

         Role of the User:<br />
          <select name="role" value=<%=user.getRole() %> selected required>
          <% 
          List<Role> roleList = (List<Role>) request.getAttribute("Roles");
          for(Role e: roleList){%>
        <option value="<%=e.getName()%>" ><%=e.getName() %></option>
        
	<% }%></select>

        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
        </button>

    </form>

    <% } else {%>

        <div class="row">
            <div class="col l8 m8" style="font-size: x-large">
                Name: <%=user.getName()%><br />
                Email: <%=user.getEmail()%><br />
                Role: <%=user.getRole() %><br />
            </div>
            <div class="col l4 m4">
             </div>

        </div>

    <% } %>

    <hr />
    <br />
    


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
