package com.smartedhub_server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

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
@TableName("t_student")
@ApiModel(value="Student对象", description="")
public class Student implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",hidden = true)
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @ApiModelProperty(value = "students’ real name")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "Can this student make a comment or not", hidden = true)
    @TableField("right_to_comment")
    private Integer rightToComment;

    @ApiModelProperty(value = "mobile phone number")
    private String mobile;

    @ApiModelProperty(value = "gender")
    private String gender;

    @ApiModelProperty(value = "email address")
    private String email;

    @ApiModelProperty(value = "location", hidden = true)
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "avatar", hidden = true)
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "is it valid or not")
    @TableLogic(value = "1", delval = "0")
    @TableField("validity")
    private boolean validity;


    @Override
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isEnabled() {
        return validity;
    }
}
