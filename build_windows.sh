#!/bin/sh

compile() {
  javac -d out -cp ".;src" src/Server.java;
}

run() {
  echo "Running...";
  java -cp "out" src.Server 8080;
}

execute() {
  mkdir out;
  compile && run;
}

execute;