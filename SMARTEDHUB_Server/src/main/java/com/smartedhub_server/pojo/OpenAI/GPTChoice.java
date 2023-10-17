package com.smartedhub_server.pojo.OpenAI;

import lombok.Data;

/**
 * @program: SmartEDHub
 * @description: Text model returns content
 * @author: Junxian Cai
 **/

@Data
public class GPTChoice {

    private String text;
    private Integer index;
    private Message message;

}
