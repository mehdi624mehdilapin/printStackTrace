FROM tomcat:latest
COPY . /tmp
WORKDIR /tmp
RUN chmod +x /tmp/entry-point
RUN cp /tmp/API.war /usr/local/tomcat/webapps
RUN chmod +x -R /usr/local/tomcat
RUN apt-get update
RUN apt-get install default-mysql-client -y
