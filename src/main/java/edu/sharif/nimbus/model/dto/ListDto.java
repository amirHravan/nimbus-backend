package edu.sharif.nimbus.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListDto<T> {
    private final List<T> list;
    private final int count;

    public ListDto(List<T> list) {
        this.list = list;
        this.count = this.list.size();
    }

    public ListDto(List<T> list, int page, int limit) {
        int end = Math.min(page * limit, list.size());
        int start = Math.min((page - 1) * limit, end);
        this.list = list.subList(start, end);
        this.count = list.size();
    }

}
