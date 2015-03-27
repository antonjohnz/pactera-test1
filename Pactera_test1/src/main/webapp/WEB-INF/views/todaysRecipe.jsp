
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.pactera.test.model.Ingredient"%>
<html>
<body>
	<h2>Todays Recipe</h2>
	<c:if test="${empty orderout}">
    Item : ${recipename} <br/><br/>
	${ingredientSize } Ingredients<br/>
	${fullingredients}
	</c:if>
	<c:if test="${not empty orderout}">
	    ${orderout}
	</c:if>
	
	
</body>
</html>