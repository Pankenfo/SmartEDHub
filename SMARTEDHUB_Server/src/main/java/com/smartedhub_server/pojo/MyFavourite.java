package com.smartedhub_server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * The notebook that saves the question that student favourite
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_my_favourite")
@ApiModel(value="MyFavourite对象", description="The notebook that saves the question that student favourite")
public class MyFavourite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of student’s myfavourite notebook")
    @TableId(value = "myfavourite_id", type = IdType.AUTO)
    private Integer myfavouriteId;

    @ApiModelProperty(value = "username of student")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "is it valid or not")
    private Integer validity;


}
