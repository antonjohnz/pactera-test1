<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Home</title>
	<style>
	.error {
		color: #ff0000;
	}
	 
	.errorblock {
		color: #000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding: 8px;
		margin: 16px;
	}
	</style>
</head>
<body>
	<h2>Recipe Finder</h2>
 
	<form:form method="POST" commandName="file"
		enctype="multipart/form-data">
 
		<form:errors path="*" cssClass="errorblock" element="div" />
 
		Please select a csv file for the fridge contents : <input type="file" name="csvfile" /><br/>
		Please select a json file for the Recipes : <input type="file" name="jsonfile" />
		<input type="submit" value="upload" />
		<form:errors path="csvfile" cssClass="error" />
 
	</form:form>
 
</body>
</html>
