package com.smartedhub_server.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.service.IQuestionClassService;
import com.smartedhub_server.service.IQuestionService;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @Autowired
    private IQuestionClassService iQuestionClassService;

    /**
     *
     * @param pageNo 第几页 必须输入
     * @param pageSize 一页显示几个 必须输入
     * @param questionTitle 问题的标题
     * @return
     */
    @GetMapping("/getAllQuestionPage")
    @ApiOperation("Get all the question(有分页)" )
    public GeneralReturn GetAllOrSpecificQuestion(@RequestParam(value = "pageNo") int pageNo,
                                        @RequestParam(value = "pageSize") int pageSize,
                                        @RequestParam(value = "questionTitle", required = false) String questionTitle){
        return iQuestionService.GetAllOrSpecificQuestion(pageNo,pageSize,questionTitle);

    }

    @GetMapping("/getAllQuestion")
    @ApiOperation("Get all the question(无分页)" )
    public GeneralReturn GetAllOrSpecificQuestionNoPage(@RequestParam(value = "questionTitle", required = false) String questionTitle) {
        return iQuestionService.GetAllOrSpecificQuestionNoPage(questionTitle);

    }

    /**
     *
     * @param pageNo 第几页 必须输入
     * @param pageSize 一页显示几个 必须输入
     * @param questionTitle 问题的标题
     * @param classId 班级号
     * @return
     */
    @GetMapping("/getQuestionByClassIdPage")
    @ApiOperation("Get all questions by class id(有分页)")
    public GeneralReturn GetAllQuestionByClassId(@RequestParam(value = "pageNo") int pageNo,
                                                  @RequestParam(value = "pageSize") int pageSize,
                                                  @RequestParam(value = "classId") int classId,
                                                  @RequestParam(value = "questionTitle", required = false) String questionTitle) {
        return iQuestionService.GetAllQuestionByClassId(pageNo,pageSize,classId,questionTitle);
    }
    @GetMapping("/getQuestionByClassId")
    @ApiOperation("Get all questions by class id(无分页)")
    public GeneralReturn GetQuestionByClassIdNoPage(@RequestParam(value = "classId") int classId,
                                                 @RequestParam(value = "questionTitle", required = false) String questionTitle) {
        return iQuestionService.GetAllQuestionByClassIdNoPage(classId,questionTitle);
    }

    @GetMapping("/getQuestionByStudentUsername")
    @ApiOperation("Get all questions by student username")
    public List<Question> getQuestionByStudentUsername(Principal principal) {
        if (principal == null) {
            return null;
        }
        String studentName = principal.getName();
        return iQuestionService.list(new QueryWrapper<Question>().eq("username", studentName));
    }

    @PostMapping("/createQuestion")
    @ApiOperation("Create a new question")
    public GeneralReturn createQuestion(@RequestBody Question question){
        return iQuestionService.createQuestion(question);
    }

    @PostMapping("/allocateQuestion")
    @ApiOperation("Allocate a question to a classroom")
    public GeneralReturn AllocateQuestion(@RequestParam(value = "questionId") Integer questionId,
                                          @RequestParam(value = "classId") Integer classId){
        return iQuestionClassService.AllocateQuestion(questionId, classId);
    }

    @PostMapping("/getQuestionById")
    @ApiOperation("Get a question by id")
    public GeneralReturn GetQuestionById(@RequestParam Integer questionId){
        return iQuestionService.GetQuestionById(questionId);
    }

    @PutMapping("/like")
    @ApiOperation("Like the question")
    public int LikeQuestion(@RequestParam Integer questionId){
        return iQuestionService.LikeQuestion(questionId);
    }

    @PutMapping("/cancelLike")
    @ApiOperation("Cancel like the question")
    public int CancelLikeQuestion(@RequestParam Integer questionId){
        return iQuestionService.CancelLikeQuestion(questionId);
    }

}
