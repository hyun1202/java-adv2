package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        int data;
        // read는 int로 반환한다 - 부호 없는 바이트 표현
        // int로 반환함으로써 0에서 255까지의 모든 가능한 바이트 값을 부호 없이 표현할 수 있다.
        // 자바의 byte는 -128 ~ 127까지 256종류의 값만 가질 수 있어 EOF를 위한 특별한 값을 할당하기 어렵다.
        // int는 0~255까지 모든 가능한 바이트 값을 표현하고, 여기에 추가로 -1을 반환하여 스트림의 끝을 나타낼 수 있다.
        while ((data=fis.read()) != -1) {
            System.out.println(data);
        }
        fis.close();
    }
}
