import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5002);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String input = in.readLine();
                // Format: type:expression

                String[] parts = input.split(":");
                String type = parts[0];
                String expr = parts[1];

                String result = ExpressionConverter.process(type, expr);

                out.println(result);

                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}