package com.tushar.resumeai.controller;

import com.tushar.resumeai.dto.ResumeAnalysisResponse;
import com.tushar.resumeai.exception.ResumeUploadException;
import com.tushar.resumeai.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private AiResumeService aiResumeService;

    @Autowired
    private AtsJobMatchService atsService;

    @Autowired
    private AiBiasDetectionService aiBiasDetectionService;

    @Autowired
    private JobMatchService jobMatchService;

    @Autowired
    private ParserService parserService;

    // ===================== UPLOAD RESUME API =====================
    @PostMapping(
            value = "/uploadResume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(
            summary = "Upload Resume for AI Analysis",
            description = "Upload PDF/DOCX resume and get AI summary, ATS score, job match and bias report"
    )
    public ResumeAnalysisResponse uploadResume(
            @RequestPart("file") MultipartFile file
    ) throws Exception {

        System.out.println(">>> uploadResume API HIT <<<");


        if (file.isEmpty()) {
            throw new ResumeUploadException("Uploaded file is empty");
        }

        // 1. Create upload directory
        String uploadDirPath = System.getProperty("user.dir") + "/upload/";
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 2. Save uploaded resume
        File savedFile = new File(uploadDirPath + file.getOriginalFilename());
        file.transferTo(savedFile);
        System.out.println("1. File saved");

        // 3. Parse & clean resume text
        String resumeText = parserService.extractText(savedFile);
        System.out.println("2. Text extracted");
        String cleanText = parserService.cleanText(resumeText);

        System.out.println("3. Text cleaned");

        // 4. Extract skills
        List<String> resumeSkills = parserService.extractSkills(cleanText);

        System.out.println("4. Skills extracted");

        // 5. AI Summary
        String aiSummary = aiResumeService.generateResumeSummary(cleanText);
        System.out.println("5. AI summary done");

        // 6. Job Description & Skills
        List<String> jobSkills = Arrays.asList(
                "Java", "Spring Boot", "MySQL", "Git", "REST API"
        );

        String jobDescription = """
                Java Developer with experience in Spring Boot,
                REST APIs, MySQL, Git and basic cloud knowledge.
                """;

        // 7. AI Evaluations
        String atsReport = atsService.evaluate(cleanText, jobDescription);
        System.out.println("6. ATS evaluation done");

        int jobMatch = jobMatchService.calculateJobMatch(resumeSkills, jobSkills);
        System.out.println("7. Job match done");
        String biasReport = aiBiasDetectionService.detectBias(cleanText);
        System.out.println("8. Bias detection done");

        // 8. Build Response
        ResumeAnalysisResponse response = new ResumeAnalysisResponse();
        response.setAiSummary(aiSummary);
        response.setAtsReport(atsReport);
        response.setJobMatchPercentage(jobMatch);
        response.setBiasReport(biasReport);
        response.setExtractedSkills(resumeSkills);

        return response;
    }
}
