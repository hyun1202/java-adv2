package io.streams;

import java.io.*;

public class DataStreamEtcMain {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        // 자바 데이터 타입을 저장한다.
        dos.writeUTF("회원A");
        dos.writeInt(20);
        dos.writeDouble(10.5);
        dos.writeBoolean(true);
        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    }
}
