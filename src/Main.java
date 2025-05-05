import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5050;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servern körs på port " + port);

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("Klient ansluten: " + client.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String expression = in.readLine();
            String response = calculateExpression(expression);

            out.println(response);

            client.close();
        }
    }

    private static String calculateExpression(String expr) {
        try {
            expr = expr.replaceAll(" ", "");
            if (expr.contains("+")) {
                String[] parts = expr.split("\\+");
                int result = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
                return "Summan av " + expr + " är " + result;
            } else if (expr.contains("*")) {
                String[] parts = expr.split("\\*");
                int result = Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                return "Produkten av " + expr + " är " + result;
            } else {
                return "Ogiltigt uttryck.";
            }
        } catch (Exception e) {
            return "Fel vid beräkning.";
        }
    }
}
