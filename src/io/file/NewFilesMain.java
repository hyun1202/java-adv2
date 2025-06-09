package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path dir = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + " File already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(dir);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(dir + " Directory already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Files.delete(file);
//        System.out.println("File deleted");

        System.out.println("is regular file: " + Files.isRegularFile(file));
        System.out.println("is directory: " + Files.isDirectory(dir));
        System.out.println("File name: " + file.getFileName());
        System.out.println("File size: " + Files.size(file) + " bytes");

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("file moved/renamed");

        System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

        // attributes
        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
        attrs.isDirectory();
        attrs.creationTime();
        attrs.size();
        attrs.lastModifiedTime();
    }
}
