package com.Sellics.codingchallenge.client;

import com.Sellics.codingchallenge.dto.SearchVolumeEstimationDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SearchVolumeEstimationApi {
    @GetMapping(value = "estimate", produces = MediaType.APPLICATION_JSON_VALUE)
    SearchVolumeEstimationDTO estimate(@RequestParam String keyword);
}
