package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;

/**
 * <p>
 * Storage the details of each question 服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IQuestionService extends IService<Question> {

    GeneralReturn createQuestion(Question question);

    GeneralReturn GetQuestionById(Integer questionId);

    GeneralReturn GetAllOrSpecificQuestion(int pageNo, int pageSize, String questionTitle, String questionDetail);

    GeneralReturn GetAllQuestionByClassId(int pageNo, int pageSize, int classId, String questionTitle, String questionDetail);
}
