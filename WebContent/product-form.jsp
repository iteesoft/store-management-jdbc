<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 31/03/2022
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Store Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="/new">Add New Product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Products</a>

    </h2>
</center>
<div align="center">
    <c:if test="${product != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${product == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${product != null}">
                            Edit Product
                        </c:if>
                        <c:if test="${product == null}">
                            Add New Product
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${product != null}">
                    <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${product.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Description: </th>
                    <td>
                        <input type="text" name="description" size="45"
                               value="<c:out value='${product.description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Price: </th>
                    <td>
                        <input type="text" name="price" size="5"
                               value="<c:out value='${product.price}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
                <tr>
                    <th>Quantity: </th>
                    <td>
                        <input type="text" name="quantity" size="5"
                               value="<c:out value='${product.quantity}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>

