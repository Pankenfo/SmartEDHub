package com.smartedhub_server.Controller;

import cn.hutool.http.HttpResponse;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.QuestionInfo;
import com.smartedhub_server.service.IGPTService;
import io.github.asleepyfish.util.OpenAiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 * @program: SmartEDHub
 * @description: For connecting to the GPT
 * @author: Junxian Cai
 **/

@RestController("/openAI")
@Api(tags = "GPT Controller")
public class GPTController {



    @Autowired
    private IGPTService gptService;

    /**
     * 用于向GPT发送文本请求 - 设计用于生成MCQ
     * @param text
     * @return
     */
    //TODO: 改成我们写好语句然后封装发给GPT，前端只需要传输选择题的内容
    @ApiOperation(value = "generateMCQ")
    @PostMapping("/generateMCQ")
    public GeneralReturn sendTextToGPT(@RequestParam String text, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            return gptService.sendText(text, username);
        }
        return GeneralReturn.error("Please login first!");
    }

    /**
     * 调用官方封装Utils
     * @param text
     * @return
     */
    @ApiOperation(value = "sendTextToGPTOfficial")
    @PostMapping("/sendTextToGPTOfficial")
    public List<String> sendTextToGPTOfficial(String text) {
        return OpenAiUtils.createChatCompletion(text);
    }

    /**
     * 可以生成照片，返回的是一个url可以直接访问拿到照片
     * @param text
     * @return
     */
    //TODO: 可以改这个接口拿来做智能生成头像的功能！！
    @ApiOperation(value = "generateImages")
    @PostMapping("/generateImagesByGPT")
    public List<String> generateImagesByGPT(@RequestParam String text) {
        List<String> image = OpenAiUtils.createImage(text);
        return image;
    }

    @ApiOperation(value = "Teacher Generate Questions By AI")
    @PostMapping("/teacherGenerateQuestionsByGPT")
    public GeneralReturn teacherGenerateQuestionByGPT(@RequestBody QuestionInfo questionInfo) {
        return gptService.teacherGenerateQuestionByGPT(questionInfo);
    }

    /**
     * Translation
     * @param text
     * @return
     */
    //TODO: 做一个智能翻译功能
//    @ApiOperation(value = "translation")
//    @PostMapping("/translation")
//




}
