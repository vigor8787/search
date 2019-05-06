package com.maps.search.external;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class KakaoApiServiceTest {

    private WebTestClient webclient;

    @Before
    public void setUp() {
        webclient = WebTestClient
                .bindToServer()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION,"KakaoAK 3c5be0db1104a08f5e88083590ffad5b")
                .build();
    }

    @Test
    public void getPlacesInKakaoApiTest() {
        webclient.get()
                .uri("/v2/local/search/keyword.json?x=0&y=0&page=1&size=10&query=kakao")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.documents.length()")
                .isEqualTo(10);
    }
}
