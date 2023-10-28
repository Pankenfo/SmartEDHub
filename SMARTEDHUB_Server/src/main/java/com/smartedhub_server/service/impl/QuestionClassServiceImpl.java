package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.QuestionClassMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.QuestionClass;
import com.smartedhub_server.pojo.QuestionCorrection;
import com.smartedhub_server.service.IQuestionClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private QuestionClassMapper questionClassMapper;
    @Override
    public GeneralReturn AllocateQuestion(Integer questionId, Integer classId) {
        QuestionClass questionClass = new QuestionClass();
        questionClass.setClassId(classId);
        questionClass.setQuestionId(questionId);
        questionClass.setValidity(1);
        questionClassMapper.insert(questionClass);
        return GeneralReturn.success("Allocate successfully");
    }
}
