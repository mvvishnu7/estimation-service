package com.Sellics.codingchallenge.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder
public class SearchVolumeEstimationDTO implements Serializable {
    private static final long serialVersionUID = 8294901779689245204L;

    @NonNull
    String keyword;

    @NonNull
    Double score;
}
