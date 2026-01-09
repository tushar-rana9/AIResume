package com.tushar.resumeai.helper;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import  java.io.IOException;

import java.io.File;

@Component
public class PdfHelper {

    public String extractText(File file){
        try(PDDocument document = PDDocument.load(file)){

            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e){
            throw new RuntimeException("Failed to Parse PDF file: " + e.getMessage());
        }
    }
}
