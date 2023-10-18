package com.smartedhub_server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.ClassroomMapper;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public GeneralReturn CreateClass(Classroom newclass) {
        String classname = newclass.getClassname();
        QueryWrapper query = new QueryWrapper<>();
        query.eq("classname",classname);
        if(classroomMapper.selectCount(query) > 0){
            return GeneralReturn.error("The classname has existed, please choose another one");
        }
        else {
            newclass.setValidity(1);
            classroomMapper.insert(newclass);
            return GeneralReturn.success("successfully");
        }

    }
}
