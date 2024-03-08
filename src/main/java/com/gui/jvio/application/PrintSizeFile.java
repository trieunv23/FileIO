package com.gui.jvio.application;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;


public class PrintSizeFile {
    public static String sizeFile(File file){
        if (file.exists() && !file.isDirectory()) {
            DecimalFormat df = new DecimalFormat("#.##");
            long size_file_bytes = file.length();
            double kb_size_file = (double) size_file_bytes / 1024 ;
            double mb_size_file = (double) kb_size_file / 1024 ;
            if (size_file_bytes < 500) {
                return String.valueOf(size_file_bytes) + " Bytes" ;
            } else {
                if (kb_size_file < 500){
                    return String.valueOf(df.format(kb_size_file)) + " KB" ;
                } else {
                    return String.valueOf(df.format(mb_size_file)) + " MB" ;
                }
            }
        }
        return "-1" ;
    }

    public static long sizeFolder(File folder){
        long size_file = 0 ;
        if (folder.isDirectory()){
            File[] list_file = folder.listFiles() ;
            if (list_file != null ){
                for (File file : list_file){
                    if (file.exists() && !file.isDirectory()){
                        size_file = size_file + file.length() ;
                    } else {
                        size_file = size_file + sizeFolder(file) ;
                    }
                }
            }
        }
       return size_file ;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in) ;
        String url = scanner.nextLine() ;
        File file = new File(url) ;
        if (file.isDirectory()){
            System.out.println("Size folder '" + url + "' : " + sizeFolder(file));
        } else if (file.isFile() && file.exists()){
            System.out.println("Size file '" + url + "' : " + sizeFile(file));
        } else {
            System.out.println("File not exists!");
        }
    }
}

