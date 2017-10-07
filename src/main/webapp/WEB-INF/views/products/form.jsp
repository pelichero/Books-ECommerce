<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
	
		<div>
			<label for="title">Título</label>
			<input type="text" name="title" id="title" />
			<form:errors path="product.title" element="li"/>
		</div>
	
		<div>
			<label for="description">Descrição</label>
			<textarea rows="10" cols="20" id="description" name="description"></textarea>
			<form:errors path="product.description" element="li"/>
		</div>
		<div>
			<label for="numberOfPages"> Número de páginas</label>
			<input type="text" name="numberOfPages" id="numberOfPages">
			<form:errors path="product.numberOfPages" element="li"/>
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
