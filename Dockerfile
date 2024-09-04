# Use the official Maven image to create a build artifact.
FROM maven:3.8.3-jdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the source code
COPY . .

# Package the application
RUN mvn clean package

# Use the AdoptOpenJDK base image for runtime
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Expose port 8080
EXPOSE 8080

# Set the entrypoint for the container
ENTRYPOINT ["java", "-jar", "demo.jar"]