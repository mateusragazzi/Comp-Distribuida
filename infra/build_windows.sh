#!/bin/sh

compile() {
  if [ -d out ]; then
    rm -rf out
  fi
  mkdir out
  javac -d out -cp "java;src" java/src/Server.java
}

run() {
  java -cp "out" src.Server "$PORT"
}

execute() {
  echo "Please, enter the server port. The default is 8080."
  read PORT

  if [ -z "$PORT" ]; then
    PORT=8080
  fi

  compile && run
}

execute
