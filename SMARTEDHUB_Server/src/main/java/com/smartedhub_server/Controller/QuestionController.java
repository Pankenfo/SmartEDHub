package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.service.IQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Storage the details of each question 前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;

    /**
     *
     * @param pageNo 第几页 必须输入
     * @param pageSize 一页显示几个 必须输入
     * @param questionTitle 问题的标题
     * @param questionDetail 问题的详细内容
     * @return
     */
    @GetMapping("/getAllQuestion")
    @ApiOperation("Get all the question")
    public GeneralReturn GetAllOrSpecificQuestion(@RequestParam(value = "pageNo") int pageNo,
                                        @RequestParam(value = "pageSize") int pageSize,
                                        @RequestParam(value = "questionTitle", required = false) String questionTitle,
                                        @RequestParam(value = "questionDetail", required = false) String questionDetail){
        return iQuestionService.GetAllOrSpecificQuestion(pageNo,pageSize,questionTitle,questionDetail);

    }

    /**
     *
     * @param pageNo 第几页 必须输入
     * @param pageSize 一页显示几个 必须输入
     * @param questionTitle 问题的标题
     * @param questionDetail 问题的详细内容
     * @param classId 班级号
     * @return
     */
    @GetMapping("/getAllQuestionByClassId")
    @ApiOperation("Get all questions by class id")
    public GeneralReturn GetAllQuestionByClassId(@RequestParam(value = "pageNo") int pageNo,
                                                  @RequestParam(value = "pageSize") int pageSize,
                                                  @RequestParam(value = "classId") int classId,
                                                  @RequestParam(value = "questionTitle", required = false) String questionTitle,
                                                  @RequestParam(value = "questionDetail", required = false) String questionDetail) {
        return iQuestionService.GetAllQuestionByClassId(pageNo,pageSize,classId,questionTitle,questionDetail);
    }

    @PostMapping("/createQuestion")
    @ApiOperation("Create a new question")
    public GeneralReturn createQuestion(@RequestBody Question question){
        return iQuestionService.createQuestion(question);
    }
}
