package com.maps.search.service.external;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KakaoApiService implements ExternalApiService {

    private final static String BASE_URL = "https://dapi.kakao.com";
    private WebClient webClient;

    public KakaoApiService() {
        initWebClient();
    }

    public Flux<String> getPlaces(@RequestParam("keyword") String keyword,
                                  @RequestParam("page") int page,
                                  @RequestParam("limit") int pageSize) {
        Flux<String> result = webClient.get()
                .uri("/v2/local/search/keyword.json?x=0&y=0&page="+page+"&size="+pageSize+"&query="+keyword)
                .retrieve()
                .bodyToFlux(String.class)
                .onErrorResume(e ->
                            Mono.just("Exception : " + e.getMessage()
                        ));
        return result;
    }

    @Override
    public void initWebClient() {
        this.webClient = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"KakaoAK 3c5be0db1104a08f5e88083590ffad5b")
                .build();
    }
}
