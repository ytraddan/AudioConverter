package com.convert.audioconverter;
import java.io.*;

class Convertor {
    private final String filePath;
    private final String outPath;

    Convertor (File file, String outFormat){
        filePath = file.getPath();
        String filename = removeFileExtension(file.getName());
        outPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\" + filename + outFormat;
    }

    public String Convert (){
        if (isExist()) {
            return "File already exist!";
        }
        String[] cmd = {".\\ffmpeg", "-i", filePath, "-b:a", "320k", "-bufsize", "320k", outPath};
        try {
            return runConversion(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String runConversion(String[] cmd) throws IOException{
        ProcessBuilder f = new ProcessBuilder(cmd);
        f.redirectErrorStream(true);
        Process process = f.start();
        InputStream stdout = process.getInputStream() ;
        stdout.transferTo(System.out);
        //Checks if file was converted
        if (!new File(outPath).isFile()) {
            System.out.println("Wrong file type");
            return "Wrong file type!";
        }
        System.out.println("Success! File created at " + outPath);
        return "Success!\nFile created at " + outPath;
    }

    //Checks if out_path already points to a file with same name
    private boolean isExist() {
        if (new File(outPath).isFile()) {
            System.out.println("File already exist!");
            return true;
        }
        else
            return false;
    }

    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.][^.]*$";
        return filename.replaceAll(extPattern, "");
    }
}