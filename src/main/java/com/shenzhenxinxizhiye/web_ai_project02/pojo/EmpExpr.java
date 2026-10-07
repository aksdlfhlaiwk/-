package com.shenzhenxinxizhiye.web_ai_project02.pojo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpExpr {
    private Integer id; // 主键ID
    private Integer empId; // 员工id
    private LocalDate begin; // 开始时间
    private LocalDate end; // 结束时间
    private String company; // 公司名称
    private String job; // 职位
}