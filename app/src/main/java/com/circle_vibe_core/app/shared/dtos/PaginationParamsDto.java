package com.circle_vibe_core.app.shared.dtos;

import org.springframework.data.domain.Sort;

public record PaginationParamsDto(
    Integer page,
    Integer size,
    String sortBy,
    Sort.Direction direction) {}
