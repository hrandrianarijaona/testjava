<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Images</title>
</head>
<body>
	<div align="center">
		<h2>Thumbnail generated:</h2>

		<c:forTokens items="${imageList}" delims="," var="name">
			<c:out value="${name}" />
			<p>
		</c:forTokens>

	</div>
</body>
</html>