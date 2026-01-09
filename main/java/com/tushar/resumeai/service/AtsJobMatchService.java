package com.tushar.resumeai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AtsJobMatchService {

    private final ChatClient chatClient;

    public AtsJobMatchService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String evaluate(String resumeText, String jobDescription) {

        String prompt = """
        You are an ATS system.

        Evaluate the resume against the job description.
        Provide:
        1. ATS Score out of 100
        2. Job Match Percentage
        3. Strengths
        4. Missing Skills

        Resume:
        %s

        Job Description:
        %s
        """.formatted(resumeText, jobDescription);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
