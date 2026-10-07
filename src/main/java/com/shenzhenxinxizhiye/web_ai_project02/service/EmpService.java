package com.shenzhenxinxizhiye.web_ai_project02.service;

import java.util.List;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Emp;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpQueryParam;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.PageResult;

public interface EmpService {

    // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer
    // gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void saveEmpInfo(Emp emp);

    void delete(List<Integer> ids);

    Emp getById(Integer id);

}