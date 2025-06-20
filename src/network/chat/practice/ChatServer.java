package network.chat.practice;


import network.tcp.v5.SessionV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/*
* 채팅 명령어
*
* 입장: /join | {name}
* 메시지: /message | {message}
* 이름 변경: /change | {name}
* 전체 사용자: /users
* 종료: /exit
* */
public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        ChatSessionManager chatSessionManager = new ChatSessionManager();

        while (true) {
            Socket socket = serverSocket.accept();  // 블로킹
            log("소켓 연결: " + socket);

            ChatSession session = new ChatSession(socket, chatSessionManager);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
