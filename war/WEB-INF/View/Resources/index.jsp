<%@ page import="model.entity.Resource" %>
<%@ page import="model.entity.User" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("User"); %>
<% List<Resource> resourceList = (List<Resource>)request.getAttribute("ResourceList");%>
<html lang="es">
<head>
    <title>Resources </title>

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
<div class="container">
    <br />
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif">Recursos</span>
    <br />
    <br />

    <a class="waves-effect waves-light btn whiteLink" onclick="postRedirect('/resources/add',{action:'redirect'})">Create</a>
    <br />
    <br />

    <table class="striped responsive-table">
        <thead>
        <tr>
            <td>URL</td>
            <td>Status</td>
            <td>Date created</td>
        </tr>
        </thead>

        <tbody>
		
        <% try{
        	for (int i = 0; i < resourceList.size(); i++) {%>
        
        <% Resource resource = resourceList.get(i); %>
        <% String key = resource.getKey();
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
            <td><%= resource.getUrl()%></td>
            <td><%= resource.getStatus()%></td>
            <td><%= resource.getDate()%></td>
            <td>
                <a class="postLink" onclick="postRedirect('recourses/view',{action:'viewRedirect',key:'<%=key%>'})">Ver</a>
                | <a class="postLink" onclick="postRedirect('recourses/view',{action:'editRedirect',key:'<%=key%>'})">Editar</a>
                | <a class="postLink" onclick="postRedirect('recourses/delete',{key:'<%=key%>'})">Borrar</a></td>
        </tr>
        <% }} 
        catch(Exception e){
        	
        }
        %>

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
