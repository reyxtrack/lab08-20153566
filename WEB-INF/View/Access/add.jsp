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
          <select name="resource" required>
          <%         
          for(Resource e: resources){%>
        <option name="<%=e.getUrl()%>" ><%=e.getUrl() %></option>
        
	<% }%></select><br/>
	<select name="status">
	<option value="true">Habilitado</option>
	<option value="false">Desabilitado</option>
	</select><br/>
<button type="submit">Enviar</button>

    </form>

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
