package io.text;

import java.io.*;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        // 문자를 byte[]로 변환한다.
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);
        osw.write(writeString);
        osw.close();

        FileInputStream fis = new FileInputStream(FILE_NAME);
        // byte[] -> char 변경
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        StringBuilder sb = new StringBuilder();
        int ch;
        while((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }

        isr.close();

        System.out.println("readString = " + sb);
    }
}
