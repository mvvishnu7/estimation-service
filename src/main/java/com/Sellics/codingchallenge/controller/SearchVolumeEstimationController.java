package com.Sellics.codingchallenge.controller;

import com.Sellics.codingchallenge.client.SearchVolumeEstimationApi;
import com.Sellics.codingchallenge.dto.SearchVolumeEstimationDTO;
import com.Sellics.codingchallenge.service.EstimationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("search-volume")
public class SearchVolumeEstimationController implements SearchVolumeEstimationApi {

    private final EstimationService estimationService;

    @GetMapping("estimate")
    public SearchVolumeEstimationDTO estimate(@RequestParam String keyword) {
        return estimationService.estimate(keyword);
    }
}
