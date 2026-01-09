package com.tushar.resumeai.helper;


import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class DocxHelper {
    public String extractText (File file){

        try(FileInputStream fis = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document)){

            return extractor.getText();

        }catch (IOException e){
            throw new RuntimeException("Failed to parse DOCX file: " + e.getMessage());
        }
    }

}
