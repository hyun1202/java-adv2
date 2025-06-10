package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV6 implements Runnable {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
        // try-with-resources는 사용과 해제를 함께 묶어서 처리할 때 사용하므로 여기서는 사용할 수가 없다.
        try {
            while (true) {
                String received = input.readUTF(); // 블로킹
                log("client -> server: " + received);

                String toSend = received + " World!";
                output.writeUTF(toSend);

                if (received.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            close();
        }
    }

    // 세션 종료시, 서버 종료 시 동시에 호출될 수 있다.
    public synchronized void close() {
        // 중복 호출 방지
        if (closed) {
            return;
        }
        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket + " is Closed " + socket.isClosed());
    }
}
