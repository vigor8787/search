package com.maps.search.controller.keyword;

import com.maps.search.service.external.KakaoApiService;
import com.maps.search.service.keyword.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KakaoApiService kakaoApiService;

    @GetMapping("/api/places")
    public Flux<String> getPlaces(@RequestParam("keyword") String keyword,
                                  @RequestParam("page") int page,
                                  @RequestParam("limit") int pageSize
    ) {
        return kakaoApiService.getPlaces(keyword, page, pageSize);
    }

    @GetMapping("/api/rank/keyword/count/{keyword}")
    public Mono<Double> countViews(@PathVariable("keyword") String keyword) {
        return keywordService.countViews(keyword);
    }

    @GetMapping("/api/rank/keywords")
    public Flux<ZSetOperations.TypedTuple<String>> getLank() {
        return keywordService.getLankKeywords();
    }

}
