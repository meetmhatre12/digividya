
FROM tomcat:9.0-jdk17
COPY target/DigiVidya.war /usr/local/tomcat/webapps/
EXPOSE 8080
