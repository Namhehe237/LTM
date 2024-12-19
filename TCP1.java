import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109",2207)){

            socket.setSoTimeout(5000);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String message = "B21DCVT312;VGjFCXoD";
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();

            int n = dataInputStream.readInt();
            System.out.println("Received array size: " + n);

            int[] number = new int[n];
            for (int i=0;i<n;i++){
                number[i] = dataInputStream.readInt();
            }

            int sum = 0;
            for (int num : number) {
                sum += num;
            }
            float mean = (float) sum / n;

            float variance = 0;
            for (int num : number) {
                variance += (num - mean) * (num - mean);
            }
            variance /= n;

            // Gửi kết quả lên server
            dataOutputStream.writeInt(sum);
            dataOutputStream.writeFloat(mean);
            dataOutputStream.writeFloat(variance);
            dataOutputStream.flush();

            System.out.println("Sent sum: " + sum);
            System.out.println("Sent mean: " + mean);
            System.out.println("Sent variance: " + variance);

            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
