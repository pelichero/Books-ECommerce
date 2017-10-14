<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Casa do C�digo</title>
</head>
<body>
	<h1>Cadastro de produtos</h1>
	
	<form:form action="${spring:mvcUrl('insereProduto').build()}" method="POST" commandName="product">
		<div>
			<label for="title">T�tulo</label>
			<form:input path="title"/>
			<form:errors path="title" element="li"/>
		</div>
	
		<div>
			<label for="description">Descri��o</label>
			<form:textarea path="description" rows="10" cols="20" id="description"/>	
			<form:errors path="description" element="li"/>
		</div>
		<div>
			<label for="numberOfPages"> N�mero de p�ginas</label>
			<form:input path="numberOfPages" name="numberOfPages" id="numberOfPages" />
			<form:errors path="numberOfPages" element="li"/>
		</div>
		<div>
			<label for="launchDate">Data de lan�amento</label>
			<form:input type="date" path="launchDate" name="launchDate" id="launchDate" />
			<form:errors path="launchDate" element="li"/>
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}" />
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
	
</body>
</html>
