import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5003);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            // MENU
            System.out.println("===== Expression Converter =====");
            System.out.println("1. Infix to Postfix");
            System.out.println("2. Infix to Prefix");
            System.out.println("3. Postfix to Infix");
            System.out.println("4. Prefix to Infix");
            System.out.println("5. Postfix to Prefix");
            System.out.println("6. Prefix to Postfix");
            System.out.print("Enter your choice (1-6): ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            String type = "";

            switch (choice) {
                case 1:
                    type = "infix_to_postfix";
                    break;
                case 2:
                    type = "infix_to_prefix";
                    break;
                case 3:
                    type = "postfix_to_infix";
                    break;
                case 4:
                    type = "prefix_to_infix";
                    break;
                case 5:
                    type = "postfix_to_prefix";
                    break;
                case 6:
                    type = "prefix_to_postfix";
                    break;
                default:
                    System.out.println("Invalid choice!");
                    socket.close();
                    return;
            }

            System.out.print("Enter expression: ");
            String expr = sc.nextLine();

            // Send to server
            out.println(type + ":" + expr);

            // Receive result
            String response = in.readLine();
            System.out.println("Result: " + response);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}