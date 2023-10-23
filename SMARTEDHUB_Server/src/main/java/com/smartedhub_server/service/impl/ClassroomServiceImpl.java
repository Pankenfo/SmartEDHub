package com.smartedhub_server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.ClassroomMapper;
import com.smartedhub_server.mapper.TeacherMapper;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements IClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 10-22 jimmy 改了一创建班级的代码
     * @param newclassroom
     * @param username
     * @return
     */
    @Override
    public GeneralReturn CreateClass(Classroom newclassroom, String username) {
        String classname = newclassroom.getClassname();
        QueryWrapper query = new QueryWrapper<>();
        query.eq("classname",classname);
        if(classroomMapper.selectCount(query) > 0){
            return GeneralReturn.error("The classname has existed, please choose another one");
        }
        else {
            newclassroom.setUsername(username);
            newclassroom.setValidity(1);
            classroomMapper.insert(newclassroom);
            return GeneralReturn.success("successfully");
        }

    }

    @Override
    public GeneralReturn GetAllOrSpecificClassroom(int pageNo, int pageSize, String classname, String username) {
        LambdaQueryWrapper<Classroom> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(classname), Classroom::getClassname,classname); //条件查询
        wrapper.like(StringUtils.hasLength(username), Classroom::getUsername,username); //条件查询
        Page<Classroom> page = new Page<>(pageNo, pageSize);
        classroomMapper.selectPage(page,wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());//查询的总数
        data.put("rows",page.getRecords());//查询到的数据集
        return GeneralReturn.success(data);
    }

    @Override
    public GeneralReturn DeleteClassroom(Integer classroomId) {
        int result = classroomMapper.deleteById(classroomId);
        if (result == 1) {
            return GeneralReturn.success("Delete successfully");
        }
        return GeneralReturn.error("Delete failed, please try again");
    }

//    @Override
//    public GeneralReturn GetAllOrSpecificClassroom(int pageNo, int pageSize, String classname) {
//        LambdaQueryWrapper<Classroom> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.hasLength(classname), Classroom::getClassname,classname); //条件查询
//        Page<Classroom> page = new Page<>(pageNo, pageSize);
//        classroomMapper.selectPage(page,wrapper);
//        Map<String, Object> data = new HashMap<>();
//        data.put("total", page.getTotal());//查询的总数
//        data.put("rows",page.getRecords());//查询到的数据集
//        return GeneralReturn.success(data);
//    }
}
