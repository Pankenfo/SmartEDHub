package com.smartedhub_server.service;


import com.smartedhub_server.pojo.GeneralReturn;

public interface IGPTService {

    /**
     * For sending text to GPT-3.5
     * @param text
     * @return
     */
    GeneralReturn sendText(String text);
}
