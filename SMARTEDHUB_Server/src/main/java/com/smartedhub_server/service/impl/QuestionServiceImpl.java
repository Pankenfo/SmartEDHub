package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.QuestionMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public GeneralReturn createQuestion(Question question) {
        question.setValidity(1);
        question.setLikes(0);
        question.setQuestionDate(new Date());
        questionMapper.insert(question);
        return GeneralReturn.success("create a question successfully");
    }

    @Override
    public GeneralReturn GetQuestionById(Integer questionId) {
        Question question = questionMapper.selectById(questionId);
        return GeneralReturn.success(question);
    }

    @Override
    public GeneralReturn GetAllOrSpecificQuestion(int pageNo, int pageSize, String questionTitle) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(questionTitle), Question::getQuestionTitle,questionTitle); //条件查询
        Page<Question> page = new Page<>(pageNo, pageSize);
        questionMapper.selectPage(page,wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());//查询的总数
        data.put("rows",page.getRecords());//查询到的数据集
        return GeneralReturn.success(data);
    }

    @Override
    public GeneralReturn GetAllQuestionByClassId(int pageNo, int pageSize, int classId, String questionTitle) {
        Page<Question> page = new Page<>(pageNo, pageSize);
        IPage<Question> iPage = questionMapper.GetAllQuestionByClassId(page, classId,questionTitle);
        Map<String, Object> data = new HashMap<>();
        data.put("total", iPage.getTotal());
        data.put("data",iPage.getRecords());
        return GeneralReturn.success(data);
    }

    @Override
    public int LikeQuestion(Integer questionId) {
        questionMapper.AddLike(questionId);
        return (questionMapper.selectById(questionId).getLikes());
    }

    @Override
    public int CancelLikeQuestion(Integer questionId) {
        if(questionMapper.selectById(questionId).getLikes() == 0){
            return 0;
        }
        else {
            questionMapper.CancelLike(questionId);
            return (questionMapper.selectById(questionId).getLikes());
        }
    }

    @Override
    public GeneralReturn GetAllQuestionByClassIdNoPage(int classId, String questionTitle) {
        questionMapper.GetAllQuestionByClassIdNopage(classId,questionTitle);
        return GeneralReturn.success(questionMapper.GetAllQuestionByClassIdNopage(classId,questionTitle));
    }

    @Override
    public GeneralReturn GetAllOrSpecificQuestionNoPage(String questionTitle) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(questionTitle), Question::getQuestionTitle,questionTitle); //条件查询
        Map<String, Object> data = new HashMap<>();
        data.put("total", questionMapper.selectCount(wrapper));//查询的总数
        data.put("rows",questionMapper.selectList(wrapper));//查询到的数据集
        return GeneralReturn.success(data);
    }

    @Override
    public GeneralReturn StudentGetAllQuestion(Integer studentId) {
        return GeneralReturn.success(questionMapper.StudentGetAllQuestion(studentId));
    }

}
