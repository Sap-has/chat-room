# Simple Chat Application

## Overview
This is a real-time chat application built using **Java**, **Spring Boot**, and **WebSockets**. The application allows multiple users to join a chatroom and communicate in real time.

## Features
- Real-time messaging with WebSockets
- Supports multiple users in different chatrooms
- Ability to **host** a chatroom with a randomly generated 5-character code
- Ability to **join** a chatroom using a code
- User-friendly interface
- Built using **Spring Boot** and **STOMP** over WebSockets

## Next Steps
- Make room code persist by adding the room code generator in the backend instead of front end (currenlty uses front end)
- Implement message persistence in a database (MySQL)
- Ability to create users
- Implement authentication and user management

- Ability to friend users
- Can invite friends to chats
- Improve UI design

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
git clone https://github.com/Sap-has/chat-room.git
cd chat-room
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

Or use this link to see web application live
https://chatty-buddies.onrender.com

### 3. Access the Chat Interface
Open your web browser and go to:
```
http://localhost:8080
```

Or use this link to see web application live
https://chatty-buddies.onrender.com

## How It Works
1. The user is prompted to either **Host** or **Join** a chatroom.
   - If **Hosting**, a random 5-character chatroom code is generated.
   - If **Joining**, the user enters the chatroom code.
2. Enter a username and click **Join Chat**.
3. Send messages that are broadcasted to all connected users in the chatroom.
4. Open multiple browser tabs to simulate different users.

## WebSocket Endpoints
- **/ws** → WebSocket connection endpoint
- **/app/chat.sendMessage/{roomCode}** → Send chat messages to a specific chatroom
- **/app/chat.addUser/{roomCode}** → Notify when a user joins a specific chatroom
- **/topic/{roomCode}** → Receives broadcasted messages for a specific chatroom

## License
This project is open-source and available under the [MIT License](LICENSE).
