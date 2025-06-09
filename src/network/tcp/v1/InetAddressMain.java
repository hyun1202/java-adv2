package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

// 찾는 과정
// 1. hosts 파일 확인
// 2. DNS 서버에서 IP 주소를 얻어옴
public class InetAddressMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google);
    }
}
