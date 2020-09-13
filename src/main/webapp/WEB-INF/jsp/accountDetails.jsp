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
<title>Account details</title>
</head>
<body>

<table id="AccountTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Currency</th>
            <th>Balance</th>
            <th>Treasury</th>
            <th>Do transfer</th>
        </tr>
        </thead>
        <tbody>
	        	<tr>
	            	<td>
	                	<c:out value="${account.name}"/>
	                </td>
	                <td>
	                    <c:out value="${account.currency}"/>
	                </td>
	                <td>
	                    <c:out value="${account.balance}"/>
	                </td>
	                <td>
	                    <c:out value="${account.treasury}"/>
	                </td>
	                <td>
	                	<spring:url value="/accounts/account/{accountId}/transfer/new" var="addUrl">
	        				<spring:param name="accountId" value="${account.id}"/>
	    				</spring:url>
	    				<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Do transfer</a>
	                </td>
	           </tr>	
          </tbody>
    </table>

</body>
</html>