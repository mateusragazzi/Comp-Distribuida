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
COPY . /home/trab-comp-distribuida
WORKDIR /home/trab-comp-distribuida

SHELL ["/bin/bash", "-c"]
RUN cd /home/