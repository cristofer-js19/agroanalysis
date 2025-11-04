package com.tech.agroanalysis.infrastructure.gateway.ai.config;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerativeAiClientConfig {

    @Value("${ai.llm.api-key}")
    private String apiKey;

    @Value("${ai.llm.url}")
    private String url;

    @Bean
    public OpenAIClient generativeAiClient() {
        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .baseUrl(url)
                .build();
    }
}
