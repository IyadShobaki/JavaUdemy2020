package com.shobaki;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {
        try {
//          Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
//          Files.createFile(fileToCreate);

//          Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
//          Files.createDirectory(dirToCreate);

//          Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2/Dir3/Dir4/Dir5/Dir6");
//          Path dirToCreate = FileSystems.getDefault().getPath("Examples/Dir2/Dir3/Dir4/Dir5/Dir6/Dir7");
//          Files.createDirectories(dirToCreate);

            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1/file1.txt");
            long size = Files.size(filePath);
            System.out.println("Size = " + size);
            System.out.println("Last modified = " + Files.getLastModifiedTime(filePath));

            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size = " + attrs.size());
            System.out.println("Last modified = " + attrs.lastModifiedTime());
            System.out.println("Created= " + attrs.creationTime());
            System.out.println("Is directory = " + attrs.isDirectory());
            System.out.println("Is regular file = " + attrs.isRegularFile());
            System.out.println("Is Other = " + attrs.isOther());


            //------------------------------------------------------

//          Path fileToDelete = FileSystems.getDefault().getPath("Examples" , "Dir1", "file1copy.txt");
//          Files.deleteIfExists(fileToDelete);

//          Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1.txt");
//          Path destination = FileSystems.getDefault().getPath("Examples", "file2.txt");  //this will rename the file
//          Files.move(fileToMove, destination);

//          Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//          Path destination = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
//          Files.move(fileToMove, destination);

//          Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
//          Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//          Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
//
//          sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
//          copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
//          Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

//    public static void main(String[] args) {
//
//        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
//        printFile(path);
//        System.out.println();
////        Path filePath = FileSystems.getDefault().getPath("files","SubdirectoryFile.txt");
//        Path filePath = Paths.get(".","files","SubdirectoryFile.txt");
//        printFile(filePath);
//
//        System.out.println();
////        filePath = Paths.get("D:/JavaUdemy2020/OutThere.txt");     //working fine
////        filePath = Paths.get("D:\\JavaUdemy2020\\OutThere.txt");    //working fine
////        filePath = Paths.get("D:","JavaUdemy2020","OutThere.txt");  //working fine
//        filePath = Paths.get("D:\\","\\JavaUdemy2020","\\OutThere.txt");  //working fine
//        printFile(filePath);
//
//        System.out.println();
//        filePath = Paths.get(".");
//        System.out.println(filePath.toAbsolutePath());
//
//        System.out.println();
//        Path path2 = FileSystems.getDefault().getPath(".", "files" , ".." , "files" , "SubdirectoryFile.txt");
//        System.out.println(path2.normalize().toAbsolutePath());
//        printFile(path2);
//
//        System.out.println();
//        Path path3 =FileSystems.getDefault().getPath("thisfiledoesntexist.txt");
//        System.out.println(path3.toAbsolutePath());
//        System.out.println();
//
//        Path path4 = Paths.get("Z:\\", "asdsad", "whatever.txt");
//        System.out.println(path4.toAbsolutePath());
//        System.out.println("Exists = " + Files.exists(path4));
//
//
//        filePath = FileSystems.getDefault().getPath("files");
//        System.out.println("Exists = " + Files.exists(filePath));
//
//
//
//    }
//
//    private static void printFile(Path path) {
//        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
//            String line;
//            while ((line = fileReader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
}
