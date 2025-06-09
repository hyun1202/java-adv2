package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());

        boolean created = file.createNewFile();
        System.out.println("File created: " + created);

        boolean dirCreated = directory.mkdir();
        System.out.println("Directory created: " + dirCreated);


        System.out.println("File isFile: " + file.isFile());
        System.out.println("Directory isDirectory: " + directory.isDirectory());
        System.out.println("File name: " + file.getName());
        System.out.println("File size: " + file.length() + "bytes");

        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);
        System.out.println("renamed = " + renamed);

        long lastModified = newFile.lastModified();
        System.out.println("new Date(lastModified) = " + new Date(lastModified));

//        boolean deleted = file.delete();
//        System.out.println("File deleted: " + deleted);
    }
}
