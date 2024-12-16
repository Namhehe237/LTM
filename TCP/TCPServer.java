package TCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2307);
        System.out.println("Server is running");
        while (true) {
            Socket socket = serverSocket.accept();  

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


            dataOutputStream.writeUTF("I receive the number you send");

            dataOutputStream.close();
         
        }
    }
}