package edu.sharif.nimbus.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDto<T> {
    private final List<T> list;
    private final int count;

    public ListDto(List<T> list) {
        this.list = list;
        this.count = this.list.size();
    }

    public ListDto(ArrayList<T> arrayList) {
        this.list = arrayList;
        this.count = this.list.size();
    }
}
