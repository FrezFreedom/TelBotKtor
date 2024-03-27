# Use a base image with the JVM installed
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Ktor application JAR file into the container
COPY . /app

RUN ./gradlew build

# Expose the port that your Ktor application listens on
EXPOSE 8080

# Command to run your Ktor application when the container starts
CMD ["java", "-jar", "build/libs/telegram_library-all.jar"]
