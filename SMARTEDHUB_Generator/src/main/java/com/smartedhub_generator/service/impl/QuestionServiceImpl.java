package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.Question;
import com.smartedhub_generator.mapper.QuestionMapper;
import com.smartedhub_generator.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Storage the details of each question 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
