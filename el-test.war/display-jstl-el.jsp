<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:out value="${name}"/>

<c:set var="name2" scope="application" value="${name} +++ "/>

<c:out value="${name2}"/>