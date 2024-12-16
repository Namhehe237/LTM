package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket= new DatagramSocket(2307);
        System.out.println("Server is running");
        while (true) {
            byte[] buf = new byte[2048];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length-1024);
            datagramSocket.receive(datagramPacket);
            String res = new String(datagramPacket.getData()).trim();
            System.out.println(res);
        }
    }
}