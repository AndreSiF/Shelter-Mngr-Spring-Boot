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
    <title> Visualizar/Excluir vítimas </title>
</head>
<body>
    <h1> Vítimas </h1>
    <form method="post" action="VitimaController">
        <label for="nome"> Nome Completo: </label>
        <input type="text" id="nome" name="nome" required><br>
        <label for="cpf"> CPF: </label>
        <input type="text" id="cpf" name="cpf" maxlength="14" minlength="14" required><br>
        <label for="idade"> Idade: </label>
        <input type="number" id="idade" name="idade" required><br>
        <label for="endereco"> Antigo endereço: </label>
        <input type="text" id="endereco" name="endereco" required><br>
        <label> Riscos imediatos: </label>
        <input type="checkbox" id="fome" name="fome" value="desnutrido">
        <label for="fome"> Desnutrido </label>
        <input type="checkbox" id="sede" name="sede" value="desidratado">
        <label for="sede"> Desidratado </label>
        <input type="checkbox" id="frio" name="frio" value="frio">
        <label for="frio"> Hipotérmico </label>
        <input type="checkbox" id="machucado" name="machucado" value="machudado">
        <label for="machucado"> Machudado </label><br>
        <button type="submit" name="opcao" value="CAD"> Cadastrar </button>
    </form>
    <c:if test="${retorno == 'OK'}">
        <h3> Vitima cadastrada com sucesso </h3>
    </c:if>
    <c:if test="${retorno == 'DEL'}">
        <h3> Vítima removida com sucesso </h3>
    </c:if>
    <c:if test="${retorno == 'error'}">
        <h3> Erro na hora do cadastro/remoção </h3>
    </c:if>
    <table>
        <tr>
            <th> Nome </th>
            <th> CPF </th>
            <th> Idade </th>
            <th> Endereço </th>
            <th> Data de chegada </th>
            <th> Presente no abrigo </th>
            <th> Desidratado </th>
            <th> Desnutrido </th>
            <th> Hipotérmico </th>
            <th> Machucado </th>
            <th> Ações </th>
        </tr>
        <c:forEach var="v" items="${vitimas}">
        <tr>
            <td> ${v.nome}</td>
            <td> ${v.cpf}</td>
            <td> ${v.idade}</td>
            <td> ${v.ultimoEnd}</td>
            <td> ${v.data_cad}</td>
            <td> ${v.presente}</td>
            <td> ${v.riscos.desidrat}</td>
            <td> ${v.riscos.nutricao}</td>
            <td> ${v.riscos.frio}</td>
            <td> ${v.riscos.machucado}</td>
            <td>
                <a href="http://localhost:8080/Gerenciamento_de_Abrigos/VitimaController?opcao=DEL&&id=${v.id}"> Excluir </a>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
