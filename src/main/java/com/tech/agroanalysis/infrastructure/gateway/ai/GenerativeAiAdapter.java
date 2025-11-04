package com.tech.agroanalysis.infrastructure.gateway.ai;

import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.tech.agroanalysis.application.port.out.GenerativeAiDataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerativeAiAdapter implements GenerativeAiDataPort {

    private final OpenAIClient aiClient;

    @Override
    public String generateAnalysis() {
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Qual é o nome da capital do estado do Pará no Brasil?")
                .model("openai/gpt-oss-20b")
                .build();

        Response response = aiClient.responses().create(params);
        return response.output().getLast().message().get().content().getFirst().asOutputText().text();
    }
}
