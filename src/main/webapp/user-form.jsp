<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		crossorigin="anonymous"></link>
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${usuario != null}">
                            <form action="update" method="post"/>
                        </c:if>
                        <c:if test="${usuario == null}">
                            <form action="insert" method="post"/>
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${usuario != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${usuario == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${usuario != null}">
                            <input type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>User Name</label> <input type="text" value="<c:out value='${usuario.nombre}' />" class="form-control" name="nombre" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Direccion</label> <input type="text" value="<c:out value='${usuario.direccion}' />" class="form-control" name="direccion">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Email</label> <input type="text" value="<c:out value='${usuario.email}' />" class="form-control" name="email">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>