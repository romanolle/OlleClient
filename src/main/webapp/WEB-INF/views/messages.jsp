<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Messages</title>
</head>
<body>

	<c:url var="addAction" value="/message/add" ></c:url>
	
	<c:if test="${!empty result}">
		${result}<br />
	</c:if>
	
	<form:form action="${addAction }" commandName="message">
		<form:label path="name">Name: </form:label>
		<form:input path="name" />
		
		<br />
		
		<form:label path="email">E-mail: </form:label>
		<form:input path="email" />
		
		<br />
		
		<form:label path="message">Message: </form:label>
		<form:input path="message" />
		
		<br />
		
		<input type="submit" value="Send" />
	</form:form>
	
	<c:if test="${!empty messages }">
		<h2>Messages</h2>
		<c:forEach items="${messages }" var="m">
			<fmt:formatDate value="${m.created}" pattern="dd.MM.yyyy HH:mm:ss" />: ${m.name} (${m.email}) - ${m.message}<br />
		</c:forEach>
	</c:if>
</body>
</html>