package com.smartedhub_server.service;


import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.QuestionInfo;

import java.security.Principal;

public interface IGPTService {

    /**
     * For sending text to GPT-3.5
     * @param text
     * @return
     */
    GeneralReturn sendText(String text, String username);

    /**
     * For teacher generate question by GPT
     * @param questionInfo
     * @return
     */
    GeneralReturn teacherGenerateQuestionByGPT(QuestionInfo questionInfo, Principal principal);

    /**
     * For user translation
     * @param text
     * @param targetLanguage
     * @return
     */
    String translation(String text, String targetLanguage);

    /**
     * For user revise an essay
     * @param text
     * @return
     */
    String reviseAnEssay(String text);
}
