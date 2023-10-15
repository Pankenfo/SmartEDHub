package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.CommentMapper;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.service.ICommentService;
import org.springframework.stereotype.Service;

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

}
