package com.Sellics.codingchallenge.service;

import com.Sellics.codingchallenge.client.AmazonAutoCompleteApi;
import com.Sellics.codingchallenge.configuration.AmazonAutoCompletionConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutoCompletionServiceTest {
    @Mock
    private AmazonAutoCompletionConfig amazonAutoCompletionConfig;
    @Mock
    private AmazonAutoCompleteApi amazonAutoCompleteApi;

    @InjectMocks
    AutoCompletionService autoCompletionService;

    private static final String OPTIONS_RESPONSE = "[\"bat\",[\"batteries\",\"bathroom rugs\",\"bath bombs\"," +
            "\"batteries aa size\",\"bath towels\",\"bath mat\",\"bath toys\",\"bathroom decor\"," +
            "\"bathroom scale\",\"bathroom trash can\"],[{},{},{},{},{},{},{},{},{},{}],[]," +
            "\"TESTING1234\"]";

    @Test
    public void shouldFetchAndExtractOptions() {
        when(amazonAutoCompleteApi.getAutoCompleteSuggestions(any(), any(), any(), any()))
                .thenReturn(OPTIONS_RESPONSE);

        assertThat(autoCompletionService.getOptions("bat"))
                .containsExactlyInAnyOrder("batteries",
                        "bathroom rugs",
                        "bath bombs",
                        "batteries aa size",
                        "bath towels",
                        "bath mat",
                        "bath toys",
                        "bathroom decor",
                        "bathroom scale",
                        "bathroom trash can");
    }
}
