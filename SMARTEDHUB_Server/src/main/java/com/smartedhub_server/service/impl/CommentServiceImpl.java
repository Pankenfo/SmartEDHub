package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.exception.ParamsException;
import com.smartedhub_server.mapper.CommentMapper;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.ICommentService;
import com.smartedhub_server.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * For storage the users’ comment  服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public GeneralReturn createComment(Comment comment,Integer questionId, String currentUsername) {


        comment.setUsername(currentUsername);
        comment.setCommentDate(new Date());
        comment.setQuestionId(questionId);
        comment.setLikes(0);
        comment.setValidity(1);
        int result = commentMapper.insert(comment);

        if (result != 0){
            return GeneralReturn.success("Comment success.");
        }

        return GeneralReturn.error("Comment create failed, please try again.");
    }

    /**
     * get comment by question id
     * @param questionId
     * @return
     */
    @Override
    public GeneralReturn getCommentByQuestionId(Integer questionId) {

        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("question_id", questionId));
        if (commentList != null && commentList.size() > 0) {
            return GeneralReturn.success(commentList);
        }
        return GeneralReturn.success("No comment found.");
    }

    /**
     * Like a comment
     * @param commentId
     * @return
     */
    @Override
    public GeneralReturn likeComment(Integer commentId) {

        //return the amount of like
        Comment comment = commentMapper.selectById(commentId);
        Integer currentLikes = comment.getLikes();
        comment.setLikes(currentLikes + 1);
        int result = commentMapper.updateById(comment);
        if (result == 1) {
            return GeneralReturn.success("Like success.");
        }
        return GeneralReturn.error("Like failed, please try again.");
    }


}
