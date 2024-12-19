import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class TCPClient {
     public static String res;

    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2208)){

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String sendString = "B21DCVT312;435rR8lu";

            bufferedWriter.write(sendString);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String data = bufferedReader.readLine();
            int index=0;
            int index_tmp=0;
            int max_length = 0;
            System.out.println(data);

            StringTokenizer stringTokenizer = new StringTokenizer(data);
            while (stringTokenizer.hasMoreTokens()) {
                String tmpString = stringTokenizer.nextToken();
                index_tmp++;
                if (tmpString.length() > max_length){
                    index=index_tmp;
                    res=tmpString;
                    max_length = tmpString.length();
                }
                System.out.println(index_tmp+" "+   tmpString+" "+tmpString.length());
            }
            bufferedWriter.write(res);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(index);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println(res + " "+ index);

            bufferedReader.close();
            bufferedWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}