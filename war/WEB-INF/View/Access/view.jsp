<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Access" %>
<%@ page import="model.entity.Role" %>
<%@ page import="model.entity.Resource" %>
<%@ page import="java.util.List" %>
<%  Access access= (Access) request.getAttribute("access");
    User user = (User) request.getAttribute("user");
    boolean edit = (Boolean) request.getAttribute("edit");
    String action = (String) request.getAttribute("action");
%>
<%  List<Role> roles = (List<Role>)request.getAttribute("Roles"); %>
<%  List<Resource> resources  = (List<Resource>) request.getAttribute("Resources"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    
<meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=action%>Acceso</title>
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
            <li ><a  href="/roles">Roles</a></li>
            <li class="active"><a  href="/access">Access</a></li>
            <li ><a href="/resources">Resources</a></li>
        
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
        <li><a href="/access">Lista de Accesos</a></li>
    </ul>
</nav>

<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif"><%=action%> Acceso</span>
    <br />
    <br />

    <% if (edit) {%>

    <form action="./add" method="post">

        <input name="key" value="<%=access.getKey()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Rol:<br />
        <select value="<%=access.getRol()%>" name="rol" required>
          <% 
        for(Role e: roles){%>
        <option name="<%=e.getName()%>" ><%=e.getName() %></option>
        <% }%>
        </select>
        
      
              <font face= "times new roman" size= "3.5" color="#2895A6 " >Recurso </font>
          <select value="<%=access.getResource() %>" name="resource" required>
          <%         
          for(Resource e: resources){%>
        <option name="<%=e.getUrl()%>" ><%=e.getUrl() %></option>
        
	<% }%></select>
        
        <br/>
        Status:<br />
        <select  value=<%=access.getStatus()%> name="status" class="browser-default" required>
            <option value="true">true</option>
            <option value="false">false</option>
        </select>
        <br />

        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
        </button>

    </form>

    <% } else {%>


    <div style="font-size: x-large">
        Role: <%=access.getRol()%><br />
        Recurso: <%=access.getResource()%><br />
        Estado:  <%=access.getStatus() %><br/>
    </div>


    <% } %>

    <hr />
    <br />
    <a href="../resources" class="waves-effect waves-light btn whiteLink"><i class="material-icons left">arrow_back</i>Go Back</a>


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
</body>
</html>
