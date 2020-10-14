package com.Sellics.codingchallenge.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScoreUtil {
    public static final BigDecimal MAX_COUNT_PER_LETTER = BigDecimal.valueOf(10);
    public static final int SCALE = 2;
    public static final BigDecimal PERCENTAGE_CONVERSION_FACTOR = BigDecimal.valueOf(100);

    public static double calculateScore(long totalLength, long count) {
        BigDecimal maxCountPossible = BigDecimal.valueOf(totalLength).multiply(MAX_COUNT_PER_LETTER);
        return BigDecimal.valueOf(count)
                .divide(maxCountPossible, SCALE, RoundingMode.HALF_UP)
                .multiply(PERCENTAGE_CONVERSION_FACTOR).doubleValue();
    }
}
