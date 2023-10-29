package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartedhub_server.pojo.Student;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface StudentMapper extends BaseMapper<Student> {


    List<Student> getAllStudent();

    /**
     * For admin enable student
     * @param username
     * @return
     */
    int enableStudent(String username);

    /**
     * For admin get student by username
     * @param userName
     * @return
     */
    Student getStudentByUserNameIgnoreValidity(String userName);
}
