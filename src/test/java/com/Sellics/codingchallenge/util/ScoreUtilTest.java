package com.Sellics.codingchallenge.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.Sellics.codingchallenge.util.ScoreUtil.calculateScore;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreUtilTest {

    @ParameterizedTest
    @MethodSource("provideTestArguments")
    public void shouldCalculateScore(long totalLength, long count, double score) {
        assertEquals(score, calculateScore(totalLength, count));
    }

    private static Stream<Arguments> provideTestArguments() {

        return Stream.of(
                Arguments.of(10, 100, 100.0),
                Arguments.of(10, 0, 0),
                Arguments.of(10, 10, 10.0),
                Arguments.of(9, 70, 78.0)
        );
    }
}
