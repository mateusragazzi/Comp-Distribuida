FROM ubuntu:20.04

# Instalando JDK8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Configurando JAVA_HOME -
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN mkdir /home/trab-comp-distribuida
ENV workdir /home/trab-comp-distribuida
COPY . /home/trab-comp-distribuida
WORKDIR ${workdir}

SHELL ["/bin/bash", "-c"]
RUN cd ${workdir}
RUN javac -cp ".;src" src/Server.java
CMD ['java', '-cp ".;src"', 'src.Server']
