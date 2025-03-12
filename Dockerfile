# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Ensure the Gradle wrapper has execution permissions and build the project
RUN chmod +x gradlew && ./gradlew build

# Expose the port that your app listens on
EXPOSE 8080

# Run the Spring Boot app; update the jar name if it differs (e.g., chat-room-0.0.1-SNAPSHOT.jar)
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
