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

    @ApiModelProperty(value = "id of announcement", hidden = true)
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Integer announcementId;

    @ApiModelProperty(value = "announcement title")
    @TableField("announcement_title")
    private String announcementTitle;

    @ApiModelProperty(value = "the content of announcement")
    @TableField("announcement_detail")
    private String announcementDetail;

    @ApiModelProperty(value = "announcement date", hidden = true)
    @TableField("announcement_date")
    private Date announcementDate;

    @ApiModelProperty(value = "the teacher’s id", hidden = true)
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty(value = "the class’s id", hidden = true)
    @TableField("class_id")
    private Integer classId;

    @TableLogic(value = "1", delval = "0")
    @ApiModelProperty(value = "is it valid or not", hidden = true)
    private Integer validity;


}
