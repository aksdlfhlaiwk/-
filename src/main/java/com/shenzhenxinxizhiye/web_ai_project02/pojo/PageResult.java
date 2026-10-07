package com.shenzhenxinxizhiye.web_ai_project02.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class PageResult<T> {

    private long total;
    private List<T> rows;

}
