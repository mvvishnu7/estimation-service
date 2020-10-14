package com.Sellics.codingchallenge.service;

import com.Sellics.codingchallenge.dto.SearchVolumeEstimationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstimationServiceTest {

    @Mock
    AutoCompletionService autoCompletionService;

    @InjectMocks
    private EstimationService estimationService;

    private String keyword = "bat";

    @Test
    public void shouldCalculateEstimate() {
        when(autoCompletionService.getOptions(argThat(arg -> arg.length() < 3)))
                .thenReturn(getBestResult(keyword));
        when(autoCompletionService.getOptions(keyword))
                .thenReturn(Collections.emptyList());
        assertThat(estimationService.estimate(keyword))
                .extracting(SearchVolumeEstimationDTO::getScore)
                .isEqualTo(67.0);
    }

    @Test
    public void shouldReturnMax() {
        when(autoCompletionService.getOptions(any()))
                .thenReturn(getBestResult(keyword));
        assertThat(estimationService.estimate(keyword))
                .extracting(SearchVolumeEstimationDTO::getScore)
                .isEqualTo(100.0);
    }

    @Test
    public void shouldReturnMin() {
        when(autoCompletionService.getOptions(any()))
                .thenReturn(Collections.emptyList());
        assertThat(estimationService.estimate(keyword))
                .extracting(SearchVolumeEstimationDTO::getScore)
                .isEqualTo(0.0);
    }

    private List<String> getBestResult(String keyword) {
        return IntStream.range(0, 10)
                .mapToObj(i -> keyword)
                .collect(Collectors.toList());
    }
}
