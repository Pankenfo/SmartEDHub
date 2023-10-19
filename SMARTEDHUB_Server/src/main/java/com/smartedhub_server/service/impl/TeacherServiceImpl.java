package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.TeacherMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import sun.security.krb5.internal.PAData;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {


    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Return teacher by username
     * @param teacherName
     * @return
     */
    @Override
    public Teacher getTeacherByUserName(String teacherName) {
        return teacherMapper.selectOne(new QueryWrapper<Teacher>().eq("username", teacherName).eq("validity", true));
    }

    /**
     * For teacher register
     * @param teacher
     * @return
     */
    @Override
    public GeneralReturn teacherRegister(Teacher teacher) {

        Student studentByUserName = studentService.getStudentByUserName(teacher.getUsername());
        Teacher teacherByUserName = getTeacherByUserName(teacher.getUsername());

        if (studentByUserName == null && teacher.getUsername() != null && teacherByUserName == null) {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacher.setValidity(true);

            int result = teacherMapper.insert(teacher);
            if (result == 1) {
                return GeneralReturn.success("Register successfully");
            } else {
                return GeneralReturn.error("Register failed");
            }
        } else {
            return GeneralReturn.error("Username already exists, please try another one");
        }
    }
}
