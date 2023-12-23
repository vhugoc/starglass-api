package com.starglass.api.args;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class Pagination {

    private int page;

    private int size;

    private Pagination() {
    }

    private Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static Pagination of(int page, int size) {
        return new Pagination(page, size);
    }

    public Pageable pageable() {
        return PageRequest.of(this.page, this.size);
    }

}
