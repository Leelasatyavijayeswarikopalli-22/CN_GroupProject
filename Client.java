import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5002);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter type (e.g., infix_to_postfix): ");
            String type = sc.nextLine();

            System.out.println("Enter expression: ");
            String expr = sc.nextLine();

            out.println(type + ":" + expr);

            String response = in.readLine();
            System.out.println("Result: " + response);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}