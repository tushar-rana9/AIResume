package com.tushar.resumeai.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private String aiSummary;
    private String atsReport;
    private int jobMatchPercentage;
    private String biasReport;
    private List<String> extractedSkills;

    // getters & setters
    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }

    public String getAtsReport() {
        return atsReport;
    }

    public void setAtsReport(String atsReport) {
        this.atsReport = atsReport;
    }

    public int getJobMatchPercentage() {
        return jobMatchPercentage;
    }

    public void setJobMatchPercentage(int jobMatchPercentage) {
        this.jobMatchPercentage = jobMatchPercentage;
    }

    public String getBiasReport() {
        return biasReport;
    }

    public void setBiasReport(String biasReport) {
        this.biasReport = biasReport;
    }

    public List<String> getExtractedSkills() {
        return extractedSkills;
    }

    public void setExtractedSkills(List<String> extractedSkills) {
        this.extractedSkills = extractedSkills;
    }
}
