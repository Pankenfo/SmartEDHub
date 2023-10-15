package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.StudentClass;
import com.smartedhub_generator.mapper.StudentClassMapper;
import com.smartedhub_generator.service.IStudentClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
