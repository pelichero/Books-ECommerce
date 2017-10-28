<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!--  JQuery -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<meta charset="UTF-8" />
	<title>Casa do C&oacute;digo</title>
</head>
<body>
    <div class="container">
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal" var="user" />
            <div>
                Olá ${user.name}
            </div>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:url value="/products/form" var="formLink" />
            <a href="${formLink}">
                Cadastrar Novo Produto
            </a>
        </sec:authorize>

        <h1>Listagem de produtos</h1>
        ${sucesso}
        <div class="well">
            <ul>
                <li> <a href="<c:url value='/products?locale=pt' />" >Portugues</a>
                <li> <a href="<c:url value='/products?locale=en_Us' />" >Inglês</a>
            </ul>

            <table>
                <tr>
                    <th>T&iacute;tulos</th>
                    <th>Valores</th>
                    <th>Detalhes</th>
                </tr>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.title}</td>
                        <td>
                            <c:forEach items="${product.prices}" var="price">
                                [${price.value} - ${price.bookType}]
                            </c:forEach>
                        </td>
                        <td>
                            <c:url value="/products/${product.id}" var="linkDetalhar"/>
                            <a href="${linkDetalhar}">Detalhar</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
     </div>
</body>
</html>