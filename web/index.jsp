<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/14/2022
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>To Do App</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@page import="dao.TaskDao"%>
<%@page import="models.Task"%>
<%@page import="java.util.List"%>
<div class="container">
    <h2>TODO LIST</h2>
    <h3>Add Item</h3>
    <p>
    <form action="/add" method="post">
        <input type="text" id="name" name="name" >
        <button type="submit">Add</button>
    </form>
    </p>

    <h3>Todo</h3>
    <ul id="incomplete-tasks">
        <%
            List<Task> allTasks = TaskDao.getTaskLists();
            request.setAttribute("allTasks", allTasks);
        %>
        <c:forEach items = "${allTasks}" var = "task">
            <li><input type="checkbox"><label>${task.getName()}</label>
                <input type="text"><a href="EditServlet?id=${task.getId()}" style="text-decoration: none; color: #888888"> Edit </a><br><a href="DeleteServlet?id=${task.getId()}" style="text-decoration: none; color: #888888">   Delete </a></li>
        </c:forEach>
    </ul>

<%--    <h3>Completed</h3>--%>
<%--    <ul id="completed-tasks"></ul>--%>
</div>

<script type="text/javascript" src="app.js"></script>
</body>
</html>
