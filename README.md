# Java TCP Chat Server

A TCP client-server application built entirely with Java, without external frameworks.

The project was developed as a practical study of network programming, focusing on how clients and servers communicate through TCP sockets, how data is exchanged using streams, and how multiple connections can be managed concurrently.

## Overview

The application consists of a TCP server and clients that establish persistent socket connections with the server.

The server is responsible for accepting connections, identifying clients, managing connected users, and exchanging messages between clients.

```text
Client A
   |
   | TCP
   v
+-------------------+
|       Server      |
|                   |
|  ServerSocket     |
|  Client Management|
|  Message Handling |
+-------------------+
   ^
   | TCP
   |
Client B
```

The project intentionally uses Java's low-level networking APIs instead of a framework to provide a better understanding of what happens underneath higher-level communication technologies.

## Features

* TCP client-server communication
* Server implemented with `ServerSocket`
* Client connections using `Socket`
* Input and output stream handling
* Client identification
* Connected client management
* Text-based message communication
* Multiple client connections
* Basic real-time communication
* Separation between server and client responsibilities

## Technologies

| Technology     | Purpose                      |
| -------------- | ---------------------------- |
| Java           | Application development      |
| TCP/IP         | Network communication        |
| `ServerSocket` | Accepting client connections |
| `Socket`       | Client-server communication  |
| Java I/O       | Reading and writing data     |
| Threads        | Concurrent client handling   |
| Git            | Version control              |

The project is intentionally implemented without frameworks.

## Project Structure

```text
src/
└── main/
    └── java/
        └── com/
            └── websocket/
                ├── Server.java
                ├── Client.java
                └── ...
```

The exact structure may evolve as new networking features are implemented.

## How It Works

The server creates a `ServerSocket` and waits for incoming connections.

```java
ServerSocket server = new ServerSocket(PORT);
Socket client = server.accept();
```

When a client connects, the server receives a dedicated `Socket` representing that connection.

Communication is performed through the socket's input and output streams:

```java
InputStream input = client.getInputStream();
OutputStream output = client.getOutputStream();
```

Each connected client can then be handled independently, allowing the server to communicate with multiple clients.

## Running the Project

### Requirements

* Java JDK 17 or newer
* Git

Check your Java installation:

```bash
java -version
```

### Clone the repository

```bash
git clone https://github.com/Matheus-MMVIII/WebSocket.git
cd WebSocket
```

### Compile

Compile the Java source files using your IDE or Java compiler.

If using IntelliJ IDEA, open the project and run the server and client classes separately.

### Start the server

Start the `Server` class first.

The server will begin listening for incoming TCP connections.

### Start a client

After the server is running, start one or more instances of the `Client` class.

Each client establishes a TCP connection with the server and can communicate through the established connection.

## Architecture

The application follows a basic client-server architecture:

```text
                    TCP Connection
                         |
                         v
+----------+      +-------------+      +----------+
| Client A | ---> |             | <--- | Client B |
+----------+      |    Server   |      +----------+
                  |             |
+----------+      | ServerSocket|
| Client C | ---> |   + Sockets |
+----------+      +-------------+
```

The server acts as the central point responsible for managing client connections.

## Concepts Studied

This project is being developed incrementally to study:

* TCP/IP
* Client-server architecture
* `ServerSocket`
* `Socket`
* Input streams
* Output streams
* Blocking I/O
* Threads
* Concurrency
* Connection management
* Session management
* Message protocols
* Real-time communication
* Network programming
* WebSocket fundamentals

## Why Pure Java?

Using Java's standard networking APIs makes the underlying communication process explicit.

Instead of relying on frameworks that abstract sockets, connections, and data streams, this project works directly with Java's networking and I/O APIs.

This makes it possible to understand the foundations that higher-level technologies build upon.

## Future Improvements

Planned improvements include:

* Dedicated client handler threads
* Structured message protocol
* User authentication
* Session management
* Private messages
* Broadcast messages
* Multiple chat rooms
* Graceful connection handling
* Improved error handling
* Message serialization
* Persistent message history
* HTTP server integration
* WebSocket implementation

## Learning Progression

The project is intended to evolve from a basic TCP server into a more complete real-time communication system.

```text
TCP
 |
 v
Sockets
 |
 v
Streams
 |
 v
Threads
 |
 v
Concurrency
 |
 v
Client Management
 |
 v
Message Protocol
 |
 v
Authentication
 |
 v
Sessions
 |
 v
HTTP
 |
 v
WebSocket
```

## Author

**Matheus Viturino Ferreira**

