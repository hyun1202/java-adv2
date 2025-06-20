package network.chat.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ChatClient {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            log("소켓 연결: " + socket);
            Scanner scanner = new Scanner(System.in);

            // 이름 입력
            System.out.print("이름을 입력하세요: ");
            String name = scanner.nextLine();
            output.writeUTF("/join|" + name);

            // 메시지 받는 다른 스레드 추가
            Thread thread = new Thread(() -> {
                while(true) {
                    try {
                        String receiveMessage = input.readUTF();
                        System.out.println(receiveMessage);
                    } catch (IOException e) {
                        log("메시지 받기 종료");
                        break;
                    }
                }
            });

            thread.start();

            while(true) {
                String inputMessage = scanner.nextLine();
                if (inputMessage.equals("/exit")) {
                    log("채팅 서버를 종료합니다.");
                    break;
                }

                if (inputMessage.startsWith("/")) {
                    output.writeUTF(inputMessage);
                    continue;
                }

                // 명령 모드가 아닌 경우, 메시지로 전송
                output.writeUTF("/message|" + inputMessage);
            }
        }
    }
}
