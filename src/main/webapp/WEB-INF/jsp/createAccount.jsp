<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New account</title>
</head>
<body>
<form:form modelAttribute="account" class="form-horizontal">
            <input type="hidden" name="id" value="${account.id}"/>
            <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <div class="control-group">
                    <petclinic:selectField name="currency" label="Currency" names="${currencyType}" size="1"/>
                </div>
            <petclinic:inputField label="Balance" name="balance"/>
            <div class="control-group">
                    <petclinic:selectField name="Treasury" label="Treasury" names="${treasuryType}" size="1"/>
                </div>
            </div>
           <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${account['new']}">
                            <button class="btn btn-default" type="submit">Add account</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!account['new']}">
        </c:if>

</body>
</html>