package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.QuestionClass;

/**
 * <p>
 * relationship between question and class (send question to class) 服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IQuestionClassService extends IService<QuestionClass> {

    GeneralReturn AllocateQuestion(Integer questionId, Integer classId);

}
