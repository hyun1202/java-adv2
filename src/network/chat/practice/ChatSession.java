package network.chat.practice;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static util.MyLogger.log;

public class ChatSession implements Runnable {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final ChatSessionManager chatSessionManager;
    private String name;

    public ChatSession(Socket socket, ChatSessionManager chatSessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.chatSessionManager = chatSessionManager;
        chatSessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            String received = null;
            while (true) {
                received = input.readUTF();
                log("client -> server: " + received);

                if (!received.isEmpty()) {
                    String[] split = received.split("\\|");
                    String command = split[0].trim();
                    String message = "";
                    if (split.length > 1) {
                        message = split[1];
                    }

                    if (message.equals("/exit")) {
                        log("채팅 서버 접속을 종료합니다.");
                        break;
                    }

                    commandRun(command, message);
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            SocketCloseUtil.closeAll(socket, input, output);
        }

        log("연결 종료: " + socket + " is Closed " + socket.isClosed());
    }

    private void commandRun(String command, String message) {
        switch (command) {
            case "/join":
                changeName(message);
                break;
            case "/message":
                broadCast(name, message);
                break;
            case "/change":
                changeName(message);
                break;
            case "/users":
                printAllUsers();
                break;
        }
    }

    private void printAllUsers() {
        List<ChatSession> sessions = chatSessionManager.getSessions();
        StringBuilder sb = new StringBuilder();
        sb.append("유저 접속 목록: \n");
        sessions.stream()
                .map(ChatSession::getName)
                .forEach(name -> sb.append(name).append("\n"));

        try {
            output.writeUTF(sb.toString());
        } catch (IOException e) {
            log(e);
        }
    }

    private void changeName(String name) {
        this.name = name;
    }

    private void broadCast(String name, String message) {
        List<ChatSession> sessions = chatSessionManager.getSessions();
        System.out.println("sessions = " + sessions);

        sessions.forEach(session -> {
            try {
                session.getOutput().writeUTF("[" + name + "]: " + message);
            } catch (IOException e) {
                log(e);
            }
        });
    }

    public String getName() {
        return name;
    }

    public DataOutputStream getOutput() {
        return output;
    }
}
