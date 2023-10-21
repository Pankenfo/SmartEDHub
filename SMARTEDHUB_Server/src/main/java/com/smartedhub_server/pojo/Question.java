package com.smartedhub_server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * Storage the details of each question
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question")
@ApiModel(value="Question对象", description="Storage the details of each question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of question")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty(value = "the detail of question")
    @TableField("question_detail")
    private String questionDetail;

    @ApiModelProperty(value = "question title")
    @TableField("question_title")
    private String questionTitle;

    @ApiModelProperty(value = "question date")
    @TableField("question_date")
    private Date questionDate;

    @ApiModelProperty(value = "username of both teacher/student")
    private String username;

    @ApiModelProperty(value = "the correct answer of MCQ")
    @TableField("correct_answer")
    private String correctAnswer;

    @ApiModelProperty(value = "the amount of like")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "question type")
    @TableField("question_type")
    private Integer questionType;

    @ApiModelProperty(value = "is it valid or not")
    @TableLogic(value = "1", delval = "0")
    private Integer validity;


}
