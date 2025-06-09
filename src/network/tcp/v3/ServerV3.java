package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

// main 스레드: 새로운 연결이 있을 때마다 Session 객체와 별도의 스레드를 생성하고, 별도의 스레드가 Session 객체를 실행하도록 함
// session 담당 스레드: 자신의 소켓이 연결된 클라이언트와 메시지를 반복해서 주고 받는 역할
public class ServerV3 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();  // 블로킹
            log("소켓 연결: " + socket);

            SessionV3 session = new SessionV3(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
