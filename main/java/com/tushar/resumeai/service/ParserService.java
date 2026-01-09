package com.tushar.resumeai.service;


import com.tushar.resumeai.helper.DocxHelper;
import com.tushar.resumeai.helper.PdfHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParserService {

    @Autowired
    private PdfHelper pdfHelper;

    @Autowired
    private DocxHelper docxHelper;

    public String extractText(File file) {

        String filename = file.getName().toLowerCase();

        if(filename.endsWith(".pdf")){
            return pdfHelper.extractText(file);
        } else if (filename.endsWith(".docx")) {
            return docxHelper.extractText(file);
        }else {
            throw new RuntimeException("Unsupported file formate. ");
        }
    }

    public String cleanText (String text){
        text = text.replaceAll("\\s+"," ");
        text = text.replaceAll("[^a-zA-Z0-9,.@\\-+]"," ");
        text = text.trim();

        return text;
    }

    public String generateSummary(String text){
        if(text.length()<50) return "Summary not available";

        String [] lines = text.split("\\.");
        StringBuilder summary = new StringBuilder();

        int limit = Math.min(lines.length, 3);

        for(int i=0; i<limit; i++){
            summary.append(lines[i].trim()).append(". ");

        }
        return summary.toString();
    }

    public List<String> extractSkills (String text){
        List<String> techSkills = Arrays.asList(
                "Java", "Python", "C++", "HTML", "CSS", "JavaScript", "Spring",
                "Spring Boot", "MySQL", "MongoDB", "Git", "GitHub", "Docker",
                "React", "Node", "AWS", "Kotlin", "DSA", "Machine Learning", "AI"
        );
        List<String> found = new ArrayList<>();

        for(String skill : techSkills){
            Pattern pattern = Pattern.compile("\\b" +skill+ "\\b",Pattern.CASE_INSENSITIVE );
            Matcher matcher = pattern.matcher(text);

            if(matcher.find()){
                found.add(skill);

            }
        }
        return found;
    }




}
