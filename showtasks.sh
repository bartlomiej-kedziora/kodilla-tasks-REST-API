#!/usr/bin/env bash

URL=http://localhost:8080/crud/v1/task/getTasks

fail() {
  echo "There were errors"
}

runBrowser() {
    x-www-browser $URL
}
if ./runcrud.sh; then
   runBrowser
else
   fail
fi