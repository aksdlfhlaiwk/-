package com.shenzhenxinxizhiye.web_ai_project02.service;

import java.util.List;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Dept;

public interface DeptService {

    // 查询所有的部门数据
    List<Dept> findALL();

    // 删除指定部门的数据
    void deleteDept(Integer id);

    // 添加指定的部门
    void add(Dept dept);

    // 通过id查询部门
    Dept getById(Integer id);

    // 修改对应部门
    void update(Dept dept);

}
