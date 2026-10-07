package com.shenzhenxinxizhiye.web_ai_project02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    void saveExper(List<EmpExpr> empexpr);

    void delete(List<Integer> ids);

    @Select ("SELECT * FROM emp_expr WHERE emp_id = #{id}")
    List<EmpExpr> getById(Integer id);


}
