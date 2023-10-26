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

    private String level;

    private String requirements;

    private String subject;

    /**
     * 1: GPT生成的MCQ
     * 2: 老师用GPT的MCQ
     * 3: 老师用GPT生成的填空题
     * 4: 老师用GPT生成的简答题
     * 5: 老师用GPT生成的开放题
     */
    private Integer questionType;
}
