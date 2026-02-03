<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Patients</title></head>
<body>
<h2>Patients</h2>
<a href="patients?action=new">Add Patient</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Phone</th><th>Actions</th></tr>
    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.phone}</td>
            <td><a href="patients?action=edit&id=${p.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
