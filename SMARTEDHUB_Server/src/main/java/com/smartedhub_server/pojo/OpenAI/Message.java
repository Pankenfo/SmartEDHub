package com.smartedhub_server.pojo.OpenAI;

import lombok.Data;

/**
 * @program: SmartEDHub
 * @description: For text return object
 * @author: Junxian Cai
 **/

/**
 * Must name Message, otherwise the json will not be parsed correctly.
 * It will not get the content response from OpenAI's API correctly.
 */
@Data
public class Message {

    private String role;
    private String content;


}
