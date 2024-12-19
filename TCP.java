import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TCP {
    public static Integer number1;
    public static Integer number2;
    public static Integer gap;

    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2206)) {

            socket.setSoTimeout(5000);

            // Luồng ra và vào dạng byte
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            // Gửi mã sinh viên và mã câu hỏi lên server
            String request = "B21DCVT312;5Zi34kDT";
            outputStream.write(request.getBytes());
            outputStream.flush();

            // Nhận dữ liệu từ server
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead == -1) {
                System.out.println("Không nhận được dữ liệu từ server.");
                return;
            }

            String receivedData = new String(buffer, 0, bytesRead).trim();
            System.out.println("Dữ liệu nhận từ server: " + receivedData);

            // Phân tích dữ liệu và tìm khoảng cách nhỏ nhất
            String[] numbersStr = receivedData.split(",");
            int[] numbers = Arrays.stream(numbersStr).mapToInt(Integer::parseInt).toArray();

            // Tìm khoảng cách nhỏ nhất
            int minDistance = Integer.MAX_VALUE;
            int num1 = 0, num2 = 0;

            Arrays.sort(numbers); // Sắp xếp mảng để tính khoảng cách dễ hơn
            for (int i = 1; i < numbers.length; i++) {
                int distance = numbers[i] - numbers[i - 1];
                if (distance < minDistance) {
                    minDistance = distance;
                    num1 = numbers[i - 1];
                    num2 = numbers[i];
                }
            }

            // Chuẩn bị kết quả để gửi lại server
            String result = minDistance + "," + num1 + "," + num2;
            outputStream.write(result.getBytes());
            outputStream.flush();
            System.out.println("Kết quả gửi lên server: " + result);

            // Đóng kết nối
            System.out.println("Đóng kết nối.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
