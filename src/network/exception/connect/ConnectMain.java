package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {
    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }

    // 호스트를 알 수 없음
    private static void unknownHostEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unknownHostEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 연결 거절 -> 네트워크를 통해 해당 ip의 서버에 접속은 했음
    // port를 사용하지 않으므로 tcp 연결 거절
    // 방화벽에서 무단 연결로 인지하고 연결을 막을 때도 발생
    // OS는 TCP RST라는 패킷을 보내 연결 거절
    // 클라이언트가 연결 시도 중 RST 패킷을 받으면 예외 발생
    private static void connectionRefused() throws IOException {
        try {
            Socket socket = new Socket("localhost", 45678); // 미사용 포트
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
