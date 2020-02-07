<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:forEach items ="${result.getResults.getData }" var = "obj">
<p>Date: ${obj.getPeriod }  Ratio: ${obj.getRatio }</p>
</c:forEach>
</body>
</html>