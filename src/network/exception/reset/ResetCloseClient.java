package network.exception.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class ResetCloseClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // server -> client: FIN
        Thread.sleep(1000); // FIN 전송 대기

        // client -> server: PUSH[1]
        output.write(1);

        // server -> client: RST
        Thread.sleep(1000); // RST 전송 대기
        try {
            // Connection reset Exception
            int read = input.read();
            System.out.println("read = " + read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // broken pipe
            output.write(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
