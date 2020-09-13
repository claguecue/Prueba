<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All accounts</title>
</head>
<body>

<table id="AccountTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Currency</th>
            <th>Detaills</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${accounts}" var="account">
	        	<tr>
	            	<td>
	                	<c:out value="${account.name}"/>
	                </td>
	                <td>
	                    <c:out value="${account.currency}"/>
	                </td>
	                <td>
	                	<spring:url value="/accounts/account/{accountId}" var="addUrl">
	        				<spring:param name="accountId" value="${account.id}"/>
	    				</spring:url>
	    				<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Detaills</a>
	                </td>
	           </tr>	
	        </c:forEach>		
          </tbody>
    </table>
    
    <spring:url value="/accounts/account/new" var="addUrl">
	</spring:url>
	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">New account</a>

</body>
</html>