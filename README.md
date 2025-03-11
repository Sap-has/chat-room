# Simple Chat Application

## Overview
This is a real-time chat application built using **Java**, **Spring Boot**, and **WebSockets**. The application allows multiple users to join a public chat room and communicate in real time.

## Features
- Real-time messaging with WebSockets
- Supports multiple users in a public chat room
- User-friendly interface
- Built using **Spring Boot** and **STOMP** over WebSockets

## Technologies Used
- Java
- Spring Boot
- WebSockets
- STOMP Protocol
- SockJS (for WebSocket fallback support)
- HTML, JavaScript, CSS

## Prerequisites
Make sure you have the following installed:
- Java 11 or later
- Maven (for dependency management)
- A web browser (for testing the chat interface)

## Setup Instructions

### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/simple-chat-app.git
cd simple-chat-app
```

### 2. Build and Run the Application
If using **Maven**, run:
```sh
mvn spring-boot:run
```
If using **Gradle**, run:
```sh
gradle bootRun
```

### 3. Access the Chat Interface
Open your web browser and go to:
```
http://localhost:8080
```

## How It Works
1. Enter your name and click **Join Chat**.
2. Send messages that are broadcasted to all connected users.
3. Open multiple browser tabs to simulate different users.

## WebSocket Endpoints
- **/ws** → WebSocket connection endpoint
- **/app/chat.sendMessage** → Send chat messages
- **/app/chat.addUser** → Notify when a user joins
- **/topic/public** → Receives broadcasted messages

## Next Steps
- Add private messaging
- Store messages in a database
- Implement authentication and user management

## License
This project is open-source and available under the [MIT License](LICENSE).

