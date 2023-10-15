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
 * Saving the correction notebook detail
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_correction_notebook")
@ApiModel(value="CorrectionNotebook对象", description="Saving the correction notebook detail")
public class CorrectionNotebook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of user’s favourite post")
    @TableId(value = "correction_notebook_id", type = IdType.AUTO)
    private Integer correctionNotebookId;

    @ApiModelProperty(value = "id of student")
    @TableField("student_id")
    private Integer studentId;

    @ApiModelProperty(value = "is it valid or not")
    private Integer validity;


}
