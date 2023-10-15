package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.QuestionCorrection;
import com.smartedhub_generator.mapper.QuestionCorrectionMapper;
import com.smartedhub_generator.service.IQuestionCorrectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * relationship between question and correction notebook (save wrong question to correction notebook) 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class QuestionCorrectionServiceImpl extends ServiceImpl<QuestionCorrectionMapper, QuestionCorrection> implements IQuestionCorrectionService {

}
