package com.shenzhenxinxizhiye.web_ai_project02.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Emp;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpQueryParam;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.PageResult;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.Result;
import com.shenzhenxinxizhiye.web_ai_project02.service.EmpService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    // 分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    // 添加员工的信息, 前端以请求体方式请求
    @PostMapping
    public Result saveEmpInfo(@RequestBody Emp emp) {
        log.info("员工信息为{}", emp);
        empService.saveEmpInfo(emp);

        return Result.success();
    }

    /*
     * 删除员工操作
     * 可批量删除员工
     */

    @DeleteMapping
    // 这里传递的参数可以是数组或者集合, 其中集合必须添加@RequestParam注解
    public Result delete(@RequestParam List<Integer> ids) {

        log.info("delete empolyees' datas are {}", ids.toString());
        empService.delete(ids);
        return Result.success();
    }

    /*
     * 根据id查询对应员工的信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp data = empService.getById(id);

        return Result.success(data);
    }

}
