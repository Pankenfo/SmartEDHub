package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.QuestionCorrectionMapper;
import com.smartedhub_server.mapper.QuestionMapper;
import com.smartedhub_server.mapper.QuestionStudentMapper;
import com.smartedhub_server.pojo.*;
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

    @Autowired
    private QuestionCorrectionMapper questionCorrectionMapper;

    @Autowired
    private QuestionStudentMapper questionStudentMapper;
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

    @Override
    public GeneralReturn DeleteQuestion(Integer questionId) {
        LambdaQueryWrapper<QuestionCorrection> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionCorrection::getQuestionId,questionId);
        questionCorrectionMapper.delete(wrapper);
        questionMapper.deleteById(questionId);
        return GeneralReturn.success("Delete successfully");
    }

    @Override
    public GeneralReturn StudentAnswer(String answer, Integer studentId, Integer questionId) {
        QuestionStudent questionStudent = new QuestionStudent();
        questionStudent.setQuestionId(questionId);
        questionStudent.setStudentId(studentId);
        questionStudent.setQuestionAnswer(answer);
        String a = questionMapper.QuestionGetClassName(studentId,questionId);
        questionStudent.setClassname(questionMapper.QuestionGetClassName(studentId,questionId));
        questionStudent.setTeacherUsername(questionMapper.QuestionGetTeacher(studentId,questionId));
        questionStudent.setValidity(1);
        questionStudentMapper.insert(questionStudent);
        return GeneralReturn.success("Submit successfylly");
    }

    @Override
    public GeneralReturn TeacherMark(Integer questionStudentId, Integer mark) {
        QuestionStudent questionStudent = new QuestionStudent();
        questionStudent.setQuestionStudentId(questionStudentId);
        questionStudent.setMark(mark);
        questionStudentMapper.updateById(questionStudent);
        return GeneralReturn.success("Mark successfully");
    }

    @Override
    public GeneralReturn StudentGetAnsweredQuestion(Integer studentId) {
        LambdaQueryWrapper<QuestionStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionStudent::getStudentId,studentId);
        return GeneralReturn.success(questionStudentMapper.selectList(wrapper));
    }

    @Override
    public GeneralReturn TeacherGetAnsweredList(String teacherUsername) {
        LambdaQueryWrapper<QuestionStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionStudent::getTeacherUsername,teacherUsername);
        return GeneralReturn.success(questionStudentMapper.selectList(wrapper));
    }

    @Override
    public Integer StudetGetMark(Integer studentId, Integer questionId) {
        LambdaQueryWrapper<QuestionStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionStudent::getStudentId,studentId).and(i->i.eq(QuestionStudent::getQuestionId,questionId));
        return questionStudentMapper.selectOne(wrapper).getMark();

    }

}
