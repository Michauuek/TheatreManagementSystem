
FROM openjdk:11
EXPOSE 8081:8081
COPY build/libs/auth-service.jar auth.jar
ENTRYPOINT ["java","-jar","/auth.jar"]