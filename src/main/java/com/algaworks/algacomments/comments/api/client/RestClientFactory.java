package com.algaworks.algacomments.comments.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClient moderationRestClient(){
        return  builder.baseUrl("http://localhost:8082")
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw new ModerationClientBadGatewayException();
                })
                .build();
    }
}
