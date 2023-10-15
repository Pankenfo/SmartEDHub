package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.Comment;
import com.smartedhub_generator.mapper.CommentMapper;
import com.smartedhub_generator.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
