package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.QuestionClass;
import com.smartedhub_generator.mapper.QuestionClassMapper;
import com.smartedhub_generator.service.IQuestionClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * relationship between question and class (send question to class) 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class QuestionClassServiceImpl extends ServiceImpl<QuestionClassMapper, QuestionClass> implements IQuestionClassService {

}
