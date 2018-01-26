#!/usr/bin/env bash



succes() {
echo "Successfuly running runcrud script"
}
fail() {
echo "Something went wrong......"
}

openbrowser() {
 gnome-www-browser --open  http://localhost:8080/crud/v1/task/getTasks
}

if ./gradlew build; then
sh ./runcrud.sh
succes
openbrowser

else
fail
fi