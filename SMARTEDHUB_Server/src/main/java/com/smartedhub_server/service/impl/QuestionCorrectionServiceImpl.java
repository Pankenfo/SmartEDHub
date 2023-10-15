package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.QuestionCorrectionMapper;
import com.smartedhub_server.pojo.QuestionCorrection;
import com.smartedhub_server.service.IQuestionCorrectionService;
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
