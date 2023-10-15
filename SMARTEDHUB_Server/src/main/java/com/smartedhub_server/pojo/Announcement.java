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
import java.time.LocalDate;

/**
 * <p>
 * For storage the announcement 
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_announcement")
@ApiModel(value="Announcement对象", description="For storage the announcement ")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id of announcement")
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Integer announcementId;

    @ApiModelProperty(value = "announcement title")
    @TableField("announcement_title")
    private String announcementTitle;

    @ApiModelProperty(value = "the content of announcement")
    @TableField("announcement_detail")
    private String announcementDetail;

    @ApiModelProperty(value = "announcement date")
    @TableField("announcement_date")
    private LocalDate announcementDate;

    @ApiModelProperty(value = "the teacher’s id")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty(value = "the class’s id")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty(value = "is it valid or not")
    private Integer validity;


}
