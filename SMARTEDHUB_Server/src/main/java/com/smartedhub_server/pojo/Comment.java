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
 * For storage the users’ comment 
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comment")
@ApiModel(value="Comment对象", description="For storage the users’ comment ")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of comment",hidden = true)
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "the content of comment")
    @TableField("comment_detail")
    private String commentDetail;

    @ApiModelProperty(value = "comment date",hidden = true)
    @TableField("comment_date")
    private Date commentDate;

    @ApiModelProperty(value = "the user’s id", hidden = true)
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "question id", hidden = true)
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty(value = "count number of like", hidden = true)
    private Integer likes;

    @ApiModelProperty(value = "is it valid or not", hidden = true)
    @TableField("validity")
    @TableLogic(value = "1", delval = "0")
    private Integer validity;


}
