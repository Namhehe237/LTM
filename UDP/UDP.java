package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class UDP {

    public static void main(String[] args) {

        String studentCode = "B21DCVT312";
        String qCode = "lZNk5WIV";
        String message = ";" + studentCode + ";" + qCode;

        try (DatagramSocket datagramSocket = new DatagramSocket()) {

            byte[] sendData = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2209);
            datagramSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            byte[] requestIdBytes = new byte[8];
            System.arraycopy(receiveData, 0, requestIdBytes, 0, 8);
            String requestId = new String(requestIdBytes).trim();

            System.out.println(requestId);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData, 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product receivedProduct = (Product) ois.readObject();

            System.out.println(receivedProduct.toString());

            receivedProduct.setName(standardName(receivedProduct.getName()));
            receivedProduct.setQuantity(standardQuantity(receivedProduct.getQuantity()));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(receivedProduct);
            objectOutputStream.flush();

            byte[] productData = byteArrayOutputStream.toByteArray();
            byte[] sendBackData = new byte[8 + productData.length];

            System.arraycopy(requestIdBytes, 0, sendBackData, 0, 8);
            System.arraycopy(productData, 0, sendBackData, 8, productData.length);

            DatagramPacket sendBackPacket = new DatagramPacket(sendBackData, sendBackData.length, serverAddress, 2209);
            datagramSocket.send(sendBackPacket);

            System.out.println(receivedProduct.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int standardQuantity(int quantity) {
        // TODO Auto-generated method stub
        String res = String.valueOf(quantity);
        StringBuilder stringBuilder = new StringBuilder(res);
        res = stringBuilder.reverse().toString().trim();

        return Integer.parseInt(res);
    }

    private static String standardName(String name) {
        // TODO Auto-generated method stub
        StringTokenizer stringTokenizer = new StringTokenizer(name);
        String res="";
        ArrayList<String> arrayList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        
        Collections.swap(arrayList, 0, arrayList.size()-1);
        for(String x:arrayList) res+=x.trim()+" ";

        return res.trim();
        // throw new UnsupportedOperationException("Unimplemented method 'standardName'");
    }
}