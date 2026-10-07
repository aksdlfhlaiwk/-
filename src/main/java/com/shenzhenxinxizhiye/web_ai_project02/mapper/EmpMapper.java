package com.shenzhenxinxizhiye.web_ai_project02.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Emp;
import com.shenzhenxinxizhiye.web_ai_project02.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {
    // // 获取符合的员工总数
    // @Select("SELECT COUNT(*) AS 总记录数 FROM emp e LEFT JOIN dept on e.dept_id =
    // dept.id")
    // public Long count();

    // // 分页查询
    // // 需要传递起始索引和记录数
    // @Select("SELECT e.* , d.name deptName From emp e LEFT JOIN dept d on
    // e.dept_id = d.id order by e.update_time desc LIMIT #{start} , #{pageSize} ")
    // public List<Emp> list(Integer start, Integer pageSize);

    // @Select("SELECT e.* , d.name deptName From emp e LEFT JOIN dept d on
    // e.dept_id = d.id order by e.update_time desc")
    // public List<Emp> list( String name, Integer gender, LocalDate begin,
    // LocalDate end);

    // 使用PageHelper完成, sql写在xml文件中了
    public List<Emp> list(EmpQueryParam empQueryParam);

    // 保存员工的信息
     // 在执行完insert语句后, emp.id的值仍旧是null, 通过这个注解会让MyBatis 会自动执行 emp.setId()
    @Options (useGeneratedKeys = true, keyProperty = "id") // 获取到生成的主键
    @Insert("""
            INSERT INTO emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)
            VALUES(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})
            """)
    public void saveEmpInfo(Emp emp);
    
     // 删除员工的信息 
    public void delete(List<Integer> ids);
    
    

    // 通过id查询对应员工的信息
    @Select ("SELECT * FROM emp WHERE id = #{id}")
    public Emp getById(Integer id);


   







}