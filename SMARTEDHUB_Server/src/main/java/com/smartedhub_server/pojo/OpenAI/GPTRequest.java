package com.smartedhub_server.pojo.OpenAI;

import lombok.Data;

import java.util.List;

/**
 * @program: SmartEDHub
 * @description: A request class
 * @author: Junxian Cai
 **/

@Data
public class GPTRequest {

    // The question asked by the user
    private String question;
    // The answer returned by openai
    private String answer;
    private List<GPTData> returnImages;

}
