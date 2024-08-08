# Use an official Maven image with Java 11 (or change to another JDK version if needed)
FROM maven:3.8-openjdk-17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and the source code
COPY pom.xml /app
COPY src /app/src

# Optional: Install dependencies separately to take advantage of Docker's caching
# Install dependencies and build the project
RUN mvn dependency:go-offline

# Install Chrome
RUN apt-get update && apt-get install -y wget gnupg curl unzip \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' \
    && apt-get update && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Run the tests by default when the container starts
CMD ["mvn", "clean", "compile","test"]

