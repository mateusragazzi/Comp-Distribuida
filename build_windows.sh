#!/bin/sh

PORT=8080

compile() {
  javac -d out -cp ".;src" src/Server.java
}

run() {
  echo "Running at http://localhost:$PORT"
  java -cp "out" src.Server "$PORT"
}

execute() {
  mkdir out
  compile && run
}

execute
