<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Efetue o Login</title>
</head>
<body>
	<h1>Cadastro de produtos</h1>
	<form:form servletRelativeAction="/login">
        <div>
            <label>
                User <input type="text" name="username" />
            </label>
        </div>
        <div>
            <label>
                Password <input type="password" name="password" />
            </label>
        </div>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>

</body>
</html>
