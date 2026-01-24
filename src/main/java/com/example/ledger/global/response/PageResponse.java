package com.example.ledger.global.response;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class PageResponse<T> {
    private final List<T> item;
    private final int page;
    private final int size;
    private final Long totalElements;
    private final int totalPages;
    private final boolean hasNext;

    public static <T>PageResponse<T> from (Page<T> pageResult){
        return new PageResponse<>(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.hasNext()
        );
    }

    public PageResponse(
            List<T> item,
            int page,
            int size,
            Long totalElements,
            int totalPages,
            boolean hasNext
    ) {
        this.item = item;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
    }

    public List<T> getItem() {
        return item;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
