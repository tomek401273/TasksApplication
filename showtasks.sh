#!/usr/bin/env bash



succes() {
echo"Successfuly running runcrud script"
}
fail() {
echo "Cannot runn runcrud script"
}

openbrowser() {
 gnome-www-browser --open  http://localhost:8080/crud/v1/task/getTasks
}

if sh ./runcrud.sh; then
succes
openbrowser

else
fail
fi