# ****************************************************************
# Commands to build and run ( Execute it in the shell )
# ****************************************************************
#   JAVA_HOME=$(dirname $( readlink -f $(which java) )) &&
#   JAVA_HOME=$(realpath "$JAVA_HOME"/../) &&
#   export JAVA_HOME &&
#   mvn clean &&
#   ./mvnw install &&
#   docker build -t capture . &&
#   docker run -p 8884:8884 capture
#*****************************************************************

# In this case we are using .jar insted of .war

FROM openjdk:12-alpine
COPY target/Capture-1.0.0-SNAPSHOT-fat.jar /capture.jar
CMD ["java", "-jar", "/capture.jar"]
ENTRYPOINT [ "sh", "-c", "java -jar capture.jar" ]
