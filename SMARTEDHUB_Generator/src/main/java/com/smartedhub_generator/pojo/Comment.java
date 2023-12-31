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

    @ApiModelProperty(value = "id of comment")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "the content of comment")
    @TableField("comment_detail")
    private String commentDetail;

    @ApiModelProperty(value = "comment date")
    @TableField("comment_date")
    private LocalDate commentDate;

    @ApiModelProperty(value = "the user’s id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "count number of like")
    private Integer likes;

    @ApiModelProperty(value = "is it valid or not")
    private Integer validity;


}
