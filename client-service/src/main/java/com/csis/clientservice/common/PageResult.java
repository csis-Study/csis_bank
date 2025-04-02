package com.csis.clientservice.common;

import java.util.List;

public class PageResult<T> {
    private List<T> content;
    private long total;
    private int page;
    private int size;

    private PageResult(Builder<T> builder) {
        this.content = builder.content;
        this.total = builder.total;
        this.page = builder.page;
        this.size = builder.size;
    }

    public static class Builder<T> {
        private List<T> content;
        private long total;
        private int page;
        private int size;

        public Builder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder<T> total(long total) {
            this.total = total;
            return this;
        }

        public Builder<T> page(int page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(int size) {
            this.size = size;
            return this;
        }

        public PageResult<T> build() {
            return new PageResult<>(this);
        }
    }

    // Getter方法
    public List<T> getContent() { return content; }
    public long getTotal() { return total; }
    public int getPage() { return page; }
    public int getSize() { return size; }
}