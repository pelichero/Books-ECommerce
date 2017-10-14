<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Casa do Código</title>
</head>
<body>
	<h1>Listagem de produtos</h1>
	${sucesso}
	<table>
		<tr>
			<th>Títulos</th>
			<th>Valores</th>
			<th>Detalhes</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id} - ${product.title}</td>
				<td>
					<c:forEach items="${product.prices}" var="product">
						[${product.value} - ${product.bookType}]
					</c:forEach>
				</td>
				<td>
					<c:url value="/products/${product.id}" var="linkDetalhar"/>
					<a href="${linkDetalhar}">Detalhar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>