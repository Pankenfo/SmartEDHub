package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface ITeacherService extends IService<Teacher> {

    Teacher getTeacherByUserName(String teacherName);

    /**
     * For teacher register
     * @param teacher
     * @return
     */
    GeneralReturn teacherRegister(Teacher teacher);
}
