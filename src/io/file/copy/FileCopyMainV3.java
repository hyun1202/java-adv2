package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        String resultFileName = "temp/copy_new.dat";

        long start = System.currentTimeMillis();

        Path source = Path.of(fileName);
        Path target = Path.of(resultFileName);
        // 운영 체제의 파일 복사 기능을 사용
        // 파일 -> 파일 복사에서 유용
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        long end = System.currentTimeMillis();

        System.out.println("TIME taken : " + (end - start) + "ms");
    }
}
