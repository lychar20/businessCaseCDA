FROM tomcat:11.0

COPY target/spring-petclinic-3.4.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war