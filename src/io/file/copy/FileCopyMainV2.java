package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV2 {

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        String resultFileName = "temp/copy_new.dat";

        long start = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(resultFileName);

        fis.transferTo(fos);

        fos.close();
        fis.close();

        long end = System.currentTimeMillis();

        System.out.println("TIME taken : " + (end - start) + "ms");
    }
}
