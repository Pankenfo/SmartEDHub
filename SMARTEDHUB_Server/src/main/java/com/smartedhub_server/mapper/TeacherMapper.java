package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartedhub_server.pojo.Teacher;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * For admin get all teachers
     * @return
     */
    List<Teacher> getAllTeacher();

    /**
     * For admin enable teacher
     * @param username
     */
    int enableTeacher(String username);
}
