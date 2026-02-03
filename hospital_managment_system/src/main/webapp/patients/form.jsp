<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Patient Form</title></head>
<body>
<h2>Patient</h2>
<form method="post" action="../patients">
    <input type="hidden" name="id" value="${patient != null ? patient.id : ''}" />
    <label>Name: <input name="name" value="${patient != null ? patient.name : ''}"/></label><br/>
    <label>Gender: <input name="gender" value="${patient != null ? patient.gender : ''}"/></label><br/>
    <label>DOB: <input type="date" name="dob" value="${patient != null ? patient.dob : ''}"/></label><br/>
    <label>Phone: <input name="phone" value="${patient != null ? patient.phone : ''}"/></label><br/>
    <label>Address: <input name="address" value="${patient != null ? patient.address : ''}"/></label><br/>
    <button type="submit">Save</button>
</form>
</body>
</html>
