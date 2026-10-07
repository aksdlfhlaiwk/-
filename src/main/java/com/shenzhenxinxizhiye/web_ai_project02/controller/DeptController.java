package com.shenzhenxinxizhiye.web_ai_project02.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Dept;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.Result;
import com.shenzhenxinxizhiye.web_ai_project02.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

// 可以将方法中的统一路径抽取到类上方
@RequestMapping("/depts")
public class DeptController {

    /*
     * private static final Logger log =
     * LoggerFactory.getLogger(DeptController.class);
     * lombok提供了一个@Slf4j , 会提供log
     */
    private final DeptService deptsService;

    DeptController(DeptService deptsService) {
        this.deptsService = deptsService;
    }

    // @RequestMapping(value = "/depts", method = RequestMethod.GET) 通常不会酱紫指定访问方式
    // 查询所有的部门
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptsService.findALL();

        return Result.success(deptList);

    }

    // 根据id删除指定部门
    @DeleteMapping
    // 方式一是使用HttpServletRequest
    // 方式二通过spring提供的@RequestParam注解将请求参数绑定给方法形参 required = false 可以不传递指定参数
    // 如果前端请求参数名与服务端方法形参名一致, 可以定义方法形参接受即可(即省略@RequestParam注解)
    public Result deleteDept(@RequestParam(value = "id") Integer id) {
        // 日志可以使用{}作为占位符
        log.info("删除指定部门的数据的id是{}", id);
        deptsService.deleteDept(id);
        return Result.success();
    }

    // 添加部门
    // 接受json格式的请求参数
    @PostMapping
    // json格式的参数, 通常使用一个实体对象接受
    // json数据的键名需要与对象中的属性名相同, 并需要@RequestBody标明
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门" + dept.getName());
        deptsService.add(dept);
        return Result.success();
    }

    /*
     * 根据id查找对应数据
     * 使用路径参数请求
     * 路径参数: 通过URL直接传递的参数, 使用{}来表示
     * 需要使用@PathVariable获取
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {

        log.info("查询的id是" + id);
        Dept dept = deptsService.getById(id);
        return Result.success(dept);

    }

    // 修改对应部门数据
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改数据" + dept);
        deptsService.update(dept);
        return Result.success();
    }

}