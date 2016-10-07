<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Student Display Page</title>
</head>
<body>
 Student name is ${ param.stuname } <br />
 Student Roll No is ${ param.rollno } <br />
 <br />
 By JSTL <br />
 ------------------------------------ <br />
 
 <c:set var="studentName" scope="application" value="Student Name: ${param.stuname}."/>
 <c:set var="studentNumber" scope="application" value="Student #: ${param.rollno}."/>
 
 <c:out value="${studentName}"/> <br />
 <c:out value="${studentNumber}"/>
 
</body>
</html>