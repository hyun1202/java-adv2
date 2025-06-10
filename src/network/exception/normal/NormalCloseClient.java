package network.exception.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        readByInputStream(input, socket);
//        readByBufferedReader(input, socket);
//        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    // FIN 패킷을 받으면 더는 서버를 통해 읽은 데이터가 없다.
    // EOF 발생 = FIN 메시지를 보내고 소켓 연결 종료
    // FIN을 받으면 상대방에게 FIN 메시지를 보내고 소켓 연결을 끊어야 한다.
    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        System.out.println("read = " + read);
        if (read == -1) {
            input.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("read = " + readString);
        if (readString == null) {
            br.close();
            input.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        try {
            dis.readUTF();
        } catch (IOException e) {
            log(e);
        } finally {
            dis.close();
            input.close();
            socket.close();
        }
    }
}
