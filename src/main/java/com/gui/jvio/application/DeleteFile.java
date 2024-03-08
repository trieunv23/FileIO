package com.gui.jvio.application;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

import static com.gui.jvio.application.PrintSizeFile.sizeFile;

public class DeleteFile {
    public static void main(String[] args) {
        boolean check_exists_file = false ;
        String type = "" ;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter url: ");
        String url = sc.nextLine() ;
        File file = new File(url) ;
        if (file.isFile()){
            check_exists_file = true ;
            type = "FILE" ;
            System.out.println("File has size: " + sizeFile(file));
        } else if (file.isDirectory()){
            check_exists_file = true ;
            type = "FOLDER" ;
            DecimalFormat df = new DecimalFormat("#.##");
            long size_file_bytes = file.length();
            double kb_size_file = (double) size_file_bytes / 1024 ;
            double mb_size_file = (double) kb_size_file / 1024 ;
            System.out.print("Folder has size: ");
            if (size_file_bytes < 500) {
                System.out.println(String.valueOf(size_file_bytes) + " Bytes");
            } else {
                if (kb_size_file < 500){
                    System.out.println(String.valueOf(df.format(kb_size_file)) + " KB");
                } else {
                    System.out.println(String.valueOf(df.format(mb_size_file)) + " MB");
                }
            }
        }

        if (check_exists_file){
            System.out.println("Do you want dele '" + url + "' (y/n) : ");
            String choice = sc.nextLine() ;
            boolean result = false ;
            switch (choice){
                case "y" :
                    if (type == "FILE") {
                        result = deleteFile(file) ;

                    } else if (type == "FOLDER"){
                        result = deleteFolder(file) ;
                    }
                    break ;
                case "n" :
                    System.out.println("Do not delete files!");
                    break ;
            }
            if (result){
                System.out.println("Delete succesfully");
            } else {
                System.out.println("Delete unsuccesfull");
            }
        } else {
            System.out.println("File not exists, delete unsuccesfull!");
        }
    }

    public static boolean deleteFile(File file) {
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Nếu là thư mục, gọi đệ quy để xóa
                        deleteFolder(file);
                    } else {
                        // Nếu là file, xóa file
                        file.delete();
                    }
                }
            }
            // Sau khi xóa tất cả file và thư mục con, xóa thư mục chính
            return folder.delete();
        }
        return false;
    }
}
