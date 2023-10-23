package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.Question;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface ClassroomMapper extends BaseMapper<Classroom> {

    List<Classroom> StudentGetClassroom(String classname,Integer studentId);

    List<Classroom> TeacherGetClassroom(String classname,String username);
}
