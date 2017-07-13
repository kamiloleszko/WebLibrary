<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/header.jsp" %>
<%@ include file="/WEB-INF/include/navbar.jsp" %>

<div class="container">

    <h1>My Account</h1>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th class="text-center col-md-1">Id</th>
                    <th class="text-center">First Name</th>
                    <th class="text-center">Last Name</th>
                    <th class="text-center">Email</th>
                </tr>
                </thead>

                <tbody>
                <c  items="${user}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c>
                </tbody>
            </table>
        </div>
    </div>



</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>
