package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.Student;
import com.smartedhub_generator.mapper.StudentMapper;
import com.smartedhub_generator.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
