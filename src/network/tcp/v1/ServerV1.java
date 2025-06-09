package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // tcp 연결이 완료되면 OS backlog queue라는 곳에 클라이언트와 서버의 TCP 연결 정보를 보관
        // 서버와 클라이언트와 정보를 주고받기 위해서는 socket 객체가 필요
        // accept()를 호출하면 backlog queue에서 TCP 연결 정보를 조회한다.
        // 사용한 TCP 연결 정보는 backlog queue에서 제거된다.
        Socket socket = serverSocket.accept();
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String received = input.readUTF();
        log("client -> server: " + received);

        String toSend = received + " World!";
        output.writeUTF(toSend);

        // 자원 정리
        log("연결 종료 socket: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
