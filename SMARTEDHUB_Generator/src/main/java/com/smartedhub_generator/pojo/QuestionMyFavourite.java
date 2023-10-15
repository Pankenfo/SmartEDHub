package com.smartedhub_generator.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * relationship between question and myfavourite (save question to myfavourite)
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question_my_favourite")
@ApiModel(value="QuestionMyFavourite对象", description="relationship between question and myfavourite (save question to myfavourite)")
public class QuestionMyFavourite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_myfavourite_id", type = IdType.AUTO)
    private Integer questionMyfavouriteId;

    @TableField("question_id")
    private Integer questionId;

    @TableField("myfavourite_id")
    private Integer myfavouriteId;

    private Integer validity;


}
