package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeoutMain1 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        // 해당 서버가 바쁘거나 문제가 있어 연결 응답 패킷을 보내지 못할 수 있다.
        // TCP 연결 대기 시간
        // windows: 약 21초
        // Linux: 75~180초 사이
        // mac: 75초
        try {
            Socket socket = new Socket("192.168.1.250", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("connect time = " + (end - start));
    }
}
