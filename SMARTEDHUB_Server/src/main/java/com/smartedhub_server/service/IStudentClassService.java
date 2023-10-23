package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.StudentClass;
import io.swagger.models.auth.In;

/**
 * <p>
 * relationship between student and class (student belongs to class) 服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IStudentClassService extends IService<StudentClass> {

    GeneralReturn AddToClassroom(Integer studentId, Integer classId);

    GeneralReturn ShowClassDetail(Integer classId);

    GeneralReturn ShowTeacherClassList(String teaUsername,String classname);

    Long CountStudent(Integer classId);
}
