#!/usr/bin/env bash

export CATALINA_HOME=/home/$USER/Dokumenty/Development/Programs/tomcat/apache-tomcat-9.0.4


stop_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh stop
}

start_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh start
  end
}

rename() {
  rm build/libs/crud.war

      if mv ./build/libs/tasks-0.0.1-SNAPSHOT.war ./build/libs/crud.war; then

     echo "Successfully renamed file"
  else
     echo "Cannot rename file"
     fail
  fi
}
check_Catalina() {
  if(CATALINA_HOME == ""); then

    echo "CATALINA_HOME not found"
  else

  echo "CATALINA_HOME successfully found"

fi
}
copy_file() {
  if cp ./build/libs/crud.war $CATALINA_HOME/webapps; then
     start_tomcat
  else
     fail
  fi
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if ./gradlew build; then
   check_Catalina
   rename
   copy_file
else
   stop_tomcat
   fails
fi