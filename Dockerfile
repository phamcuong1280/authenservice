#FROM openjdk:11
#COPY ./target/*.jar /app.jar
#ENTRYPOINT ["java", "-jar" ,"/app.jar"]
#EXPOSE 8088
#
#
#
#
#
#
#
#
#
#
##
##ENV MYSQL_ROOT_PASSWORD 123456789
##ADD jsp_backup.sql /docker-entrypoint-initdb.d
##EXPOSE 3306
##
##COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar
##ENTRYPOINT ["java", "-jar", "app.jar"]
#
#
##mvn package
##type Dockerfile
#
##mvn install
##docker build -t spring-jwt .
#
##docker run -d -p 3308:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db_example
##docker run -d -p 9099:8088 --name app --net spring-net -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWROD=root -e MYSQL_PORT=3308 app
#
