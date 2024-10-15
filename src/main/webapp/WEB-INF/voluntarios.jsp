<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 6/8/2024
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title> Visualizar/Excluir voluntários </title>
</head>
<body>
<h1> Voluntários </h1>
<form method="post" action="VoluntarioController">
    <label for="nome"> Nome Completo: </label>
    <input type="text" id="nome" name="nome" required><br>
    <label for="cpf"> CPF: </label>
    <input type="text" id="cpf" name="cpf" maxlength="14" minlength="14" required><br>
    <label for="idade"> Idade: </label>
    <input type="number" id="idade" name="idade" required><br>
    <label for="endereco"> Endereço: </label>
    <input type="text" id="endereco" name="endereco" required><br>
    <c:if test="${retorno == 'OK'}">
        <h3> Voluntário cadastrado com sucesso </h3>
    </c:if>
    <c:if test="${retorno == 'DEL'}">
        <h3> Voluntário removido com sucesso </h3>
    </c:if>
    <c:if test="${retorno == 'error'}">
        <h3> Erro na hora do cadastro/remoção </h3>
    </c:if>
    <button type="submit" name="opcao" value="CAD"> Cadastrar </button>
</form>
<table>
    <tr>
        <th> Nome </th>
        <th> CPF </th>
        <th> Idade </th>
        <th> Endereço </th>
    </tr>
    <c:forEach var="v" items="${voluntarios}">
        <tr>
            <td> ${v.nome}</td>
            <td> ${v.cpf}</td>
            <td> ${v.idade}</td>
            <td> ${v.endereco}</td>
            <td>
                <a href="http://localhost:8080/Gerenciamento_de_Abrigos/VoluntarioController?opcao=DEL&&id=${v.id}"> Excluir </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
