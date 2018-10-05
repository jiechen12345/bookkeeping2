package com.oppo.dto.paging;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JieChen on 2018/8/7.
 */
public abstract class PageDto<T> {

    private int currentPage = 1;
    private int totalPages = 1;
    private Long count = 1L;
    private List<T> contents = new LinkedList<>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}