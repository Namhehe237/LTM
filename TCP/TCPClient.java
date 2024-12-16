package TCP;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 2307);
        System.out.println(socket);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String res = bufferedReader.readLine();
        System.out.println(res);

        // InputStream inputStream = socket.getInputStream();
        // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        // String reString = bufferedReader.readLine();
        // System.out.println(reString.trim());

       
    }
}
