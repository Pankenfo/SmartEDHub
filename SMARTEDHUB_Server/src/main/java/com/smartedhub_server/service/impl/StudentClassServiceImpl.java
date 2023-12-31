package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.ClassroomMapper;
import com.smartedhub_server.mapper.StudentClassMapper;
import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.StudentClass;
import com.smartedhub_server.service.IStudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * relationship between student and class (student belongs to class) 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass> implements IStudentClassService {

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private ClassroomMapper classroomMapper;
    @Override
    public GeneralReturn AddToClassroom(Integer studentId, Integer classId) {
        StudentClass studentClass = new StudentClass();
        studentClass.setStudentId(studentId);
        studentClass.setClassId(classId);
        studentClass.setValidity(1);
        studentClassMapper.insert(studentClass);
        return GeneralReturn.success("Add successfully");
    }

    @Override
    public GeneralReturn ShowClassDetail(Integer classId) {
        return GeneralReturn.success(studentClassMapper.ShowClassStudent(classId));
    }

    
    @Override
    public GeneralReturn ShowTeacherClassList(String teaUsername,String classname) {
        return GeneralReturn.success(classroomMapper.TeacherGetClassroom(classname,teaUsername));
    }

    @Override
    public Long CountStudent(Integer classId) {
        LambdaQueryWrapper<StudentClass> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentClass::getClassId,classId);
        return studentClassMapper.selectCount(wrapper);
    }

    @Override
    public GeneralReturn DeleteStudent(Integer studentId,Integer classId) {
        LambdaQueryWrapper<StudentClass> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentClass::getClassId,classId).and(i->i.eq(StudentClass::getStudentId,studentId));
        studentClassMapper.delete(wrapper);
        return GeneralReturn.success("Delete successfully");
    }
}
