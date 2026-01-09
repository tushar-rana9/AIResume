package com.tushar.resumeai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiBiasDetectionService {

    private final ChatClient chatClient;

    public AiBiasDetectionService(ChatClient.Builder builder) {

        this.chatClient = builder.build();
    }

    public String detectBias(String resumeText) {

        String prompt = """
        You are an AI bias detection system.

        Analyze the resume and detect any potential bias such as:
        - Gender bias
        - Age bias
        - Caste or region bias
        - Language bias

        Respond with:
        Bias Level (Low / Medium / High)

        Resume:
        %s
        """.formatted(resumeText);

        return chatClient.prompt(prompt).call().content();
    }
}
