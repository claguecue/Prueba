<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New transfer</title>
</head>
<body>

<form:form modelAttribute="transfer" class="form-horizontal">
            <input type="hidden" name="id" value="${transfer.id}"/>
            <div class="form-group has-feedback">
            <petclinic:inputField label="Quantity" name="quantity"/>
            <div class="control-group">
                    <petclinic:selectField name="To_id" label="ID account to" names="${toType}" size="1"/>
                </div>
            </div>
           <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${transfer['new']}">
                            <button class="btn btn-default" type="submit">Add transfer</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!transfer['new']}">
        </c:if>

</body>
</html>