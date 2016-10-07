<%@page import="jms.camel.cdi.services.HelloServiceLocal, javax.ejb.EJB"
%>

<%! @EJB private HelloServiceLocal helloService;
%>
<html>
    <body>
        <h1>Saying something.....</h1>
        <%
        helloService.sayHello("JSP");
        %>
        <h1>fim.</h1>    
    </body>
</html>
