FROM openjdk:8-jre
MAINTAINER SammiLiu<minliu@cn.ibm.com>
ARG PACKAGE="Admin_Service-0.0.1-SNAPSHOT.jar"
ENV JAVA_HOME=/usr/local/openjdk-8
RUN mkdir /build
COPY target/${PACKAGE} /build/${PACKAGE}
EXPOSE 8889
#ENTRYPOINT ["java","-jar","/build/Admin_Service-0.0.1-SNAPSHOT.jar"]
CMD exec java -jar /build/Admin_Service-0.0.1-SNAPSHOT.jar