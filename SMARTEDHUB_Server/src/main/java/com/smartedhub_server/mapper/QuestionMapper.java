package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smartedhub_server.pojo.Question;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Storage the details of each question Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    //public List<Question> GetAllQuestionByClassId(int classId);

    IPage<Question> GetAllQuestionByClassId(IPage<Question> page,int classId,String questionTitle);

    List<Question> GetAllQuestionByClassIdNopage(Integer classId, String questionTitle);

    void AddLike(int questionId);

    void CancelLike(int questionId);

    List<Question> StudentGetAllQuestion(Integer studentId);

    String QuestionGetTeacher(Integer studentId, Integer questionId);

    String QuestionGetClassName(Integer studentId, Integer questionId);

}
