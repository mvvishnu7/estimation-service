package com.Sellics.codingchallenge.service;

import com.Sellics.codingchallenge.dto.SearchVolumeEstimationDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.Sellics.codingchallenge.util.ScoreUtil.calculateScore;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstimationService {
    private final AutoCompletionService autoCompletionService;

    /**
     * Calculates the Search Volume Estimate/Score for the keyword
     * The algorithm runs in O(log n) time complexity and constant space
     *
     * @param keyword
     * @return searchVolumeEstimationDTO
     */
    public SearchVolumeEstimationDTO estimate(@NonNull String keyword) {
        log.debug("Estimating keyword {}", keyword);
        long keywordLength = keyword.length();
        int lastEndPos = keyword.length();
        int currentEnd = lastEndPos / 2;

        long count = 0;

        // with single word search
        count = count + availabilityCount(keyword.substring(0, 1), keyword);

        if (keywordLength == 1) {
            return toSearchVolumeEstimationDTO(keyword, calculateScore(keywordLength, count));
        }

        // with full word search
        count = count + availabilityCount(keyword, keyword);

        // search with parts of the keyword where we follow binary search method to identify parts to search
        while (currentEnd > 0 && currentEnd <= keywordLength) {
            long currentCount = availabilityCount(keyword.substring(0, currentEnd), keyword);
            if (currentCount > 0) {
                count = count + currentCount;
                lastEndPos = currentEnd;
                currentEnd = currentEnd / 2;
            } else {
                int lengthLeft = lastEndPos - currentEnd;
                if (lengthLeft <= 1) {
                    return toSearchVolumeEstimationDTO(keyword, calculateScore(keywordLength, count));
                }
                currentEnd = currentEnd + lengthLeft / 2;
            }
        }
        return toSearchVolumeEstimationDTO(keyword, calculateScore(keywordLength, count));
    }

    private long availabilityCount(String query, String key) {
        long count = autoCompletionService.getOptions(query).stream()
                .filter(option -> option.contains(key))
                .count();
        log.debug("Found {} matches for key {}", count, key);
        return count;
    }

    private SearchVolumeEstimationDTO toSearchVolumeEstimationDTO(String keyword, Double score) {
        log.info("Score for keyword {} is {}", keyword, score);
        return SearchVolumeEstimationDTO.builder()
                .keyword(keyword)
                .score(score)
                .build();
    }
}
