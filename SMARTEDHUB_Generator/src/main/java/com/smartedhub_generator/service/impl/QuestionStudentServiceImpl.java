package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.QuestionStudent;
import com.smartedhub_generator.mapper.QuestionStudentMapper;
import com.smartedhub_generator.service.IQuestionStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * relationship between question and student (history of student do question) 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class QuestionStudentServiceImpl extends ServiceImpl<QuestionStudentMapper, QuestionStudent> implements IQuestionStudentService {

}
