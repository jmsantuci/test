CLI commands:

/subsystem=security/security-domain=jms-poc-tac-login-module:add()
/subsystem=security/security-domain=jms-poc-tac-login-module/authentication=classic:add(login-modules=[ {"code"=>"jms.poc.tac.security.UserLoginModule", "flag"=>"sufficient"}, {"code"=>"jms.poc.tac.security.TokenLoginModule", "flag"=>"sufficient"} ])
/subsystem=security/security-domain=jms-poc-tac-web-login-module:add()
/subsystem=security/security-domain=jms-poc-tac-web-login-module/authentication=classic:add(login-modules=[{"code"=>"org.jboss.security.ClientLoginModule", "flag"=>"required"}])
/subsystem=logging/periodic-rotating-file-handler=FILE_TAC:add(named-formatter="PATTERN",file={path=tac.log,relative-to=jboss.server.log.dir},suffix=.yyyy-MM-dd)
/subsystem=logging/logger=jms.poc.tac:add(handlers=[FILE_TAC], level=DEBUG)
/subsystem=logging/logger=org.jboss.security:add(level=DEBUG)


URLs de test:

Login:
http://localhost:8080/jms-poc-tac-web/LoginServlet?user=<userName>&password=<password>

Schedule:
http://localhost:8080/jms-poc-tac-web/LoginServlet?user=<userName>&password=<password>

List schedules:
http://localhost:8080/jms-poc-tac-web/ListScheduledInfoServlet

Logout:
http://localhost:8080/jms-poc-tac-web/LogoutServlet

