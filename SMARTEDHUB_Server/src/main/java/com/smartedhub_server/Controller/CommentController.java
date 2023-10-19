package com.smartedhub_server.Controller;


import com.smartedhub_server.exception.ParamsException;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>
 * For storage the users’ comment  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;


    /**
     * 目前comment只允许学生评论, 只允许学生评论question不能评论comment
     * @param comment
     * @param questionId
     * @return
     * @throws ParamsException
     */
    //TODO:接口文档要传的参数不对,看看是什么原因
    @PostMapping("/leaveComment")
    @ApiOperation(value = "Create a comment")
    public GeneralReturn createComment(@RequestBody Comment comment, @RequestParam Integer questionId, Principal principal) {

        if (principal == null) {
            return null;
        }
        String currentUsername = principal.getName();
        return commentService.createComment(comment, questionId, currentUsername);
    }

    @ApiOperation(value = "get comment by question id")
    @GetMapping("/getCommentByQuestionId")
    public GeneralReturn getCommentByQuestionId(@RequestParam Integer questionId) {
        return commentService.getCommentByQuestionId(questionId);
    }

    /**
     * For admin delete comment
     * @param commentId
     * @return
     */
    @ApiOperation(value = "delete comment")
    @DeleteMapping("/deleteComment")
    public GeneralReturn deleteComment(@RequestParam Integer commentId) {
        if (commentService.removeById(commentId)) {
            return GeneralReturn.success("Delete comment success.");
        } else {
            return GeneralReturn.error("Delete comment failed, please try again.");
        }
    }

    @ApiOperation(value = "like a comment")
    @PostMapping("/likeComment")
    public GeneralReturn likeComment(@RequestParam Integer commentId) {
        return commentService.likeComment(commentId);
    }

}
