package com.tushar.resumeai.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public boolean isValidResume(MultipartFile file){
        String filename = file.getOriginalFilename();
         if(filename  == null) return false;

         return filename.endsWith(".pdf") || filename.endsWith(".docx");
    }

}

