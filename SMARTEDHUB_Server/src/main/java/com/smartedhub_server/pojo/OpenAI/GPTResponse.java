package com.smartedhub_server.pojo.OpenAI;

import lombok.Data;

import java.util.List;

/**
 * @program: SmartEDHub
 * @description: For json return object
 * @author: Junxian Cai
 **/

@Data
public class GPTResponse {

    private String id;
    private String object;
    private String created;
    private String model;
    private List<GPTChoice> choices;

}
