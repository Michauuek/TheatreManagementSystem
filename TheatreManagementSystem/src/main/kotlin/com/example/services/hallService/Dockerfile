FROM openjdk:11
EXPOSE 8082:8082
COPY build/libs/hall-service.jar hall.jar
ENTRYPOINT ["java","-jar","/hall.jar"]