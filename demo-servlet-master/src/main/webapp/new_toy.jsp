<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Toys Management Servlet</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Toy Management </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Toys</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${toy != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${toy == null}">
                <form action="insert" method="post">
            </c:if>

            <caption>
                <h2>
                    <c:if test="${toy != null}">
                        Edit Toy
                    </c:if>
                    <c:if test="${toy == null}">
                        Add New Toy
                    </c:if>
                </h2>
            </caption>

<!--            <c:if test="${user != null}">-->
<!--                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />-->
<!--            </c:if>-->

            <fieldset class="form-group">
                <label>Name</label> <input type="text" value="<c:out value='${toy.name}' />" class="form-control" name="name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Price</label> <input type="text" value="<c:out value='${toy.price}' />" class="form-control" name="price">
            </fieldset>

            <fieldset class="form-group">
                <label>Quantity</label> <input type="text" value="<c:out value='${toy.quantity}' />" class="form-control" name="quantity">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>