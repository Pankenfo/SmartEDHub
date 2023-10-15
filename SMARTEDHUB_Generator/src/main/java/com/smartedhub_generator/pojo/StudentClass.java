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
 * relationship between student and class (student belongs to class)
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_student_class")
@ApiModel(value="StudentClass对象", description="relationship between student and class (student belongs to class)")
public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_class_id", type = IdType.AUTO)
    private Integer studentClassId;

    @TableField("class_id")
    private Integer classId;

    @TableField("student_id")
    private Integer studentId;

    private Integer validity;


}
