<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Resource" %>
<%  Resource resource= (Resource) request.getAttribute("Resource");
    User user = (User) request.getAttribute("User");
    boolean edit = (Boolean) request.getAttribute("edit");
    String action = (String) request.getAttribute("action");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=action%> Ver Recurso</title>

    <meta name="google-signin-client_id" content="746890482047-c734fgap3p3vb6bdoquufn60bsh2p8l9.apps.googleusercontent.com">

    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


</head>
<body>

<nav style="background-color: #67c9b3">
    <div class="nav-wrapper">
        <a class="whiteLink hide-on-med-and-down" href="../" style="padding: 0 0 0 20px; font-family: 'Product Sans', Roboto, serif; font-size: xx-large">Hotel Services</a>

        <div class="right valign-wrapper" style="padding: 0 0 0 10px; cursor: pointer;" onclick="changeUserOptions()">
            <%= resource.getUrl()%>
            <img src="<%=user.getImgUrl()%>" alt="" class="circle responsive-img" style="padding: 5px" width="50px">
            <i class="material-icons right">arrow_drop_down</i>

            <div id="userOptions" style="background-color: white; border:solid 2px #67c9b3; position: absolute; width: auto; display: none;">
                <ul style="color: black">

                    <li style="padding: 0 5px;">
                        <a style="color: black" onclick="postRedirect('./users/view',{action:'closeSession'})">Cerrar Sesion</a>
                    </li>

                    <li id="cerrar" style="padding: 0 5px; cursor: pointer">

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
    <span style="font-size: xx-large; font-family: 'Product Sans',Roboto,serif"><%=action%> a User</span>
    <br />
    <br />

    <% if (edit) {%>

    <form action="./add" method="post">

        <input name="key" value="<%=resource.getKey()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Name:<br />
        <input name="url" value="<%=resource.getUrl()%>" placeholder="url" required><br/>
        <br/>
        Status:<br />
        <select name="status" class="browser-default" required>
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
        Url: <%=resource.getUrl()%><br />
        Email: <%=resource.getStatus()%><br />
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
