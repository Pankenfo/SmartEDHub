package com.smartedhub_server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_classroom")
@ApiModel(value="Class对象", description="")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "class_id", type = IdType.AUTO)
    @ApiModelProperty(value = "id", hidden = true)
    private Integer classId;

    @ApiModelProperty(value = "classname")
    @TableField("classname")
    private String classname;

    @TableField("teacher_username")
    @ApiModelProperty(value = "teacher_username", hidden = true)
    private String username;

    @TableLogic(value="1",delval="0")
    @ApiModelProperty(value = "validity", hidden = true)
    //value为正常数据的值，delval为删除数据的值
    private Integer validity;


}
