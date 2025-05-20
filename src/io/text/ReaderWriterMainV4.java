package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("== write string ==");
        System.out.println(writeString);

        // == new OutputStreamWriter(new FileOutputStream(FILE_NAME), UTF_8);
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, 8192);
        bw.write(writeString);
        bw.close();

        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, 8192);
        StringBuilder sb = new StringBuilder();
        String line;

        // 한 줄 단위로 문자를 읽고 String 반환
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();

        System.out.println("== Read String ==");
        System.out.println(sb);
    }
}
