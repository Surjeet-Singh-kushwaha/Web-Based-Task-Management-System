<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
if(session.getAttribute("Name")==null){
	response.sendRedirect("login.jsp");
}

%>
<%@ page import="java.lang.String" %>


<% 
    
    String str = (String) session.getAttribute("Name");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>