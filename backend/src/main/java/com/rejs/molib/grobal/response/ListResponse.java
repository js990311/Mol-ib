package com.rejs.molib.grobal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse <T>{
    private List<T> data;
    private int count;

    public static <T> ListResponse<T> of(List<T> data){
        return new ListResponse<>(data, data.size());
    }
}
