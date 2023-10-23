package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IClassroomService extends IService<Classroom> {

    GeneralReturn CreateClass(Classroom newclassroom, String username);

    GeneralReturn GetAllOrSpecificClassroom(int pageNo, int pageSize, String classname, String username);

    GeneralReturn DeleteClassroom(Integer classroomId);

    //GeneralReturn GetAllOrSpecificClassroom(int pageNo, int pageSize, String classname);
}
