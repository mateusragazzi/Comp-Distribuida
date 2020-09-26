FROM openjdk:8

COPY . /usr/src/trab-comp-distribuida
WORKDIR /usr/src/trab-comp-distribuida
RUN javac Main.java
CMD ["java", "Main"]