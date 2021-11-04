FROM maven:3.6-jdk-11 as build 
WORKDIR /helidon

ADD pom.xml .
RUN mvn package -DskipTests -Dhttps.proxyHost=www-proxy-ash7.us.oracle.com -Dhttps.proxyPort=80

ADD src src
RUN mvn package -DskipTests -Dhttps.proxyHost=www-proxy-ash7.us.oracle.com -Dhttps.proxyPort=80
RUN echo "done!"

FROM openjdk:11-jre-slim 
WORKDIR /helidon

COPY --from=build /helidon/target/helidon-mp-tutorial.jar ./ 
COPY --from=build /helidon/target/libs ./libs

CMD ["java", "-jar", "helidon-mp-tutorial.jar"] 
EXPOSE 8080
