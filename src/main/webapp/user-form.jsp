<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<html>
<head>
    <title>User Management System</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="/User-Management-System/" class="navbar-brand">User Management System</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%= request.getContextPath() %>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <% 
                User user = (User) request.getAttribute("user");
                String actionUrl = (user != null) ? "update" : "insert";
                String pageTitle = (user != null) ? "Edit User" : "Add New User";
                String hiddenIdField = (user != null) ? "<input type='hidden' name='id' value='" + user.getId() + "' />" : "";
                String nameValue = (user != null) ? user.getName() : "";
                String emailValue = (user != null) ? user.getEmail() : "";
                String countryValue = (user != null) ? user.getCountry() : "";
            %>
            <form action="<%= actionUrl %>" method="post">
                <caption>
                    <h2><%= pageTitle %></h2>
                </caption>

                <%= hiddenIdField %>

                <fieldset class="form-group">
                    <label>Name</label>
                    <input type="text" value="<%= nameValue %>" class="form-control" name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="text" value="<%= emailValue %>" class="form-control" name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label>Country</label>
                    <input type="text" value="<%= countryValue %>" class="form-control" name="country">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
