package network.exception.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMain2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        // 해당 서버가 바쁘거나 문제가 있어 연결 응답 패킷을 보내지 못할 수 있다.
        // TCP 연결 대기 시간
        // windows: 약 21초
        // Linux: 75~180초 사이
        // mac: 75초
        try {
            Socket socket = new Socket();
            // 소켓 객체 생성 후 connect()를 호출했을 때 TCP 연결 시작
            // SocketTimeout: TCP 연결과 관련이 되어있음
            // ReadTimeout: 서버에 사용자가 폭주하고 느려져서 응답을 못하는 상황
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("connect time = " + (end - start));
    }
}
