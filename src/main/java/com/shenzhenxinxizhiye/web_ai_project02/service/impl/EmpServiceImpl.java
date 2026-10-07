package com.shenzhenxinxizhiye.web_ai_project02.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenzhenxinxizhiye.web_ai_project02.mapper.EmpExprMapper;
import com.shenzhenxinxizhiye.web_ai_project02.mapper.EmpMapper;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.Emp;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpExpr;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpQueryParam;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.PageResult;
import com.shenzhenxinxizhiye.web_ai_project02.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;

    public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprMapper) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        /*
         * // 查询总记录数
         * Long total = empMapper.count();
         * // 查询结果列表
         * Integer start = (page - 1) * pageSize;
         * List<Emp> list = empMapper.list(start, pageSize);
         * // 封装数据
         * PageResult<Emp> pageResult = new PageResult<>();
         * pageResult.setRows(list);
         * pageResult.setTotal(total);
         * return pageResult;
         * 
         * 
         */

        // 使用PageHelper分页查询

        // 1. 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2.执行查询
        // 只有紧跟着 PageHelper.startPage() 之后的第一条 MyBatis 查询（Mapper 方法）会被分页。
        // 中间如果插入了其他查询，分页参数会被消耗掉。
        List<Emp> list = empMapper.list(empQueryParam);

        // 3.封装对应数据
        // 分页查询相关数据会被封装到Page这个对象中;
        // Page<Emp> p = (Page<Emp>) list; 可以强转或使用PageInfo
        PageInfo<Emp> p = new PageInfo<>(list);
        return new PageResult<Emp>(p.getTotal(), p.getList());

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void saveEmpInfo(Emp emp) {
        // 1.添加员工信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.saveEmpInfo(emp);
        // 2.添加员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();

        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合, 为emp_id赋值
            for (EmpExpr e : exprList) {
                e.setEmpId(emp.getId());
            }

            empExprMapper.saveExper(exprList);
        }
    }

    @Transactional(rollbackFor = Exception.class) // 开启事务控制
    @Override
    public void delete(List<Integer> ids) {
        // 调用Mapper层的删除语句
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        List<EmpExpr> empExprs = empExprMapper.getById(id);
        if (empExprs != null) {
            emp.setExprList(empExprs);
        }

        return emp;
    }

}