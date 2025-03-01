package com.jwt.serviceImpl.fileservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl  implements FileService{

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        //get original file name
        String fileName=file.getOriginalFilename();

       
        //get file path
        String filePath=path + File.separator + fileName;

        //create file object
        File f= new File(path);

        //create dir if not exist
        if(!f.exists()){
            f.mkdir();
        }
        //copy the file or upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;

    }
    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath= path+ File.separator+ filename;
        return new  FileInputStream(filePath);
    }
    
}
