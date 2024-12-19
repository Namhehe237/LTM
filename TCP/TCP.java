package TCP;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2209)) {
            socket.setSoTimeout(5000);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject("B21DCVT312;E9yWhH4T");
            objectOutputStream.flush();

            Product product = (Product) objectInputStream.readObject();
            System.out.println(product.toString());

        
            String tmp = String.valueOf(product.getPrice());
            for (int i =0;i<tmp.length();i++){
                if (tmp.charAt(i) == '.') break;
                product.setDiscount(product.getDiscount()+ Integer.parseInt(Character.toString(tmp.charAt(i))));
            }

            System.out.println(product.toString());

            objectOutputStream.writeObject(product);
            objectOutputStream.flush();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
