# Stage 1: Build the application
FROM eclipse-temurin:17-jdk as build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Install Maven dependencies
RUN ./mvnw dependency:go-offline

# Copy the project files
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the packaged application from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]