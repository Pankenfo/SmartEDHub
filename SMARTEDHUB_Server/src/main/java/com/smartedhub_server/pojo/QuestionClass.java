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
 * relationship between question and class (send question to class)
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question_class")
@ApiModel(value="QuestionClass对象", description="relationship between question and class (send question to class)")
public class QuestionClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_class_id", type = IdType.AUTO)
    private Integer questionClassId;

    @TableField("class_id")
    private Integer classId;

    @TableField("question_id")
    private Integer questionId;

    private Integer validity;


}
