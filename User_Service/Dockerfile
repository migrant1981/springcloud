FROM openjdk:8-jre
MAINTAINER SammiLiu<minliu@cn.ibm.com>
ARG PACKAGE="User_Service-0.0.1-SNAPSHOT.jar"
ENV JAVA_HOME=/usr/local/openjdk-8
RUN mkdir /build
COPY target/${PACKAGE} /build/${PACKAGE}
EXPOSE 8888
#ENTRYPOINT ["java","-jar","/build/User_Service-0.0.1-SNAPSHOT.jar"]
CMD exec java -jar /build/User_Service-0.0.1-SNAPSHOT.jar