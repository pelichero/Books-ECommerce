<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Casa do Código</title>
</head>
<body>
	<h1>Cadastro de produtos</h1>
	
	<c:url value="/products" var="url" />
	<form method="post" action="${url}">
		<spring:hasBindErrors name="product">
			<ul>
			<c:forEach var="error" items="${errors.allErrors}">
				<li><spring:message code="${error.code}" text="${error.defaultMessage}"/></li>
			</c:forEach>
			</ul>
		</spring:hasBindErrors>
	
		<div>
			<label for="title">Título</label>
			<input type="text" name="title" id="title" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<textarea rows="10" cols="20" id="description" name="description"></textarea>
		</div>
		<div>
			<label for="numberOfPages"> Número de páginas</label>
			<input type="text" name="numberOfPages" id="numberOfPages">
		</div>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}" />
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		
		<div>
			<input type="submit" values="Enviar">
		</div>
	</form>
	

</body>
</html>
