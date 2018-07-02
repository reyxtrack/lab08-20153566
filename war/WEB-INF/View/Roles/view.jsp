<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Role" %>

<%  Role role = (Role) request.getAttribute("Role");
    User user = (User) request.getAttribute("User");
    boolean edit = (Boolean) request.getAttribute("edit");
    String action = (String) request.getAttribute("action");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Roles</title>

    <meta charset="utf-8"/>    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Roles</title>
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
            <li class="active"><a  href="/roles">Roles</a></li>
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

        <ul id="nav-mobile" class="right">
            <li><a class="whiteLink" href="/users">Users</a></li>
            <li class="active"><a class="whiteLink" href="/roles">Roles</a></li>
            <li><a class="whiteLink" href="/access">Access</a></li>
            <li><a class="whiteLink" href="/resources">Resources</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <br />
    
    <br />
    <br />

    <% if (edit) {%>

    <form action="./add" method="post">

        <input name="key" value="<%=role.getId()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Name:<br />
        <input name="roleName" value="<%=role.getName()%>" placeholder="Name" required><br/>
        <br/>
        Status:<br />
        <select name="roleStatus" class="browser-default" required>
            <option value="" disabled selected>Choose a status</option>
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
        Name: <%=role.getName()%><br />
        Email: <%=role.getStatus()%><br />
    </div>


    <% } %>

    <hr />
    <br />
    <a href="../roles" class="waves-effect waves-light btn whiteLink"><i class="material-icons left">arrow_back</i>Go Back</a>


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
