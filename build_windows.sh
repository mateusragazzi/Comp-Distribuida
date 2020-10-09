#!/bin/sh

compile() {
  if [ -d out ]; then
    rm -rf out
  fi
  mkdir out
  javac -d out -cp ".;src" src/Server.java
}

run() {
  echo "Running at http://localhost:$PORT"
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
