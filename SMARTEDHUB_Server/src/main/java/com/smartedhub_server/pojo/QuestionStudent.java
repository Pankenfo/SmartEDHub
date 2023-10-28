package com.smartedhub_server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * relationship between question and student (history of student do question)
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question_student")
@ApiModel(value="QuestionStudent对象", description="relationship between question and student (history of student do question)")
public class QuestionStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_student_id", type = IdType.AUTO)
    private Integer questionStudentId;

    @TableField("question_id")
    private Integer questionId;

    @TableField("student_id")
    private Integer studentId;

    @TableField("teacher_username")
    private String teacherUsername;

    @TableField("classname")
    private String classname;

    @TableField("question_answer")
    private String questionAnswer;

    @TableField("mark")
    private Integer mark;

    private Integer validity;


}
