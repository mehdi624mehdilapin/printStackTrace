FROM tomcat:10.0.0-M7-jdk11-openjdk-buster
COPY . /tmp
RUN cp /tmp/API.war /usr/local/tomcat/webapps
RUN apt-get update
RUN apt-get install mariadb-client -y