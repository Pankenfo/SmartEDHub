package com.smartedhub_generator.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Storage the details of each question
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
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
    private LocalDate questionDate;

    @ApiModelProperty(value = "id of teacher")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty(value = "is it valid or not")
    private Integer validity;


}
