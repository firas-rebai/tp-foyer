FROM openjdk:11-jdk-slim
EXPOSE 8089
ADD target/foyer-1.0.jar foyer-1.0.jar
ENTRYPOINT ["java","-jar","/foyer-1.0.jar"] 