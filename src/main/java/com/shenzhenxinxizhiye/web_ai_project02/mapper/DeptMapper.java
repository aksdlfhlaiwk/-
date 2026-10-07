package com.shenzhenxinxizhiye.web_ai_project02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Dept;

@Mapper
public interface DeptMapper {

    // 查询所有的部门
    @Select("SELECT id, name, create_time, update_time  FROM dept ORDER BY update_time DESC")
    List<Dept> findALL();

    // 删除指定id的部门, 无需返回值
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteDept(Integer id);

    @Insert("INSERT INTO dept (name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    @Select ("SELECT id, name, create_time, update_time  FROM dept WHERE id = #{id}")
    Dept getById(Integer id);

    @Update ("UPDATE dept SET name = #{name}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Dept dept);

}
