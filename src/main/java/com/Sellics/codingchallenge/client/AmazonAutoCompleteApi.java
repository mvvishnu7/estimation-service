package com.Sellics.codingchallenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "amazon-auto-complete", url = "https://completion.amazon.com")
public interface AmazonAutoCompleteApi {

    @GetMapping("/search/complete")
    String getAutoCompleteSuggestions(@RequestParam("search-alias") String searchAlias,
                                            @RequestParam("client") String client,
                                            @RequestParam("mkt") Integer mkt,
                                            @RequestParam("q") String query);
}

