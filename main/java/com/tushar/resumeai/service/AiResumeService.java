package com.tushar.resumeai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiResumeService {

    private final ChatClient chatClient;

    public AiResumeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generateResumeSummary(String resumeText) {

        String prompt = """
        You are an ATS resume expert.
        Create a professional 3-4 line summary for the following resume.
        Focus on skills, experience and strengths.

        Resume Text:
        %s
        """.formatted(resumeText);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
