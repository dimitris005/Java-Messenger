# Java-Messenger

This is an instant messaging app written in Java. It consists of three main components: ChatClient, ChatServer, and ClientHandler. The app allows users to connect to the server and exchange messages in real-time.

## Features

- Connect to the ChatServer using the ChatClient.
- Send and receive messages to/from other connected users.
- Server-side ClientHandler manages incoming client connections and message broadcasting.

## Technologies Used

- Java
- Socket programming
- Multithreading

<!--
## How to Use

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Start the ChatServer by running the `ChatServer.java` file.
4. Run the `ChatClient.java` file to start a new client instance.
5. Enter a unique username when prompted.
6. Begin sending and receiving messages with other connected clients.
-->

## Project Structure

The project is organized as follows:

- `ChatClient.java`: Implements the client-side functionality for connecting to the server and sending/receiving messages.
- `ChatServer.java`: Implements the server-side functionality for managing client connections and message broadcasting.
- `ClientHandler.java`: Represents a client connection on the server and handles incoming messages.
