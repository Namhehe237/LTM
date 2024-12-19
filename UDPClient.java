import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            
            String message = ";B21DCVT312;ncjgCPh8";
            DatagramPacket sendDatagramPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("203.162.10.109"), 2207);
            datagramSocket.send(sendDatagramPacket);

            byte[] buffer = new byte[2048];
            DatagramPacket rcvDatagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(rcvDatagramPacket);

            String res = new String(rcvDatagramPacket.getData());
    
            ArrayList<String> arrayList_res = new ArrayList<>();
            TreeSet<Integer> treeSet = new TreeSet<>();
            
            String[] cStrings = res.split("\\;");
         
            res = cStrings[2];
            String[] tmpsStrings = res.split("\\,");
            ArrayList<String> arrayList_tmp = new ArrayList<>(Arrays.asList(tmpsStrings));
            
            for( int i = 1 ; i<= Integer.parseInt(cStrings[1]);i++){
                String tmpString = String.valueOf(i);
                if (!arrayList_tmp.contains(tmpString))treeSet.add(Integer.parseInt(tmpString));
            }


            String sendString =cStrings[0]+";";
            
            for(Integer x : treeSet) sendString+=x+",";
            sendString = sendString.substring(0, sendString.length()-1).trim();

            sendDatagramPacket = new DatagramPacket(sendString.getBytes(), sendString.length(), InetAddress.getByName("203.162.10.109"), 2207);
            datagramSocket.send(rcvDatagramPacket);

            System.out.println(sendString);
            
            datagramSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
