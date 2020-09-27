FROM ubuntu:20.04

# Instalando JDK8
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y \
    openjdk-8-jdk \
    ant \
    vim \
    net-tools
RUN apt-get clean

# Fix certificate issues
RUN apt-get update
RUN apt-get install ca-certificates-java
RUN apt-get clean && update-ca-certificates -f

# Configurando JAVA_HOME -
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN mkdir /home/trab-comp-distribuida
ENV workdir /home/trab-comp-distribuida
COPY . /home/trab-comp-distribuida
WORKDIR ${workdir}

RUN cd ${workdir}
RUN javac -d "out" -cp ".:src" src/Server.java
ENV SERVER_PORT 8080
EXPOSE ${SERVER_PORT}
CMD ["java", "-cp \".:src\"", "src.Server", "${SERVER_PORT}"]
