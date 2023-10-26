package com.smartedhub_server.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: SmartEDHub
 * @description: Used to record the difficulty of the problem, description, discipline
 * @author: Junxian Cai
 **/

@Data
@ApiModel(value="QuestionInfo", description="Storage the details of each question")
public class QuestionInfo {

    private String difficulty;

    private String description;

    private String discipline;
}
