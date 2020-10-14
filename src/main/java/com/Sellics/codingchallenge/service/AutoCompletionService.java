package com.Sellics.codingchallenge.service;

import com.Sellics.codingchallenge.client.AmazonAutoCompleteApi;
import com.Sellics.codingchallenge.configuration.AmazonAutoCompletionConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutoCompletionService {
    private final AmazonAutoCompletionConfig amazonAutoCompletionConfig;
    private final AmazonAutoCompleteApi amazonAutoCompleteApi;

    public List<String> getOptions(@NonNull String query) {
        log.debug("Searching key {}", query);
        String response = amazonAutoCompleteApi
                .getAutoCompleteSuggestions(amazonAutoCompletionConfig.getSearchAlias(),
                        amazonAutoCompletionConfig.getClient(),
                        amazonAutoCompletionConfig.getMkt(),
                        query);
        return extractOptions(response);
    }

    // TODO Use a custom deserializer for the autoCompletion API search
    private List<String> extractOptions(String response) {
        int start = response.indexOf("[", 1);
        int end = response.indexOf("]", 1);
        return Arrays.stream(response.substring(start + 1, end)
                .replace('"', ' ')
                .split(","))
                .filter(Objects::nonNull)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
