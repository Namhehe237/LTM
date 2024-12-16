package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();

        String sendString = "Thanh nam";
        DatagramPacket senDatagramPacket = new DatagramPacket(sendString.getBytes(), sendString.length(), InetAddress.getByName("localhost"), 2307);
        datagramSocket.send(senDatagramPacket);
    }
}
