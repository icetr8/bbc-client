FROM java:8 

# Install maven
RUN apt-get update
RUN apt-get install -y maven

WORKDIR /bbc


ADD ./binance-java-api/pom.xml /bbc/pom.xml
RUN ["mvn", "install"]
# Prepare by downloading dependencies
ADD pom.xml /bbc/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /bbc/src
RUN ["mvn", "compile"]
RUN ["mvn", "package"]

