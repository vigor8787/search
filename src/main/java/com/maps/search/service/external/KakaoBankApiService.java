package com.maps.search.service.external;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//TODO : 임시파일 - 추후 기타 외부 API 연계 서비스 추가

@Service
public class KakaoBankApiService implements ExternalApiService {
    private final String BASE_URL="https://dapi.kakaobank.com";
    private WebClient webClient;

    @Override
    public void initWebClient() {
        this.webClient = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"KakaoAK 3c5be0db1104a08f5e88083590ffad5b")
                .build();
    }

    public Mono<String> getAccounts() {
        return Mono.just("accounts");
    }
}
