package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.exception.ParamsException;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.pojo.GeneralReturn;

import java.security.Principal;

/**
 * <p>
 * For storage the users’ comment  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface ICommentService extends IService<Comment> {

    GeneralReturn createComment(Comment comment, Integer questionId, String currentUsername);

    /**
     * get comment by question id
     * @param questionId
     * @return
     */
    GeneralReturn getCommentByQuestionId(Integer questionId);

    /**
     * Like a comment
     * @param commentId
     * @return
     */
    GeneralReturn likeComment(Integer commentId);
}
