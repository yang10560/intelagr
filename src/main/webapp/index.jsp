<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <% String APP_PATH = request.getContextPath(); %>
</head>
<body>
<%
    response.sendRedirect(APP_PATH+"/pages/login.jsp");
%>
</body>
</html>
