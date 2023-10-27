package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.CorrectionNotebookMapper;
import com.smartedhub_server.mapper.QuestionCorrectionMapper;
import com.smartedhub_server.mapper.StudentMapper;
import com.smartedhub_server.pojo.*;
import com.smartedhub_server.service.ICorrectionNotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Saving the correction notebook detail 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class CorrectionNotebookServiceImpl extends ServiceImpl<CorrectionNotebookMapper, CorrectionNotebook> implements ICorrectionNotebookService {

    @Autowired
    private CorrectionNotebookMapper correctionNotebookMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private QuestionCorrectionMapper questionCorrectionMapper;

    @Override
    public void CreateCorrection(String username) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getUsername,username);
        CorrectionNotebook correctionNotebook = new CorrectionNotebook();
        correctionNotebook.setValidity(1);
        correctionNotebook.setStudentId(studentMapper.selectOne(wrapper).getStudentId());
        correctionNotebookMapper.insert(correctionNotebook);
    }

    @Override
    public GeneralReturn AddToCorrection(int questionId, String username) {
        QuestionCorrection questionCorrection = new QuestionCorrection();
        LambdaQueryWrapper<CorrectionNotebook> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Student> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Student::getUsername,username);
        wrapper.eq(CorrectionNotebook::getStudentId,studentMapper.selectOne(wrapper2).getStudentId());
        questionCorrection.setCorrectionNotebookId(correctionNotebookMapper.selectOne(wrapper).getCorrectionNotebookId());
        questionCorrection.setQuestionId(questionId);
        questionCorrection.setValidity(1);
        questionCorrectionMapper.insert(questionCorrection);
        return GeneralReturn.success("Add to correction correction notebook successfully");
    }


    @Override
    public GeneralReturn ListCorrectionNote(String username) {
        QuestionCorrection questionCorrection = new QuestionCorrection();
        LambdaQueryWrapper<CorrectionNotebook> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Student> wrapper2 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<QuestionCorrection> wrapper3 = new LambdaQueryWrapper<>();
        wrapper2.eq(Student::getUsername,username);
        wrapper.eq(CorrectionNotebook::getStudentId,studentMapper.selectOne(wrapper2).getStudentId());
        wrapper3.eq(QuestionCorrection::getCorrectionNotebookId,correctionNotebookMapper.selectOne(wrapper).getCorrectionNotebookId());
        return GeneralReturn.success(questionCorrectionMapper.selectList(wrapper3));
    }

    @Override
    public GeneralReturn CancelFromCorrection(int questionId, String username) {
        QuestionCorrection questionCorrection = new QuestionCorrection();
        LambdaQueryWrapper<CorrectionNotebook> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Student> wrapper2 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<QuestionCorrection> wrapper3 = new LambdaQueryWrapper<>();
        wrapper2.eq(Student::getUsername,username);
        wrapper.eq(CorrectionNotebook::getStudentId,studentMapper.selectOne(wrapper2).getStudentId());
        wrapper3.eq(QuestionCorrection::getCorrectionNotebookId,correctionNotebookMapper.selectOne(wrapper).getCorrectionNotebookId())
                .and(i->i.eq(QuestionCorrection::getQuestionId,questionId));
        questionCorrectionMapper.delete(wrapper3);
        return GeneralReturn.success("Delete successfully");
    }
}
