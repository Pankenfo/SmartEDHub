package com.smartedhub_server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: SMARTEDHUB
 * @description:
 * @author: Junxian Cai
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserLoginInfo", description = "")
public class UserLoginInfo {

    @ApiModelProperty(value = "username", required = true)
    private String username;
    @ApiModelProperty(value = "password", required = true)
    private String password;


}
