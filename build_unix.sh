#!/bin/sh

echo "Please, enter the server port. The default is 8080."
read PORT

if [ -z "$PORT" ]; then
  PORT=8080
fi

compile() {
  javac -d out -cp ".:src" src/Server.java
}

run() {
  echo "Running at http://localhost:$PORT"
  java -cp "out" src.Server "$PORT"
}

execute() {
  rm -rf out
  mkdir out
  compile && run
}

execute
