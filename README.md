# Multi-Thread Client-Server Chat with Sockets

This is a multi-threaded client-server chat application developed using Java Sockets. It includes various components and examples for creating a chat application using Java.

## Components

- **Server.java:** A simple test for a Server Socket.

- **Client.java:** A simple test for a client socket.

- **ClientMT.java:** Demonstrates creating multi-clients Java Sockets using threads. This allows multiple clients to connect to the server concurrently.

- **ServerMT.java:** A multi-server socket that accepts multiple clients using threads. It can handle multiple client connections simultaneously.

- **ClientChat.java:** An extension of ClientMT.java with a GUI using JavaFX. It provides a graphical user interface for the client-side of the chat application.

- **ServerChat.java:** An extension of ServerMT.java with a GUI using JavaFX. It provides a graphical user interface for the server-side of the chat application.

## Example

Here's a screenshot of the application in action:

![Screenshot](https://user-images.githubusercontent.com/79877072/216454663-7642d040-7946-4d79-a26e-fc8fecf6ae19.PNG)

## How to Use

1. Clone the repository to your local machine.

2. Compile and run the server and client files according to your requirements.

3. You can experiment with both the command-line-based examples (Server.java and Client.java) and the GUI-based chat application (ClientChat.java and ServerChat.java).

Feel free to explore and modify the code to create your custom chat application using Java Sockets.

## License

This project is open-source and available under the [MIT License](LICENSE).
