package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.StudentClass;

import java.util.List;

/**
 * <p>
 * relationship between student and class (student belongs to class) Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface StudentClassMapper extends BaseMapper<StudentClass> {
    List<Student> ShowClassStudent(Integer classId);
}
