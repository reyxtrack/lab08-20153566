<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Role" %>
<%@ page import="java.util.List" %>

<%@ page import="model.entity.Inform" %>
<%  
List<Role> roles=null;
try{
	roles=( List<Role>) request.getAttribute("roles");
}
catch(Exception e){
	System.out.print("error:"+e);
}
Inform inform= (Inform) request.getAttribute("inform");
User user = (User) request.getAttribute("user");
    boolean edit = (Boolean) request.getAttribute("edit");
    String action = (String) request.getAttribute("action");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=action%> Informe</title>

    
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
            <li><a  href="/access">Access</a></li>
            <li ><a href="/resources">Resources</a></li>
        
            <li class="active"><a href="/products">Informes</a></li><li>       
            
            <li> <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="options()">
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
        <li><a href="/products">Lista de Informes</a></li>
    </ul>
</nav>

<div class="container clearfix">

<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif"><%=action%> Inform</span>
    <br />
    <br />

    <% if (edit) {%>

    <form action="./add" method="post">

        <input name="key" value="<%=inform.getId()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Informante:<br />
        <input name="name" value="<%= inform.getName()%>" placeholder="url" required><br/>
        <br/>
        Email:<br />
        <input name="email" value="<%= inform.getEmail()%>" placeholder="url" required><br/>
        <br/>
        
        Rol:<br />
        <select name="rol" class="browser-default" value="<%=inform.getRol() %>" select="select" required>
            <option value="" disabled selected>Seleccione un rol</option>
           <% for(Role e: roles){ %>
            <option value="<%=e.getName()%>">e.getName()</option><%} %>
        </select>
        <font face= "times new roman" size= "3.5" color="#2895A6 " >    Tipo de Informe</font><br>
        <select name="tipo" value="<%=inform.getType()%>" selected required>
        <option value="Academico">Académico</option>
        <option value="Extra Academico">Extra Académico </option>
        <option value="Logistico">Logistico</option>
        <option value="Problematica">Problematica</option>
        <option value="Administracion">Administracion</option>
        <option value="Actividad">Actividad</option>
        
        </select>
        <br/>
        <br />
        <div>
        <TEXTAREA  cols="35" rows="5" maxlength="200" minlength="1" name="texto"  autocomplete="on" placeholder="¿que desea informar?"  required><%=inform.getInform() %></TEXTAREA><BR>
        </div>

        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
        </button>

    </form>

    <%  } else{%>


 <div class="users view large-9 medium-8 columns content">
    <h3><%= inform.getId() %></h3>
    <table class="vertical-table">
        <tr>
            <th scope="row">Id</th>
            <td><%= inform.getId() %></td>
        </tr>
        <tr>
            <th scope="row">Nombre del informante</th>
            <td><%= inform.getName() %></td>
        </tr>
        <tr>
            <th scope="row">Email</th>
            <td><%= inform.getEmail() %></td>
        </tr>
        <tr>
        <tr>
            <th scope="row">Rol</th>
            <td><%= inform.getRol() %></td>
        </tr>
            <th scope="row">Tipo de Informe</th>
            <td><%= inform.getType() %></td>
        </tr>
        
        <tr>
            <th scope="row">Fecha del informe</th>
            <td><%= inform.getFecha() %></td>
        </tr>
        <tr>
            <th scope="row">Informe</th>
            <td><%= inform.getInform() %></td>
        </tr>
        
    </table>
</div>


    <% } %>

    <hr />
    <br />
    

</div>

<script>
    var userOptions = document.getElementById("userOptions");
    var isUserOptionsEnable = true;
    document.getElementById("cerrar").addEventListener("click", changeUserOptions());
    function options() {
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
