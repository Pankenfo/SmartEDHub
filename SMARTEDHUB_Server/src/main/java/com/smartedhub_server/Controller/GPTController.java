package com.smartedhub_server.Controller;

import cn.hutool.http.HttpResponse;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IGPTService;
import io.github.asleepyfish.util.OpenAiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
     * 用于向GPT发送文本请求
     * @param text
     * @return
     */
    @ApiOperation(value = "sendTextToGPT")
    @PostMapping("/sendTextToGPT")
    public GeneralReturn sendTextToGPT(String text) {
        return gptService.sendText(text);
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
    @ApiOperation(value = "generateImages")
    @PostMapping("/generateImagesByGPT")
    public List<String> generateImagesByGPT(@RequestParam String text) {
        List<String> image = OpenAiUtils.createImage(text);
        return image;
    }



    /**
     * 用于向GPT发送文本请求 - 生成选择题
     * @param text
     * @return
     */
//    @ApiOperation(value = "sendTextToGPTGeneratedMCQ")
//    @PostMapping("/sendTextToGPTGeneratedMCQ")
//    public String sendTextToGPTGeneratedMCQ(String text) {
//        return gptService.sendTextGeneratedMCQ(text);
//    }

}
