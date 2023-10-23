package com.smartedhub_server.service.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.smartedhub_server.mapper.QuestionMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.OpenAI.Message;
import com.smartedhub_server.pojo.OpenAI.GPTResponse;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.service.IGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: SmartEDHub
 * @description:
 * @author: Junxian Cai
 **/

/**
 *
 *
 */
@Service
public class IGPTServiceImpl implements IGPTService {

    @Value("${ChatGPT.token}")
    private String apiKey;
    @Value("${ChatGPT.variables.maxTokens}")
    private String maxTokens;
    @Value("${ChatGPT.variables.model}")
    private String model;
    @Value("${ChatGPT.variables.temperature}")
    private String temperature;
    @Autowired
    private QuestionMapper questionMapper;


    /**
     * 提问类型可能要处理成这样："按照题目，选项和正确答案的形式，生成一道小学一年级的数学选择题"
     * @param prompt
     * @return
     */
    @Override
    public GeneralReturn sendText(String prompt, String username) {
        return getMCQ(prompt, username);
    }

    /**
     * 获得并处理GPT返回的数据
     * @param prompt
     * @return
     */
    public GeneralReturn getMCQ (String prompt, String username) {
        JSONObject bodyJson = new JSONObject();
        Message message = new Message();
//        String questionByUser = "按照题目写一行，四个选项分别一行和正确答案一行的形式，生成一道" + prompt + "选择题";
        String questionByUser = "In the form of writing one line for the question, one line for each of the four options start with A./B./C./D. and one line for the correct answer, generate an " + prompt + " multiple-choice question in english";
        message.setContent(questionByUser);
        message.setRole("system");
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message);
        bodyJson.put("messages", messages);
        bodyJson.put("model", model);
        bodyJson.put("max_tokens", Integer.parseInt(maxTokens));
        bodyJson.put("temperature", Double.parseDouble(temperature));
//
        HttpResponse httpResponse =
                // 官网请求，没梯子不能访问
                // HttpUtil.createPost("https://api.openai.com/v1/chat/completions")
                // 使用代理地址 https://api.openai-proxy.com/
                HttpUtil.createPost("https://api.openai.com/v1/chat/completions")
                        .header(Header.AUTHORIZATION, "Bearer " + apiKey)
                        .header(Header.CONTENT_TYPE, "application/json")
                        .body(JSONUtil.toJsonStr(bodyJson)).execute();
        String resStr = httpResponse.body();

        GPTResponse gptResponse = JSONUtil.toBean(resStr, GPTResponse.class);

//        return gptResponse.getChoices().get(0).getGptMessage().getContent().replaceAll("\n", "");

        //提取GPT生成的选择题
        String messageByGPT = gptResponse.getChoices().get(0).getMessage().getContent();
        System.out.println("=========================");
        System.out.println("messageByGPT: \n" + messageByGPT);
        System.out.println("=========================");

        //处理GPT返回的题目内容
        String[] lines = messageByGPT.split("\n");
        if (lines.length > 0) {
            Question question = new Question();
            question.setQuestionTitle(lines[0]);
            //设置题目选择项
            List<String> options = new ArrayList<>();
            String details = "";
            for (String line : lines) {
                if (line.startsWith("A")) {
                    details = details + line + " ";
                } else if (line.startsWith("B")) {
                    details = details + line + " ";
                } else if (line.startsWith("C")) {
                    details = details + line + " ";
                } else if (line.startsWith("D")) {
                    details = details + line + " ";
                    break;
                }
            }
            question.setQuestionDetail(details);
            question.setQuestionDate(new Date());
            //这里我甚至可以将"正确答案:"去掉，只留下正确的选项
            question.setCorrectAnswer(lines[lines.length - 1]);
            question.setLikes(0);
            question.setUsername(username);
            question.setQuestionType(1);
            question.setValidity(1);

            //将题目存入数据库
            int result = questionMapper.insert(question);
            if (result == 1) {
                return GeneralReturn.success("Insert question successfully", question);
            }

        }
        return GeneralReturn.error("Insert question failed");
    }

}
