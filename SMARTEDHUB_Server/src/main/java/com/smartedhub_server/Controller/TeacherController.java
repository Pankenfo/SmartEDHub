package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.service.IQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private IQuestionService iquestionService;




    @PostMapping("/getQuestionById")
    @ApiOperation("Get a question by id")
    public GeneralReturn GetQuestionById(@RequestParam Integer questionId){
        return iquestionService.GetQuestionById(questionId);
    }


}
