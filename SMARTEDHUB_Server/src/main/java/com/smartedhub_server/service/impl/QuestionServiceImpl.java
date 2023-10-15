package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.QuestionMapper;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.service.IQuestionService;
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
